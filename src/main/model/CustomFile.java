package model;

import exceptions.EmptyFileException;
import exceptions.FileException;
import exceptions.IncorrectFormatException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;

public abstract class CustomFile extends Observable implements Readable, Saveable {

    // For opening and reading the file
    protected java.io.File file; // This is the variable that the file is stored in

    // For writing and saving the file
    protected FileWriter writer;
    protected Scanner scanner;

    // MODIFIES: this
    // EFFECTS: Opens and reads the file specified by the given path.
    //          Throws FileNotFoundException if file a file is not found in the given path.
    //          Throws FileException if any other type of file exception occurs.
    public void readFile(String path) throws FileNotFoundException, FileException {

        file = new java.io.File(path);

        if (!file.exists()) {
            throw new FileNotFoundException();
        } else if (file.length() == 0) {
            throw new EmptyFileException();
        } else {

            processDetails();
        }

    }

    // EFFECTS: Saves the given contents into a text file.
    public void saveFile(String path, String content) throws IOException {
        writer = new FileWriter(path);

        writer.write(content);
        writer.close();
    }

    // EFFECTS: Handles the different types of file exceptions and prints out corresponding messages for exceptions.
    public String handleException(Exception e) {

        String output = "";

        if (e instanceof FileNotFoundException) {

            output = "That file is not found! Please enter a valid location!";

        } else if (e instanceof EmptyFileException) {

            output = "File cannot be empty! Please add some content to the file.";

        } else if (e instanceof  IncorrectFormatException) {

            output = "The file's contents are formatted incorrectly!";
        }

        return output;
    }

    // ------------------------------ ABSTRACT METHODS -------------------------------

    public abstract void processDetails() throws FileNotFoundException, IncorrectFormatException;

    public abstract void getContents();

    public abstract String getSampleFormat();
}
