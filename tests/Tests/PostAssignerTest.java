package Tests;

import exceptions.FileException;
import model.Group;
import model.PeopleFile;
import model.ScheduleFile;
import org.junit.Before;
import org.junit.Test;
import tools.PostAssigner;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PostAssignerTest {

    private PeopleFile peopleInput;
    private ScheduleFile scheduleInput;

    @Before
    public void setUp() throws FileNotFoundException, FileException {
        peopleInput = new PeopleFile();
        scheduleInput = new ScheduleFile();

        peopleInput.readFile("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\project_scheng20\\storage\\testPeopleInput.txt");
        scheduleInput.readFile("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\project_scheng20\\storage\\testScheduleInput.txt");

    }

    /*
    @Test
    public void testFindRareGroup() {

        PostAssigner PA = new PostAssigner(peopleInput, scheduleInput);
        PA.findRareGroup();

        ArrayList<Group> result = PA.getRareGroups();

        Group g1 = new Group("Arts 2022");
        Group g2 = new Group ("BUCS");

        assertEquals(2, result.size());
        //assertTrue(result.contains(g1));

        assertTrue(result.contains(g1));
        assertTrue(result.contains(g2));
    }*/

    @Test
    public void testFindDistinctGroups() {

        PostAssigner PA = new PostAssigner(peopleInput, scheduleInput);

        ArrayList<Group> result = PA.getAllDistinctToBeSharedGroups();

        Group g1 = new Group("Arts 2022");
        Group g2 = new Group ("BUCS");
        Group g3 = new Group ("Sauder 2023");
        Group g4 = new Group ("UBC 2022");


        assertEquals(4, result.size());

        assertTrue(result.contains(g1));
        assertTrue(result.contains(g2));
        assertTrue(result.contains(g3));
        assertTrue(result.contains(g4));

    }

}
