package com.github.alllef.task2;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop(100);
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}