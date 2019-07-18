package tools;

import model.Readable;
import model.Saveable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Analyzes a given file of text, includes reading (loading) and writing (saving) the file
// For each line, it splits the first part from the second part and turns the second part into
// an ArrayList of type Strings.

// Requires that each line is formatted correctly
// The correct format is shown below:
// label: content1, content2, content3, content4, ...

public abstract class FileAnalyzer implements Readable, Saveable {

    // For opening and reading the file
    protected File file;

    // For writing and saving the file
    protected FileWriter writer;
    protected Scanner scanner;

    // Reading (loading) the file
    public void readFile(String path) throws FileNotFoundException {
        file = new File(path);
        processDetails();
    }

    // Writing (saving) the file
    public void saveFile(String path, String content) throws IOException {
        writer = new FileWriter(path);

        writer.write(content);
        writer.close();
    }

    // Retrieves the first part of the line
    public String getLabel(String line) {
        // Split and analyze the current line
        String[] split = line.split(": ");

        // Find the first part
        String first = split[0];

        return first;

    }

    // Retrieves the second part (list) of the line
    public String[] getContent(String line) {
        // Split and analyze the current line
        String[] split = line.split(": ");

        // Find the second part (a list)
        String uncutList = split[1];
        String[] cutList = uncutList.split(", ");

        return cutList;
    }

    // Converts second part into an ArrayList
    public ArrayList convertToAList(String[] given) {

        ArrayList<String> output = new ArrayList<String>();

        for (int i = 0; i < given.length; i++) {
            String currentString = given[i];
            output.add(currentString);
        }

        return output;
    }

    public abstract void processDetails() throws FileNotFoundException;

}
