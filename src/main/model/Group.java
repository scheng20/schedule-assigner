package model;

import java.util.Objects;

public class Group {

    private String name;
    private Person personResponsible;

    // Note to self:
    // If you change this default value to null, make sure to change the method
    // that determines whether or not the group has been assigned!
    private Person nobody = new Person("Nobody");

    // Constructs a group
    public Group(String name) {

        this.name = name;
        this.personResponsible = nobody;
    }

    // MODIFIES: this
    // EFFECTS: Sets the person responsible for sharing this group to the given person
    public void setPerson(Person p) {

        if (personResponsible != p) {
            personResponsible = p;
            p.assignGroup(this);
        }
    }

    // EFFECTS: returns true if a person is assigned to this group, else false
    public Boolean isAssigned() {
        return personResponsible != nobody;
    }

    // ------------------------------ GETTERS AND SETTERS -------------------------------

    public String getName() {
        return name;
    }

    public Person getPersonResponsible() {
        return personResponsible;
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
        Group group = (Group) o;
        return Objects.equals(name, group.name)
                && Objects.equals(personResponsible, group.personResponsible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, personResponsible);
    }
}
