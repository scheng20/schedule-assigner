package tools;

import model.Group;

import java.util.ArrayList;

public class LineAnalyzer {

    // ------------------------------------ TOOL CLASS NOTES --------------------------------------
    // Analyzes a given file of text, includes reading (loading) and writing (saving) the file
    // For each line, it splits the first part from the second part and turns the second part into
    // an ArrayList of type Groups.

    // Requires that each line is formatted correctly
    // The correct format is shown below:
    // label: content1, content2, content3, content4, ...
    // --------------------------------------------------------------------------------------------

    // REQUIRES: Line is formatted correctly
    // EFFECTS: Retrieves the first part of given line
    public String getLabel(String line) {
        // Split and analyze the current line
        String[] split = line.split(": ");

        // Find the first part
        String first = split[0];

        return first;

    }

    // REQUIRES: Line is formatted correctly
    // EFFECTS: Retrieves the second part (group list) of the given line
    public String[] getContent(String line) {
        // Split and analyze the current line
        String[] split = line.split(": ");

        // Find the second part (a list)
        String uncutList = split[1];
        String[] cutList = uncutList.split(", ");

        return cutList;
    }

    // REQUIRES: Line is formatted correctly
    // EFFECTS: Converts given String Array into an ArrayList of Groups
    public ArrayList convertToAList(String[] given) {

        ArrayList<Group> output = new ArrayList<Group>();

        for (int i = 0; i < given.length; i++) {
            String currentString = given[i];
            Group g = new Group(currentString);
            output.add(g);
        }

        return output;
    }

}
