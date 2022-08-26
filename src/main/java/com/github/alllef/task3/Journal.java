package com.github.alllef.task3;

import java.util.List;
import java.util.Random;

public class Journal {
    private final List<Group> groups;

    public Journal(List<Group> groups) {
        this.groups = groups;
    }

    public synchronized void setMark(int mark) {
        int randomGroup = new Random().nextInt(groups.size());
        Group group = groups.get(randomGroup);
        group.getMarks().add(mark);
        System.out.println("Mark "+mark + "has been added to group "+group.getName()+ " by thread "+Thread.currentThread().getName());
    }
}
