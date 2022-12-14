package com.github.alllef.task3;

import java.util.Random;

public class GradingThread extends Thread {
    private final Journal journal;

    public GradingThread(String name, Journal journal) {
        super(name);
        this.journal = journal;
    }

    @Override
    public void run() {
        while (true) {
            int mark = new Random().nextInt(100) + 1;
            journal.setMark(mark);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
