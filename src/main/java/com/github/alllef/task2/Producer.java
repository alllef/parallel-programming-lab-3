package com.github.alllef.task2;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < drop.getNumbers().length - 1; i++) {
            drop.put(random.nextInt(200));
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
        drop.put(Integer.MAX_VALUE);

    }
}
