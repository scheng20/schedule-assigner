package model;

import java.util.ArrayList;
import java.util.Objects;

public class Person {

    private String name;

    private ArrayList<Group> isInGroups;

    private ArrayList<Group> assignedGroups;

    private boolean assigned;

    public Person(String name) {
        this.name = name;
        this.isInGroups = new ArrayList<Group>();
        this.assigned = false;
    }

    public String getName() {
        return name;
    }

    public void setAssigned(boolean status) {
        assigned = status;
    }

    public boolean getAssigned() {
        return assigned;
    }

    // FOR WEEK 8 DELIVERABLE
    public void assignGroup(Group group) {

        if (!assignedGroups.contains(group)) {
            assignedGroups.add(group);
            group.setPerson(this);
        }

        assigned = true;
    }

    public void setGroups(ArrayList<String> groupNames) {

        for (int i = 0; i < groupNames.size(); i++) {
            String currentName = groupNames.get(i);
            Group g = new Group(currentName);
            isInGroups.add(g);
        }
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
