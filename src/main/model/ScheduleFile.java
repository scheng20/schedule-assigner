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

        this.schedule = new HashMap<>();
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
                ArrayList<Group> groupListInput = lineReader.convertToAList(groupList);

                // NEW:
                // Store the scanned contents into the schedule hashMap!
                schedule.put(date, groupListInput);
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

            System.out.println("Date:" + entry.getKey());

            ArrayList<String> groupsAsString = getGroupsAsString(entry.getValue());

            System.out.println("Groups: " + groupsAsString);
        }

    }

    public ArrayList<String> getGroupsAsString(ArrayList<Group> input) {

        ArrayList<String> output = new ArrayList<>();

        for (Group g: input) {
            output.add(g.getName());
        }

        return output;
    }

    public void getSampleFormat() {

        System.out.println("\nThe correct format for a schedule input file should be:");
        System.out.println("Date: Group1, Group2, Group3");
        System.out.println("\nFor example: ");
        System.out.println("July 2: UBC 2022, BUCS, Science 2021");
    }

}
