package ui;

import model.MarketingDay;
import model.Person;
import model.PeopleFile;
import model.ScheduleFile;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // Instantiate a people file
        PeopleFile peeps = new PeopleFile();
        peeps.readFile();

        // Print out what was read:
        ArrayList<Person> listofPeeps = peeps.getPeople();

        for (int i = 0; i < listofPeeps.size(); i++) {
            Person currentPerson = listofPeeps.get(i);

            System.out.println("Name: " + currentPerson.getName());
            System.out.println("Groups:" + currentPerson.getGroups());
        }

        // Instantiate a new schedule file
        ScheduleFile schedule = new ScheduleFile();
        schedule.readFile();

        //Print out what was read:
        ArrayList<MarketingDay> listofDays = schedule.getDays();

        for (int i = 0; i < listofDays.size(); i++) {
            MarketingDay currentDay = listofDays.get(i);

            System.out.println("Date: " + currentDay.getDate());
            System.out.println("Groups:" + currentDay.getGroups());
        }
    }
}
