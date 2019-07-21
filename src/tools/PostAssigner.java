package tools;

import model.MarketingDay;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;

import java.util.ArrayList;

// The actual algorithm of sorting the people and assigning them to post on which day

public class PostAssigner {

    ArrayList<MarketingDay> schedule;
    ArrayList<Person> people;

    public PostAssigner(PeopleFile p, ScheduleFile s) {

        this.people = p.getPeople();
        this.schedule = s.getDays();
    }

}
