package model;

import java.util.ArrayList;
import java.util.Objects;

public class Person {

    private String name;

    private ArrayList<Group> isInGroups;

    private ArrayList<Group> assignedGroups;

    public Person(String name) {
        this.name = name;
        this.isInGroups = new ArrayList<Group>();
        this.assignedGroups = new ArrayList<Group>();
    }

    public String getName() {
        return name;
    }

    // FOR WEEK 8 DELIVERABLE
    public void assignGroup(Group group) {

        if (!assignedGroups.contains(group)) {
            assignedGroups.add(group);
            group.setPerson(this);
        }
    }

    public void setGroups(ArrayList<Group> isInGroups) {

        this.isInGroups = isInGroups;
    }

    public ArrayList<String> getGroupsAsStrings() {

        ArrayList<String> result = new ArrayList<>();

        for (Group g: isInGroups) {
            result.add(g.getName());
        }

        return result;
    }

    public ArrayList<Group> getGroups() {
        return isInGroups;
    }

    public ArrayList<Group> getAssignedGroups() {
        return assignedGroups;
    }

    public boolean hasGroupAssigned() {

        if (assignedGroups.size() >= 1) {

            return true;

        } else {
            return false;
        }
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
                && isInGroups.equals(person.isInGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isInGroups);
    }
}
