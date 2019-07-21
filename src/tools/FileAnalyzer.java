package tools;

import exceptions.EmptyFileException;
import exceptions.FileException;
import exceptions.IncorrectFormatException;
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
    public void readFile(String path) throws FileNotFoundException, FileException {

        file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException();
        } else if (file.length() == 0) {
            throw new EmptyFileException();
        } else {
            processDetails();
        }

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

    public void handleException(Exception e) {

        if (e instanceof FileNotFoundException) {

            System.out.println("That file is not found! Please enter a valid location:");

        } else if (e instanceof EmptyFileException) {
            System.out.println("File cannot be empty!");
            System.out.println("\nPlease add some content to the file.");
            System.out.println("Once you have done so, please re-enter the file's location: ");

        } else if (e instanceof IncorrectFormatException) {
            System.out.println("The file's contents are formatted incorrectly!");
            getSampleFormat();
            System.out.println("\nPlease reformat your file and try again!");
            System.out.println("Once you have done so, please re-enter the file's location: ");
        }
    }

    public abstract void processDetails() throws FileNotFoundException, FileException;

    public abstract void getSampleFormat();

    public abstract void printContents();
}
