package model;

import java.util.Objects;

public class Group {

    private String name;
    private Person personResponsible;
    private boolean isAssigned;

    public Group(String name) {

        this.name = name;
        this.isAssigned = false;
        this.personResponsible = null;
    }

    // FOR WEEK 8 DELIVERABLE
    public void setPerson(Person p) {

        if (!personResponsible.equals(p)) {
            personResponsible = p;
            p.assignGroup(this);
            isAssigned = true;
        }
    }

    public String getName() {
        return name;
    }

    public Person getPersonResponsible() {
        return personResponsible;
    }

    public Boolean getIsAssigned() {
        return isAssigned;
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
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
