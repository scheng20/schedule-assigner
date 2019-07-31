package tools;

import exceptions.NoPersonInGroupException;
import model.Group;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;

import java.util.ArrayList;
import java.util.Map;

// The actual algorithm of sorting the people and assigning them to post on which day

public class PostAssigner {

    ArrayList<Person> people;

    Map<String, ArrayList<Group>> schedule;

    ArrayList<Group> distinctToBeSharedGroups;
    ArrayList<Group> allGroupsPeopleAreIn;
    ArrayList<Group> rareGroups;

    public PostAssigner(PeopleFile p, ScheduleFile s) {

        people = p.getPeople();
        schedule = s.getSchedule();
        rareGroups = new ArrayList<Group>();
    }

    public void assignPosts() {

        // THE CURRENT ALGORITHM ISN'T PERFECT BUT IT GETS THE JOB DONE SOMEWHAT

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

    // EFFECTS: finds all the groups that people are a part of stacked together in a list
    public void findAllGroupsPeopleAreApartOf() {

        ArrayList<Group> allGroupsPeopleAreIn = new ArrayList<>();

        for (Person p: people) {
            allGroupsPeopleAreIn.addAll(p.getGroups());
        }

        this.allGroupsPeopleAreIn = allGroupsPeopleAreIn;

    }

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
