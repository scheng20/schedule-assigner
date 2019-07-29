package tools;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// The actual algorithm of sorting the people and assigning them to post on which day

public class PostAssigner {

    ArrayList<MarketingDay> schedule;
    ArrayList<Person> people;
    ArrayList<Group> rareGroups;

    Map<MarketingDay, ArrayList<Person>> assignedSchedule = new HashMap<>();

    public PostAssigner(PeopleFile p, ScheduleFile s) {

        this.people = p.getPeople();
        this.schedule = s.getDays();
        rareGroups = new ArrayList<Group>();
    }

    public void assignPosts() {

        // TODO:
        // Algorithm that computes the assignments
        findRareGroup();
        assignRareGroups();

    }

    // rareGroups = groups that only 1 person is a part of
    public void findRareGroup() {

        int numOfPeopleHasGroup = 0;

        for (MarketingDay d: schedule) {

            for (Group group: d.getGroups()) {

                for (Person p: people) {

                    if (p.getGroups().contains(group)) {
                        numOfPeopleHasGroup++;
                    }

                }

                if (numOfPeopleHasGroup <= 1 && !rareGroups.contains(group)) {

                    rareGroups.add(group);
                }

                numOfPeopleHasGroup = 0;

            }

        }

    }

    public void assignRareGroups() {

        for (MarketingDay d: schedule) {

            for (Group g: rareGroups) {

                if (d.getGroups().contains(g)) {

                    for (Person p: people) {

                        if (p.getGroups().contains(g)) {
                            p.assignDate(d);
                        }
                    }
                }
            }

        }
    }

    public ArrayList<Group> getRareGroups() {
        return rareGroups;
    }

    public Map<MarketingDay, ArrayList<Person>> getResult() {
        return assignedSchedule;
    }
}
