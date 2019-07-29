package model;

import java.util.ArrayList;
import java.util.Objects;

public class Person extends HasGroups {
    private String name;
    private ArrayList<String> groups;
    private ArrayList<MarketingDay> assignedDays;
    private boolean assigned;

    public Person(String name) {
        this.name = name;
        this.groups = new ArrayList<String>();
        this.assigned = false;
    }

    public String getName() {
        return name;
    }

    public boolean getAssigned() {
        return assigned;
    }

    // FOR WEEK 8 DELIVERABLE
    public void assignDate(MarketingDay day) {

        if (!assignedDays.contains(day)) {
            assignedDays.add(day);
            day.assignPerson(this);
        }

        assigned = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;
        return name.equals(person.name)
                &&
                groups.equals(person.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, groups);
    }
}
