package ui;

import model.MarketingDay;
import model.Person;
import model.PeopleFile;
import model.ScheduleFile;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    // For testing purposes
    static String scheduleLoadPath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\ScheduleInput.txt";
    static String scheduleSavePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\ScheduleOutput.txt";

    static String peopleLoadPath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\PeopleInput.txt";

    static String peopleSavePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\PeopleOutput.txt";

    public static void main(String[] args) throws IOException {

        // Instantiate a people file
        PeopleFile peeps = new PeopleFile();
        peeps.readFile(peopleLoadPath);

        // Print out what was read:
        ArrayList<Person> listofPeeps = peeps.getPeople();

        for (int i = 0; i < listofPeeps.size(); i++) {
            Person currentPerson = listofPeeps.get(i);

            System.out.println("Name: " + currentPerson.getName());
            System.out.println("Groups:" + currentPerson.getGroups());
        }

        // Instantiate a new schedule file
        ScheduleFile schedule = new ScheduleFile();
        schedule.readFile(scheduleLoadPath);

        //Print out what was read:
        ArrayList<MarketingDay> listofDays = schedule.getDays();

        for (int i = 0; i < listofDays.size(); i++) {
            MarketingDay currentDay = listofDays.get(i);

            System.out.println("Date: " + currentDay.getDate());
            System.out.println("Groups:" + currentDay.getGroups());
        }
    }
}
