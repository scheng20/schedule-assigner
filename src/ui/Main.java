package ui;

import model.Person;
import tools.FileReader;
import tools.FileSaver;
import tools.PeopleFile;
import tools.ScheduleFile;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // Instantiate a people file for handling the people file
        PeopleFile peeps = new PeopleFile();
        peeps.readFile();

        // Print out what was read:
        ArrayList<Person> listofPeeps = peeps.getPeople();

        for (int i = 0; i < listofPeeps.size(); i++) {
            Person currentPerson = listofPeeps.get(i);

            System.out.println("Name: " + currentPerson.getName());
            System.out.println("Groups:" + currentPerson.getGroups());
        }

        // For only reading the schedule file [for deliverable]
        FileReader readOnlySchedule = new ScheduleFile();
        readOnlySchedule.readFile();

        // For only writing & saving the people file [for deliverable]
        FileSaver writeOnlySchedule = new ScheduleFile();
        writeOnlySchedule.saveFile();



    }
}
