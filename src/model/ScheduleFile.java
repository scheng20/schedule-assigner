package model;

import tools.FileAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleFile extends FileAnalyzer {

    // For storing the scanned days
    private ArrayList<MarketingDay> days;

    public ScheduleFile() {
        this.days = new ArrayList<MarketingDay>();
    }

    @Override
    public void readFile() throws FileNotFoundException {

        file = new File("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\ScheduleInput.txt");

        processDetails();

    }

    @Override
    public void saveFile() throws IOException {

        writer = new FileWriter("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\ScheduleOutput.txt");

        writer.write("This will store the outputted schedule! TEST");
        writer.close();
    }

    public void processDetails() throws FileNotFoundException {

        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {

            // Get the current line being read
            String currentLine = scanner.nextLine();

            // Call the FileAnalyzer's methods
            String date = getLabel(currentLine);
            String[] groupList = getContent(currentLine);

            // Instantiate a new day object
            MarketingDay d = new MarketingDay(date);

            // Set the day's groups
            d.setGroups(convertToAList(groupList));

            // Add the marketing day object to the list of days
            days.add(d);
        }

        scanner.close();
    }

    public ArrayList<MarketingDay> getDays() {
        return days;
    }

}
