package com.github.alllef.task2;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private int[] numbers;

    public Producer(Drop drop, int size) {
        this.drop = drop;
        numbers = new int[size];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = i;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            drop.put(numbers[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        drop.put(Integer.MAX_VALUE);
    }
}
