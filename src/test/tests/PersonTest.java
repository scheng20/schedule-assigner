package tests;

import model.Group;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {


    Person p;
    Group g;
    ArrayList<Group> isInGroups;

    Group inG;

    @BeforeEach
    public void setUp() {
        p = new Person("Bob Ross");
        g = new Group ("Arts 2022");

        isInGroups = new ArrayList<>();

        inG = new Group("Tree House");
        isInGroups.add(inG);

    }

    @Test
    public void testAssignGroup() {

        p.assignGroup(g);

        assertTrue(p.hasGroupAssigned());
        assertEquals(p.getAssignedGroups().size(), 1);
        assertEquals(p.getAssignedGroups().get(0), g);

    }

    @Test
    public void testHasGroupAssigned() {

        assertFalse(p.hasGroupAssigned());

        p.assignGroup(g);

        assertTrue(p.hasGroupAssigned());
    }

    @Test
    public void testGetGroupsAsStrings() {

        p.setGroups(isInGroups);

       String result = p.getIsInGroupsString();

        assertEquals(result, "Tree House");

    }

    @Test
    public void testSetGroups() {

        p.setGroups(isInGroups);

        assertEquals(p.getGroups().size(), 1);
        assertEquals(p.getGroups().get(0), inG);

    }

    @Test
    public void testAssignedGroups() {

        p.assignGroup(g);

        assertEquals(1, p.getAssignedGroups().size());
        assertEquals(g, p.getAssignedGroups().get(0));

    }

    @Test
    public void testGetName() {
        assertEquals("Bob Ross", p.getName());
    }

    @Test
    public void testEqualsAndHashCode() {

        Person p1 = new Person("Bob");
        Person p2 = new Person("Bob");
        Person p3 = new Person("Marly");

        assertFalse(p1.equals(g));

        assertTrue(p1.equals(p1));

        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));

        p1.setGroups(isInGroups);

        assertFalse(p1.equals(p2));

        p2.setGroups(isInGroups);

        assertTrue(p1.equals(p2));

        assertEquals(p1.hashCode(), p2.hashCode());

    }

}
