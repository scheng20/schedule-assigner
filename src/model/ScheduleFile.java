package model;

import exceptions.IncorrectFormatException;
import tools.LineAnalyzer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScheduleFile extends CustomFile {

    // For storing the scanned schedule
    //private ArrayList<MarketingDay> days;
    private Map<String, ArrayList<Group>> schedule;

    // For analyzing the lines
    private LineAnalyzer lineReader;

    public ScheduleFile() {
        //this.days = new ArrayList<MarketingDay>();
        this.schedule = new HashMap<String,ArrayList<Group>>();
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

                // NEW:
                // Store the scanned contents into the schedule hashMap!
                schedule.put(date, lineReader.convertToAList(groupList));

                // OLD:
                // Instantiate a new day object
                //MarketingDay d = new MarketingDay(date);

                // Set the day's groups
                //d.setGroups(lineReader.convertToAList(groupList));

                // Add the marketing day object to the list of days
                //days.add(d);
            }

        } catch (ArrayIndexOutOfBoundsException e) {

            // This array runtime error would only occur if the file wasn't formatted correctly
            throw new IncorrectFormatException();
        }

        scanner.close();
    }

    /*
    public ArrayList<MarketingDay> getDays() {
        return days;
    } */

    public Map<String, ArrayList<Group>> getSchedule() {
        return schedule;
    }

    public void printContents() {

        System.out.println("File loaded successfully! Here are its contents:");

        System.out.println("\nSchedule (unassigned): ");


        for (Map.Entry<String, ArrayList<Group>> entry : schedule.entrySet()) {
            System.out.println("Date:" + entry.getKey() + "\nGroups: " + entry.getValue().toString());
        }

        // OLD:

        /*
        for (MarketingDay d: days) {

            System.out.println("Date: " + d.getDate());
            System.out.println("Groups:" + d.getGroupsAsStrings());
        }*/
    }

    public void getSampleFormat() {

        System.out.println("\nThe correct format for a schedule input file should be:");
        System.out.println("Date: Group1, Group2, Group3");
        System.out.println("\nFor example: ");
        System.out.println("July 2: UBC 2022, BUCS, Science 2021");
    }

}
