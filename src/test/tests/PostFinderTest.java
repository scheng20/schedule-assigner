package tests;

import exceptions.NoPersonInGroupException;
import model.Group;
import model.PeopleFile;
import model.ScheduleFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.PostFinder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PostFinderTest {

    private PeopleFile peopleInput;
    private ScheduleFile scheduleInput;
    private Group Arts_2022;
    private Group BUCS;
    private Group Sauder_2023;
    private Group UBC_2022;
    private PostFinder PF;

    @BeforeEach
    public void setUp() {
        peopleInput = new PeopleFile();
        scheduleInput = new ScheduleFile();

        // This setup might needs to be changed in the future to avoid using local file paths
        try {
            peopleInput.readFile(".\\storage\\testPeopleInput.txt");
            scheduleInput.readFile(".\\storage\\testScheduleInput.txt");
        } catch (Exception e) {

        }

        // Instantiate the PostFinder
        PF = new PostFinder(scheduleInput.getSchedule(), peopleInput.getPeople());

        Arts_2022 = new Group("Arts 2022");
        BUCS = new Group ("BUCS");
        Sauder_2023 = new Group ("Sauder 2023");
        UBC_2022 = new Group ("UBC 2022");
    }


    @Test
    public void testFindDistinctGroups() {

        PF.findAllDistinctToBeSharedGroups();

        ArrayList<Group> result = PF.getDistinctToBeSharedGroups();

        assertEquals(4, result.size());

        assertTrue(result.contains(Arts_2022));
        assertTrue(result.contains(BUCS));
        assertTrue(result.contains(Sauder_2023));
        assertTrue(result.contains(UBC_2022));

    }

    @Test
    public void testFindAllGroupsPeopleAreApartOf() {

        PF.findAllGroupsPeopleAreApartOf();

        ArrayList<Group> result = PF.getAllGroupsPeopleAreIn();

        assertEquals(8, result.size());
        assertTrue(result.contains(Arts_2022));
        assertTrue(result.contains(BUCS));
        assertTrue(result.contains(Sauder_2023));
        assertTrue(result.contains(UBC_2022));

        int testCount = 0;

        for (Group g: result) {

            if (g.equals(UBC_2022)) {
                testCount++;
            }
        }

        assertTrue(testCount == 3);

        testCount = 0;

        for (Group g: result) {
            if (g.equals(Sauder_2023)) {
                testCount++;
            }
        }

        assertTrue(testCount == 3);

    }

    @Test
    public void testFindRareGroupNoException() {
        try {
            // These methods must be called before findRareGroups can be called
            PF.findAllDistinctToBeSharedGroups();
            PF.findAllGroupsPeopleAreApartOf();

            PF.findRareGroups();

            ArrayList<Group> result = PF.getRareGroups();

            assertEquals(2, result.size());
            assertTrue(result.contains(Arts_2022));
            assertTrue(result.contains(BUCS));
            assertFalse(result.contains(Sauder_2023));

        } catch (NoPersonInGroupException e) {
            fail();
        }

    }

    @Test
    public void testFindRareGroupThrowException() {

        Group testGroup = new Group("GROUP THAT NO ONE BELONGS TO!");
        scheduleInput.addGroupToDay("July 2", testGroup);

        try {
            // These methods must be called before findRareGroups can be called
            PF.findAllDistinctToBeSharedGroups();
            PF.findAllGroupsPeopleAreApartOf();

            PF.findRareGroups();

            fail();

        } catch (NoPersonInGroupException e) {
            // GOOD
        }

    }
}
