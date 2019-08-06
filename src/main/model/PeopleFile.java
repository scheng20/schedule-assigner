package model;

import exceptions.IncorrectFormatException;
import observer.PeopleTracker;
import tools.LineAnalyzer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PeopleFile extends CustomFile {

    // For storing the scanned people
    private ArrayList<Person> people;

    // For analyzing the lines
    private LineAnalyzer lineReader;

    // For retrieving the statistics
    private PeopleTracker peopleTracker;

    // Constructs a new people file
    public PeopleFile() {
        this.people = new ArrayList<Person>();
        this.lineReader = new LineAnalyzer();
        this.peopleTracker = new PeopleTracker();

        addObserver(peopleTracker);
    }

    // MODIFIES: this
    // EFFECTS: Scans the file line by line and storing the scanned information into
    //          different person objects. Throws IncorrectFormatException if the file's
    //          contents are formatted incorrectly.
    public void processDetails() throws FileNotFoundException, IncorrectFormatException {

        scanner = new Scanner(file);

        try {
            while (scanner.hasNextLine()) {

                // Get the current line being read
                String currentLine = scanner.nextLine();

                // Call the lineReader's methods
                String name = lineReader.getLabel(currentLine);
                String[] groupList = lineReader.getContent(currentLine);

                // Instantiate a new person object
                Person p = new Person(name);

                // Set the person object's groups
                p.setGroups(lineReader.convertToAList(groupList));

                // Add the person object to the list of people
                people.add(p);

                // Trigger the observation and keep track of how many people have been added
                setChanged();
                notifyObservers(p);
            }

        } catch (ArrayIndexOutOfBoundsException e) {

            // This array runtime error would only occur if the file wasn't formatted correctly
            throw new IncorrectFormatException();
        }

        scanner.close();
    }

    // EFFECTS: prints out the contents of the people file
    public void printContents() {

        System.out.println("File loaded successfully! Here are its contents: ");

        System.out.println("\nPeople: ");
        System.out.println("-----------");

        for (Person p: people) {
            System.out.println("Name: " + p.getName());
            System.out.println("Groups:" + p.getGroupsAsStrings());
            System.out.println("-----------");
        }
    }

    // EFFECTS: Prints out a sample format of the people file
    public void getSampleFormat() {

        System.out.println("\nThe correct format for a people input file should be:");
        System.out.println("Name: Group1, Group2, Group3");
        System.out.println("\nFor example: ");
        System.out.println("Bob: UBC 2022, Sauder 2021, BUCS");
    }

    public void printStats() {

        System.out.println("The total number of people is: " + peopleTracker.getTotalPeople() + " people");

    }

    // ------------------------- GETTERS AND SETTERS -------------------------

    public ArrayList<Person> getPeople() {
        return people;
    }
}
