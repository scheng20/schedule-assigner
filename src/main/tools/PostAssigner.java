package tools;

import exceptions.NoPersonInGroupException;
import model.Group;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;

import java.util.ArrayList;
import java.util.Map;

public class PostAssigner {

    // ------------------------------------ TOOL CLASS NOTES --------------------------------------
    // Handles the actual algorithm of assigning the list of people to their appropriate groups
    // based on which groups each person is in, and whether or not this person must be responsible
    // for sharing to a certain group because they are the only person a part of that group out of
    // all the other people given.
    // --------------------------------------------------------------------------------------------

    // For storing the people and the schedule (main two inputs)
    ArrayList<Person> people;
    Map<String, ArrayList<Group>> schedule;

    // For storing the groups that are needed as a part of the process for assigning the posts
    ArrayList<Group> distinctToBeSharedGroups;
    ArrayList<Group> allGroupsPeopleAreIn;
    ArrayList<Group> rareGroups;

    // Constructs a new post assigner
    public PostAssigner(PeopleFile p, ScheduleFile s) {

        people = p.getPeople();
        schedule = s.getSchedule();

        distinctToBeSharedGroups = new ArrayList<Group>();
        allGroupsPeopleAreIn = new ArrayList<Group>();
        rareGroups = new ArrayList<Group>();


    }

    // MODIFIES: this
    // EFFECTS: Assigns each person a group that they are responsible for sharing based on the groups
    //          provided by the schedule.
    public void assignPosts() {

        // Note to self:
        // Algorithm isn't perfect but it gets the job done. More improvements can be made in the future

        findAllDistinctToBeSharedGroups();
        findAllGroupsPeopleAreApartOf();

        try {
            findRareGroups();
            assignRareGroups();
            assignRestOfGroups();
            printAssignedSchedule();

        } catch (NoPersonInGroupException e) {
            System.out.println("There is a group that needs to be shared but you don't have anyone suitable to do so!");
        }

    }

    // MODIFIES: this
    // EFFECTS: finds all of the distinct groups that exists in the sharing schedule
    public void findAllDistinctToBeSharedGroups() {

        ArrayList<Group> allDistinctTobeSharedGroups = new ArrayList<Group>();

        for (Map.Entry<String, ArrayList<Group>> entry : schedule.entrySet()) {

            ArrayList<Group> currentDayGroups = entry.getValue();

            for (Group g: currentDayGroups) {

                if (!allDistinctTobeSharedGroups.contains(g)) {
                    allDistinctTobeSharedGroups.add(g);
                }
            }
        }

        this.distinctToBeSharedGroups = allDistinctTobeSharedGroups;

    }

    // MODIFIES: this
    // EFFECTS: finds all the groups that people are a part of stacked together in a list
    public void findAllGroupsPeopleAreApartOf() {

        ArrayList<Group> allGroupsPeopleAreIn = new ArrayList<>();

        for (Person p: people) {
            allGroupsPeopleAreIn.addAll(p.getGroups());
        }

        this.allGroupsPeopleAreIn = allGroupsPeopleAreIn;

    }

    // MODIFIES: this
    // EFFECTS: finds a list of groups where only 1 person is a part of the group
    public void findRareGroups() throws NoPersonInGroupException {

        ArrayList<Group> rareGroups = new ArrayList<Group>();

        int peopleCount = 0;

        for (Group g: distinctToBeSharedGroups) {

            // compare how many instances of this group occurs in allPeopleAreInList
            for (Group gp: allGroupsPeopleAreIn) {
                if (g.equals(gp)) {
                    peopleCount++;
                }
            }

            // If this group only appears once then it is a rare group
            if (peopleCount == 1) {
                rareGroups.add(g);
            } else if (peopleCount < 1) {
                throw new NoPersonInGroupException();
            }

            // Reset the people count
            peopleCount = 0;
        }

        this.rareGroups = rareGroups;
    }

    // MODIFIES: this
    // EFFECTS: Finds and assigns appropriate people to the rare groups
    public void assignRareGroups() {

        // Directly assign the people who have the rare group to the rare group in the schedule
        for (Group rg: rareGroups) {

            for (Person p: people) {

                if (p.getGroups().contains(rg)) {

                    assignGroupToPersonInSchedule(rg, p);

                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Assigns specified person to specified group in the schedule
    public void assignGroupToPersonInSchedule(Group g, Person p) {

        for (Map.Entry<String, ArrayList<Group>> entry : schedule.entrySet()) {

            ArrayList<Group> currentDayGroups = entry.getValue();

            if (currentDayGroups.contains(g)) {
                int searchIndex = currentDayGroups.indexOf(g);
                Group target = currentDayGroups.get(searchIndex);
                target.setPerson(p);
            }

        }

    }

    // MODIFIES: this
    // EFFECTS: Loops through the schedule and finds appropriate person for each current group
    public void assignRestOfGroups() {

        for (Map.Entry<String, ArrayList<Group>> entry: schedule.entrySet()) {

            ArrayList<Group> currentDayGroup = entry.getValue();

            for (Group g: currentDayGroup) {

                if (!g.isAssigned()) {

                    for (Person p: people) {

                        if (!p.hasGroupAssigned() && p.getGroups().contains(g)) {

                            g.setPerson(p);

                            break;
                        }
                    }
                }
            }
        }
    }

    // EFFECTS: Prints the schedule and all of its assignments
    public void printAssignedSchedule() {
        for (Map.Entry<String, ArrayList<Group>> entry: schedule.entrySet()) {
            System.out.println();
            System.out.println("Date: " + entry.getKey());
            System.out.println("Groups: ");

            ArrayList<Group> currentDayGroup = entry.getValue();

            for (Group g: currentDayGroup) {
                System.out.println("Group Name: " + g.getName());
                System.out.println("Person Responsible: " + g.getPersonResponsible().getName());
                System.out.println();
            }
        }
    }

    // ------------------------- GETTERS AND SETTERS -------------------------

    public ArrayList<Group> getDistinctToBeSharedGroups() {
        return distinctToBeSharedGroups;
    }

    public ArrayList<Group> getAllGroupsPeopleAreIn() {
        return allGroupsPeopleAreIn;
    }

    public ArrayList<Group> getRareGroups() {
        return rareGroups;
    }

    public Map<String, ArrayList<Group>> getSchedule() {
        return schedule;
    }
}
