package com.github.alllef.task3;

import java.util.List;

public class Main {
    public static void main(String[]args){
        Group firstGroup = new Group("first");
        Group secondGroup = new Group("second");
        Group thirdGroup = new Group("third");
        Journal journal = new Journal(List.of(firstGroup,secondGroup,thirdGroup));
        List.of("lecturer","practician1","practician2","practician3").forEach(str-> new GradingThread(str,journal).start());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
