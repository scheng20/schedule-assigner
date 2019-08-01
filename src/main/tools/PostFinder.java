package tools;

import exceptions.NoPersonInGroupException;
import model.Group;
import model.Person;

import java.util.ArrayList;
import java.util.Map;

public class PostFinder {

    // ------------------------------------ TOOL CLASS NOTES --------------------------------------
    // Handles the finding all of the information needed to process the assignment algorithm in
    // the PostAssigner class.
    // --------------------------------------------------------------------------------------------

    ArrayList<Person> people;
    Map<String, ArrayList<Group>> schedule;

    // For storing the groups that are needed as a part of the process for assigning the posts
    ArrayList<Group> distinctToBeSharedGroups;
    ArrayList<Group> allGroupsPeopleAreIn;
    ArrayList<Group> rareGroups;

    // Constructs a PostFinder
    public PostFinder(Map<String, ArrayList<Group>> givenSchedule, ArrayList<Person> givenPeople) {

        schedule = givenSchedule;
        people = givenPeople;

        distinctToBeSharedGroups = new ArrayList<>();
        allGroupsPeopleAreIn = new ArrayList<>();
        rareGroups = new ArrayList<>();
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
    // EFFECTS: finds a list of groups where only 1 person is a part of the group;
    //          If there is a group where nobody in the list of people belong to
    //          throw NoPersonInGroupException.
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
}
