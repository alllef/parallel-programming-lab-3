package com.github.alllef.task2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Drop {
    // Message sent from producer
    // to consumer.
    private int message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    public Drop() {
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
        return message;
    }

    public synchronized void put(int number) {
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
        this.message = number;
        // Notify consumer that status
        // has changed.
        notifyAll();
    }
}