package tools;

import model.Group;

import java.util.ArrayList;

// Analyzes a given file of text, includes reading (loading) and writing (saving) the file
// For each line, it splits the first part from the second part and turns the second part into
// an ArrayList of type Strings.

// Requires that each line is formatted correctly
// The correct format is shown below:
// label: content1, content2, content3, content4, ...

public class LineAnalyzer {

    // Retrieves the first part of the line
    public String getLabel(String line) {
        // Split and analyze the current line
        String[] split = line.split(": ");

        // Find the first part
        String first = split[0];

        return first;

    }

    // Retrieves the second part (group list) of the line
    public String[] getContent(String line) {
        // Split and analyze the current line
        String[] split = line.split(": ");

        // Find the second part (a list)
        String uncutList = split[1];
        String[] cutList = uncutList.split(", ");

        return cutList;
    }

    // Converts second part into an ArrayList (In terms of a Group)
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
