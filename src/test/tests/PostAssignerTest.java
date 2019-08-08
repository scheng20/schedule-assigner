package tests;

import exceptions.FileException;
import exceptions.IncorrectFormatException;
import exceptions.NoPersonInGroupException;
import model.Group;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.PostAssigner;
import tools.PostFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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

    //File peopleInputFile;
    //File scheduleInputFile;

    FileWriter peopleFileWriter;
    FileWriter scheduleFileWriter;

    @BeforeEach
    public void setUp() {
        peopleInput = new PeopleFile();
        scheduleInput = new ScheduleFile();

        try {
            //peopleInputFile = new File("generatedPeopleInputFile.txt");
            //scheduleInputFile = new File ("generatedScheduleInputFile.txt");

            // TIME TO MANUALLY INJECT FILE DATA!
            // Injecting into people input file
            peopleFileWriter = new FileWriter("generatedPeopleInputFile.txt");
            peopleFileWriter.write("Bob: UBC 2022, Arts 2022, Sauder 2023\n");
            peopleFileWriter.write("Rob: UBC 2022, Sauder 2023, BUCS\n");
            peopleFileWriter.write("Fred: UBC 2022, Sauder 2023");

            peopleFileWriter.close();

            // Injecting into schedule input file
            scheduleFileWriter = new FileWriter("generatedScheduleInputFile.txt");
            scheduleFileWriter.write("July 2: BUCS, Sauder 2023\n");
            scheduleFileWriter.write("July 3: Arts 2022, Sauder 2023, UBC 2022");

            scheduleFileWriter.close();

            // Set the peopleInput to peopleInputFile and scheduleInput to ScheduleInputFile

            peopleInput.readFile("generatedPeopleInputFile.txt");
            scheduleInput.readFile("generatedScheduleInputFile.txt");

            /*
            peopleInput.setFile(peopleInputFile);
            scheduleInput.setFile(scheduleInputFile);*/

            peopleInput.processDetails();
            scheduleInput.processDetails();

        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (IncorrectFormatException e) {
            System.out.println("Incorrect format exception was caught");
        } catch (FileException e) {
            System.out.println("A file exception occured!");
        }

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

        // Old read file method
        /*
        try {


            peopleInput.readFile(".\\src\\test\\tests\\testPeopleInput.txt");
            scheduleInput.readFile(".\\src\\test\\tests\\testScheduleInput.txt");


            peopleInput.readFile("./testPeopleInput.txt");
            scheduleInput.readFile("./testScheduleInput.txt");

        } catch (Exception e) {

        }*/

        // Create the data needed to be injected into the assigner

        // PEOPLE DATA

        /*
        Arts_2022 = new Group("Arts 2022");
        BUCS = new Group ("BUCS");
        Sauder_2023 = new Group ("Sauder 2023");
        UBC_2022 = new Group ("UBC 2022");


        Person Bob = new Person("Bob");
        ArrayList<Group> BobGroups = new ArrayList<Group>();
        BobGroups.add(new Group ("UBC 2022"));
        BobGroups.add(new Group("Arts 2022"));
        BobGroups.add(new Group ("Sauder 2023"));
        Bob.setGroups(BobGroups);

        Person Rob = new Person("Rob");
        ArrayList<Group> RobGroups = new ArrayList<Group>();
        RobGroups.add(new Group ("UBC 2022"));
        RobGroups.add(new Group ("Sauder 2023"));
        RobGroups.add(new Group("BUCS"));
        Rob.setGroups(BobGroups);

        Person Fred = new Person("Fred");
        ArrayList<Group> FredGroups = new ArrayList<Group>();
        FredGroups.add(new Group("UBC 2022"));
        FredGroups.add(new Group ("Sauder 2023"));
        Fred.setGroups(FredGroups);

        // SCHEDULE DATA
        ArrayList<Group> July2Groups = new ArrayList<>();
        July2Groups.add(new Group ("BUCS"));
        July2Groups.add(new Group ("Sauder 2023"));

        ArrayList<Group>July3Groups = new ArrayList<>();
        July3Groups.add(new Group ("Arts 2022"));
        July3Groups.add(new Group ("Sauder 2023"));
        July3Groups.add(new Group ("UBC 2022"));

        // MANUALLY INJECT DATA INTO SCHEDULE FILE AND PEOPLE FILE
        ArrayList<Person> injectedPeople = new ArrayList<>();
        injectedPeople.add(Bob);
        injectedPeople.add(Rob);
        injectedPeople.add(Fred);

        peopleInput.setPeople(injectedPeople);

        Map<String, ArrayList<Group>> injectedSchedule = new HashMap();
        injectedSchedule.put("July 2", July2Groups);
        injectedSchedule.put("July 3", July3Groups);

        scheduleInput.setSchedule(injectedSchedule);

        // Instantiate the PostAssigner & PostFinder
        PA = new PostAssigner(peopleInput, scheduleInput);

        PF = new PostFinder(injectedSchedule, injectedPeople);
        //PF = new PostFinder(scheduleInput.getSchedule(), peopleInput.getPeople());

        // INJECT DATA INTO POST ASSIGNER
        PA.setPeople(injectedPeople);
        PA.setSchedule(injectedSchedule);

        // Establish the correct relationship for easy testing
        PA.setPostFinder(PF);

        p1 = new Person("Bob");
        */

        // TRY AGAIN LATER
        /*
        try {

            //peopleInput.readFile(".\\src\\test\\tests\\testPeopleInput.txt");
            //scheduleInput.readFile(".\\src\\test\\tests\\testScheduleInput.txt");


            peopleInput.readFile("./testPeopleInput.txt");
            scheduleInput.readFile("./testScheduleInput.txt");

        } catch (Exception e) {

        }

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
        */
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
            // TODO: fix this
            //assertTrue(p.hasGroupAssigned());
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

    @Test
    public void testGetScheduleForTable()
    {
        Map<String, String> expectedResult = new HashMap<>();

        expectedResult.put("July 2", "BUCS (Nobody), Sauder 2023 (Nobody)");
        expectedResult.put("July 3", "Arts 2022 (Nobody), Sauder 2023 (Nobody), UBC 2022 (Nobody)");

        Map<String, String> result = PA.getScheduleForTable();

        assertEquals(expectedResult, result);

    }


    @Test
    public void testGetGroupsWithPerson() {

        Group g1 = new Group("g1");
        Person p1 = new Person("p1");
        g1.setPerson(p1);

        Group g2 = new Group("g2");
        Person p2 = new Person("p2");
        g2.setPerson(p2);

        ArrayList<Group> input = new ArrayList<>();
        input.add(g1);
        input.add(g2);

        String result = PA.getGroupsWithPerson(input);

        assertEquals("g1 (p1), g2 (p2)", result);

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
