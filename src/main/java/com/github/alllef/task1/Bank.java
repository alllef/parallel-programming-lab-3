package com.github.alllef.task1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;
    private ReentrantLock reentrantLock;
    private Condition notExchangedCondition;
    private Condition exchangedCondition;
    private boolean isExchanged = false;

    public Bank(int n, int initialBalance) {
        reentrantLock = new ReentrantLock();
        notExchangedCondition = reentrantLock.newCondition();
        exchangedCondition = reentrantLock.newCondition();
        reentrantLock = new ReentrantLock();
        accounts = new int[n];
        int i;
        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) {
        reentrantLock.lock();
        try {
            while (isExchanged)
                notExchangedCondition.await();

            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            isExchanged = true;
            exchangedCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace(); // throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void testWithCondition() {
        reentrantLock.lock();
        try {
            while (!isExchanged)
                exchangedCondition.await();

            if (ntransacts % NTEST == 0)
                test();
            isExchanged = false;
            notExchangedCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace(); // throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i];
        System.out.println("Transactions:" + ntransacts
                + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }
}