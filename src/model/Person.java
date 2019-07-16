package model;

import java.util.ArrayList;

public class Person {
    private String name;
    private ArrayList<String> groups;
    private int postTimes;

    public Person(String name) {
        this.name = name;
        this.postTimes = 0;
        this.groups = new ArrayList<String>();
    }

    public void incrementPostTimes() {
        postTimes++;
    }

    public void addGroup(String group) {
        groups.add(group);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public int getPostTimes() {
        return postTimes;
    }

}
