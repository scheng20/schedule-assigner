package model;


import java.util.Objects;

public class Group {

    private String name;
    private Person personResponsible;

    public Group(String name) {
        this.name = name;
    }

    public void setPerson(Person p) {
        this.personResponsible = p;
    }

    public String getName() {
        return name;
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
