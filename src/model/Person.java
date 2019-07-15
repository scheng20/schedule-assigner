package model;

import java.util.ArrayList;

public class Person {
    private String name;
    private ArrayList<String> groups;
    private int postTimes;

    public Person(String name, ArrayList<String> groups) {
        this.name = name;
        this.groups = groups;
    }

    public void incrementPostTimes() {
        postTimes++;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

}
