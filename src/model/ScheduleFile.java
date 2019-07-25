package model;

import exceptions.IncorrectFormatException;
import tools.LineAnalyzer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleFile extends CustomFile {

    // For storing the scanned days
    private ArrayList<MarketingDay> days;

    // For analyzing the lines
    private LineAnalyzer lineReader;

    public ScheduleFile() {
        this.days = new ArrayList<MarketingDay>();
        this.lineReader = new LineAnalyzer();
    }

    public void processDetails() throws FileNotFoundException, IncorrectFormatException {

        scanner = new Scanner(file);

        try {
            while (scanner.hasNextLine()) {

                // Get the current line being read
                String currentLine = scanner.nextLine();

                // Call the FileAnalyzer's methods
                String date = lineReader.getLabel(currentLine);
                String[] groupList = lineReader.getContent(currentLine);

                // Instantiate a new day object
                MarketingDay d = new MarketingDay(date);

                // Set the day's groups
                d.setGroups(lineReader.convertToAList(groupList));

                // Add the marketing day object to the list of days
                days.add(d);
            }

        } catch (ArrayIndexOutOfBoundsException e) {

            // This array runtime error would only occur if the file wasn't formatted correctly
            throw new IncorrectFormatException();
        }

        scanner.close();
    }

    public ArrayList<MarketingDay> getDays() {
        return days;
    }

    public void printContents() {

        System.out.println("File loaded successfully! Here are its contents:");

        System.out.println("\nSchedule (unassigned): ");

        for (MarketingDay d: days) {

            System.out.println("Date: " + d.getDate());
            System.out.println("Groups:" + d.getGroups());
        }
    }

    public void getSampleFormat() {

        System.out.println("\nThe correct format for a schedule input file should be:");
        System.out.println("Date: Group1, Group2, Group3");
        System.out.println("\nFor example: ");
        System.out.println("July 2: UBC 2022, BUCS, Science 2021");
    }

}
