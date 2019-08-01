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
    private Map<String, ArrayList<Group>> schedule;

    // For analyzing the lines
    private LineAnalyzer lineReader;

    // Constructs a new schedule file
    public ScheduleFile() {

        this.schedule = new HashMap<>();
        this.lineReader = new LineAnalyzer();
    }

    // MODIFIES: this
    // EFFECTS: Scans the file line by line and storing the scanned information into
    //          the schedule HashMap. Throws IncorrectFormatException if the file's
    //          contents are formatted incorrectly.
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

                // Store the scanned contents into the schedule hashMap!
                schedule.put(date, groupListInput);
            }

        } catch (ArrayIndexOutOfBoundsException e) {

            // This array runtime error would only occur if the file wasn't formatted correctly
            throw new IncorrectFormatException();
        }

        scanner.close();
    }

    // EFFECTS: Returns the schedule's groups as a list of strings
    public ArrayList<String> getGroupsAsString(ArrayList<Group> input) {

        ArrayList<String> output = new ArrayList<>();

        for (Group g: input) {
            output.add(g.getName());
        }

        return output;
    }

    // EFFECTS: prints out the contents of the schedule
    public void printContents() {

        System.out.println("File loaded successfully! Here are its contents:");

        System.out.println("\nSchedule (unassigned): ");

        for (Map.Entry<String, ArrayList<Group>> entry : schedule.entrySet()) {

            System.out.println("Date:" + entry.getKey());

            ArrayList<String> groupsAsString = getGroupsAsString(entry.getValue());

            System.out.println("Groups: " + groupsAsString);
        }

    }

    // EFFECTS: Prints out a sample format of the schedule file
    public void getSampleFormat() {

        System.out.println("\nThe correct format for a schedule input file should be:");
        System.out.println("Date: Group1, Group2, Group3");
        System.out.println("\nFor example: ");
        System.out.println("July 2: UBC 2022, BUCS, Science 2021");
    }

    // ------------------------- GETTERS AND SETTERS -------------------------

    public Map<String, ArrayList<Group>> getSchedule() {
        return schedule;
    }

}