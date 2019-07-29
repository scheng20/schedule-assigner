package trash;

import model.Person;

import java.util.ArrayList;

public class MarketingDay extends HasGroups {

    private String date;

    private ArrayList<Person> assignedPeople;
    private Boolean assigned;

    public MarketingDay(String date) {
        this.date = date;
        this.assigned = false;
        this.assignedPeople = new ArrayList<Person>();
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

    // FOR WEEK 8 DELIVERABLE
    public void assignPerson(Person p) {

        if (!assignedPeople.contains(p)) {
            assignedPeople.add(p);
            p.assignDate(this);
        }

    }
}
