package com.github.alllef.task2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Drop {
    // Message sent from producer
    // to consumer.
    private int[] numbers;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;
    private int iter = 0;

    public Drop(int arrSize) {
        numbers = new int[arrSize];
        this.numbers = new int[arrSize];
    }

    public synchronized int take() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        return numbers[new Random().nextInt(numbers.length)];
    }

    public synchronized void put(int number) {
        System.out.println("WTF");
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Toggle status.
        empty = false;
        // Store message.
        numbers[iter] = number;
        iter++;
        // Notify consumer that status
        // has changed.
        notifyAll();
    }

    public int[] getNumbers() {
        return numbers;
    }
}