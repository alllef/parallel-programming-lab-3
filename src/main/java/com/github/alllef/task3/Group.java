package com.github.alllef.task3;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private final List<Integer> marks = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

}
