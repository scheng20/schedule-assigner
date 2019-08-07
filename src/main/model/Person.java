package model;

import java.util.ArrayList;
import java.util.Objects;

public class Person {

    private String name;

    private ArrayList<Group> isInGroups;

    private ArrayList<Group> assignedGroups;

    private String isInGroupsString;

    // Constructs a person
    public Person(String name) {
        this.name = name;
        this.isInGroups = new ArrayList<Group>();
        this.assignedGroups = new ArrayList<Group>();
        this.isInGroupsString = "";
    }

    // MODIFIES: this
    // EFFECTS: Assigns given group to person, and also assigns person to given group.
    public void assignGroup(Group group) {

        if (!assignedGroups.contains(group)) {
            assignedGroups.add(group);
            group.setPerson(this);
        }
    }

    // EFFECTS: Returns true if current person has a group assigned, false otherwise.
    public boolean hasGroupAssigned() {

        if (assignedGroups.size() >= 1) {

            return true;

        } else {
            return false;
        }
    }

    // ------------------------------ GETTERS AND SETTERS -------------------------------

    // EFFECTS: Sets the person's groups and adds each group to IsInGroupString
    public void setGroups(ArrayList<Group> isInGroups) {

        this.isInGroups = isInGroups;

        for (Group g: isInGroups) {
            isInGroupsString += g.getName() + ", ";
        }

        isInGroupsString = isInGroupsString.substring(0, isInGroupsString.length() - 2);
    }

    public String getIsInGroupsString() {

        return isInGroupsString;
    }

    public ArrayList<Group> getGroups() {
        return isInGroups;
    }

    public ArrayList<Group> getAssignedGroups() {
        return assignedGroups;
    }

    public String getName() {
        return name;
    }

    // ------------------------- OVERRIDING EQUALS AND HASHCODE -------------------------
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

    // ------------------------- OVERRIDING TO STRING -------------------------
    @Override
    public String toString() {
        return name;
    }
}
