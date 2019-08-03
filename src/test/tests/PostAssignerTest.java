package tests;

import exceptions.FileException;
import exceptions.NoPersonInGroupException;
import model.Group;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;
import org.junit.Before;
import org.junit.Test;
import tools.PostAssigner;
import tools.PostFinder;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PostAssignerTest {

    private PeopleFile peopleInput;
    private ScheduleFile scheduleInput;

    private PostAssigner PA;
    private PostFinder PF;

    private Group Arts_2022;
    private Group BUCS;
    private Group Sauder_2023;
    private Group UBC_2022;
    private Person p1;

    @Before
    public void setUp() throws FileNotFoundException, FileException {
        peopleInput = new PeopleFile();
        scheduleInput = new ScheduleFile();

        // This setup might needs to be changed in the future to avoid using local file paths
        peopleInput.readFile("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\project_scheng20\\storage\\testPeopleInput.txt");
        scheduleInput.readFile("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\project_scheng20\\storage\\testScheduleInput.txt");

        // Instantiate the PostAssigner & PostFinder
        PA = new PostAssigner(peopleInput, scheduleInput);
        PF = new PostFinder(scheduleInput.getSchedule(), peopleInput.getPeople());

        // Establish the correct relationship for easy testing
        PA.setPostFinder(PF);

        Arts_2022 = new Group("Arts 2022");
        BUCS = new Group ("BUCS");
        Sauder_2023 = new Group ("Sauder 2023");
        UBC_2022 = new Group ("UBC 2022");

        p1 = new Person("Bob");
    }

    @Test
    public void testAssignGroupToPersonInSchedule() {

        PA.assignGroupToPersonInSchedule(Arts_2022, p1);

        Map<String, ArrayList<Group>> schedule = PA.getSchedule();

        searchAndVerify(schedule,null,"normal");

    }

    @Test
    public void testAssignRareGroups() {

        PF.findAllDistinctToBeSharedGroups();
        PF.findAllGroupsPeopleAreApartOf();

        try {

            PF.findRareGroups();
            PA.assignRareGroups();

            ArrayList<Group> rareGroups = PF.getRareGroups();
            Map<String, ArrayList<Group>> schedule = PA.getSchedule();

            searchAndVerify(schedule,rareGroups,"rare");

        } catch (NoPersonInGroupException e) {
            fail();
        }

    }

    @Test
    public void testAssignPostsNoException() {

        PA.assignPosts();

        for (Person p: peopleInput.getPeople()) {
            assertTrue(p.hasGroupAssigned());
        }
    }

    @Test
    public void testAssignPostsCaughtException() {

        Group testGroup = new Group("GROUP THAT NO ONE BELONGS TO!");
        scheduleInput.addGroupToDay("July 2", testGroup);

        PA.assignPosts();

        for (Person p: peopleInput.getPeople()) {
            assertFalse(p.hasGroupAssigned());
        }

    }

    // Helper method!
    public void searchAndVerify(Map<String,ArrayList<Group>> schedule, ArrayList<Group> searchedGroup, String type) {

        for (Map.Entry<String, ArrayList<Group>> entry: schedule.entrySet()) {

            ArrayList<Group> currentDayGroups = entry.getValue();

            // Changes the group list based on search type
            if (type.equalsIgnoreCase("normal")){
                searchedGroup = currentDayGroups;

            }

            for (Group g: searchedGroup) {

                // Changes the comparsion based on search type
                if (type.equalsIgnoreCase(("normal"))) {
                    g = Arts_2022;
                }

                if (currentDayGroups.contains(g)){

                    int searchIndex = currentDayGroups.indexOf(g);
                    Group target = currentDayGroups.get(searchIndex);
                    assertTrue(target.isAssigned());

                    // Changes the assertions based on search type
                    if (type.equalsIgnoreCase("normal")) {
                        assertEquals(target.getPersonResponsible(), p1);
                    }

                }
            }
        }
    }

}
