package model;

import java.util.Objects;

public class Group {

    private String name;
    private Person personResponsible;

    // If you change this back to null, make sure to change the method
    // that determines whether or not the group has been assigned!
    private Person nobody = new Person("Nobody");

    public Group(String name) {

        this.name = name;
        this.personResponsible = nobody;
    }

    // FOR WEEK 8 DELIVERABLE
    public void setPerson(Person p) {

        if (personResponsible != p) {
            personResponsible = p;
            p.assignGroup(this);
        }
    }

    // EFFECTS: returns the name of the group
    public String getName() {
        return name;
    }

    // EFFECTS: returns the person responsible for sharing this group
    public Person getPersonResponsible() {
        return personResponsible;
    }

    // EFFECTS: returns true if a person is assigned to this group, else false
    public Boolean isAssigned() {
        return personResponsible != nobody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(name, group.name)
                && Objects.equals(personResponsible, group.personResponsible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, personResponsible);
    }
}
