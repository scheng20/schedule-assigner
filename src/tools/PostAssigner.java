package tools;

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

    ArrayList<Group> rareGroups;

    public PostAssigner(PeopleFile p, ScheduleFile s) {

        people = p.getPeople();
        schedule = s.getSchedule();
        rareGroups = new ArrayList<Group>();
    }

    public void assignPosts() {

        // TODO:
        // Algorithm that computes the assignments
        //findRareGroup();
        //assignRareGroups();

    }

    public ArrayList<Group> getAllDistinctToBeSharedGroups() {

        ArrayList<Group> allDistinctTobeSharedGroups = new ArrayList<Group>();

        for (Map.Entry<String, ArrayList<Group>> entry : schedule.entrySet()) {

            ArrayList<Group> currentDayGroups = entry.getValue();

            // Needs to be fixed here
            for (Group g: currentDayGroups) {
                if (!allDistinctTobeSharedGroups.contains(g)) {
                    allDistinctTobeSharedGroups.add(g);
                }
            }
        }

        return allDistinctTobeSharedGroups;

    }

    // rareGroups = groups that only 1 person is a part of


    // OLD

    /*
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
                            //p.assignDate(d);
                        }
                    }
                }
            }

        }
    } */

    public ArrayList<Group> getRareGroups() {
        return rareGroups;
    }

    public Map<String, ArrayList<Group>> getSchedule() {
        return schedule;
    }
}
