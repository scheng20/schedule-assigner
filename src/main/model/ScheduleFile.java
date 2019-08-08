package model;

import exceptions.IncorrectFormatException;
import tools.LineAnalyzer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScheduleFile extends CustomFile {

    private Map<String, ArrayList<Group>> schedule;
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

    // EFFECTS: Returns the schedule's groups in the form of a long string
    public String getGroupsAsString(ArrayList<Group> input) {

        String output = "";

        for (Group g: input) {
            output += g.getName() + ", ";
        }

        output = output.substring(0, output.length() - 2);

        return output;
    }

    // EFFECTS: Adds a group to the given day's list of groups
    // Mostly used for testing purposes

    public void addGroupToDay(String date, Group group) {

        ArrayList<Group> currentDayGroups = schedule.get(date);
        currentDayGroups.add(group);
    }

    // EFFECTS: Saves the given string map schedule into a .txt file
    public void saveFile(String path, Map<String, String> input) throws IOException {

        writer = new FileWriter(path);

        writer.write("ASSIGNED SCHEDULE\n");
        writer.write("=========================\n");

        for (Map.Entry<String, String> entry : input.entrySet()) {

            String output = "\nDate: " + entry.getKey();

            writer.write(output);

            output = "\nGroups (Person Responsible): " + entry.getValue() + "\n";

            writer.write(output);
        }

        //writer.write(content);
        writer.close();
    }

    // EFFECTS: Clears the schedule
    public void clearSchedule() {
        schedule.clear();
    }

    // ------------------------- GETTERS AND SETTERS -------------------------

    // EFFECTS: returns the read schedule
    public Map<String, ArrayList<Group>> getSchedule() {
        return schedule;
    }

    // EFFECTS: Converts the given map into a string map
    public Map<String, String> getScheduleMapString(Map<String, ArrayList<Group>> input) {

        Map<String, String> output = new HashMap<>();

        for (Map.Entry<String, ArrayList<Group>> entry : input.entrySet()) {

            String groupsAsString = getGroupsAsString(entry.getValue());

            output.put(entry.getKey(), groupsAsString);
        }

        return output;
    }

    // EFFECTS: Prints out a sample format of the schedule file
    public String getSampleFormat() {

        String output = "\nThe correct format for a schedule input file should be:"
                + "Date: Group1, Group2, Group3"
                + "\nFor example: "
                + "July 2: UBC 2022, BUCS, Science 2021";

        return output;
    }

    /*
    public void setSchedule(Map<String, ArrayList<Group>>  givenSchedule) {
        schedule = givenSchedule;
    }*/

}
