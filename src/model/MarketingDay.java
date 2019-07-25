package model;

import java.util.ArrayList;

public class MarketingDay {

    private String date;
    private ArrayList<String> groups;
    private ArrayList<Person> assignedPeople;
    private Boolean assigned;

    public MarketingDay(String date) {
        this.date = date;
        this.groups = new ArrayList<String>();
        this.assigned = false;
        this.assignedPeople = new ArrayList<Person>();
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public String getDate() {
        return date;
    }

    public void setAssigned(boolean status) {
        assigned = status;
    }

    public boolean getAssigned() {
        return assigned;
    }

    public void addPerson(Person p) {
        assignedPeople.add(p);
    }
}
