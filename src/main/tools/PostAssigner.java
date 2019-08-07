package tools;

import exceptions.NoPersonInGroupException;
import model.Group;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;

import java.util.ArrayList;
import java.util.HashMap;
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

    // For Finding the information needed to process the assignment algorithm
    PostFinder postFinder;

    // Constructs a new post assigner
    public PostAssigner(PeopleFile p, ScheduleFile s) {

        people = p.getPeople();
        schedule = s.getSchedule();

        postFinder = new PostFinder(schedule, people);

    }

    // MODIFIES: this
    // EFFECTS: Assigns each person a group that they are responsible for sharing based on the groups
    //          provided by the schedule.
    public void assignPosts() {

        // Note to self:
        // Algorithm isn't perfect but it gets the job done. More improvements can be made in the future

        postFinder.findAllDistinctToBeSharedGroups();
        postFinder.findAllGroupsPeopleAreApartOf();

        try {
            postFinder.findRareGroups();
            assignRareGroups();
            assignRestOfGroups();

        } catch (NoPersonInGroupException e) {
            System.out.println("There is a group that needs to be shared but you don't have anyone suitable to do so!");
        }

    }

    // MODIFIES: this
    // EFFECTS: Finds and assigns appropriate people to the rare groups
    public void assignRareGroups() {

        ArrayList<Group> rareGroups = postFinder.getRareGroups();

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

    // ------------------------- GETTERS AND SETTERS -------------------------

    // This setter is here to make testing easier
    public void setPostFinder(PostFinder pf) {
        this.postFinder = pf;
    }

    public Map<String, ArrayList<Group>> getSchedule() {
        return schedule;
    }

    public Map<String, String> getScheduleForTable() {

        Map<String, String> result = new HashMap<>();

        for (Map.Entry<String, ArrayList<Group>> entry : schedule.entrySet()) {

            result.put(entry.getKey(), getGroupsWithPerson(entry.getValue()));
        }

        return result;
    }

    public String getGroupsWithPerson(ArrayList<Group> input) {

        String result = "";

        for (Group g: input) {
            result += g.getName() + " (" + g.getPersonResponsible().getName() + "), ";
        }

        result = result.substring(0, result.length() - 2);

        return result;

    }
}
