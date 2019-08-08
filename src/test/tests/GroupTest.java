package tests;

import model.Group;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {

    Group target;
    Person p;
    Person nobody;

    @BeforeEach
    public void setUp() {

        target = new Group("Test Group");
        p = new Person("Bob Ross");
        nobody = new Person("Nobody");

    }

    @Test
    public void testSetPerson() {
        target.setPerson(p);

        assertEquals(p, target.getPersonResponsible());
    }

    @Test
    public void testIsAssigned() {

        assertFalse(target.isAssigned());
        target.setPerson(p);
        assertTrue(target.isAssigned());
    }

    @Test
    public void testGetName() {
        assertEquals("Test Group", target.getName());
    }

    @Test
    public void testGetPersonResponsible() {
        assertEquals(nobody, target.getPersonResponsible());

        target.setPerson(p);

        assertEquals(p, target.getPersonResponsible());
    }

    @Test
    public void testEqualsAndHashcode() {

        Group g1 = new Group ("Group Name");
        Group g2 = new Group ("Group Name");
        Group g3 = new Group ("Group Not Name");

        assertFalse(g1.equals(null));
        assertTrue(g1.equals(g1));
        assertFalse(g1.equals(p));

        assertTrue(g1.equals(g2));
        assertFalse(g2.equals(g3));

        g1.setPerson(p);

        assertFalse(g1.equals(g2));

        g2.setPerson(p);
        assertTrue(g1.equals(g2));

        assertEquals(g1.hashCode(), g2.hashCode());

    }
}
