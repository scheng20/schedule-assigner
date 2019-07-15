package ui;

import tools.FileReader;
import tools.FileSaver;
import tools.PeopleFile;
import tools.ScheduleFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // For only reading the people file
        FileReader readOnlyPeeps = new PeopleFile();
        readOnlyPeeps.openFile();
        readOnlyPeeps.readFile();

        // For only writing & saving the people file
        FileSaver saveOnlyPeeps = new PeopleFile();
        saveOnlyPeeps.saveFile();

        // A schedule that does all
        ScheduleFile schedule = new ScheduleFile();
        schedule.openFile();
        schedule.readFile();
        schedule.saveFile();

    }
}
