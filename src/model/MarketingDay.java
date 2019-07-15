package model;

import java.util.ArrayList;

public class MarketingDay {

    private String date;
    private ArrayList<String> groups;

    public MarketingDay(String date, ArrayList<String> groups) {
        this.date = date;
        this.groups = groups;
    }

    public String getDateAsString() {
        return date;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }
}
