package tools;

import model.MarketingDay;
import model.Person;

import java.util.ArrayList;

// The actual algorithm of sorting the people and assigning them to post on which day

public class PostAssigner {

    ArrayList<MarketingDay> schedule;
    ArrayList<Person> people;

    public PostAssigner(ArrayList<MarketingDay> schedule, ArrayList<Person> people) {
        this.schedule = schedule;
        this.people = people;
    }



}
