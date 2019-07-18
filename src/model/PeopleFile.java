package model;

import tools.FileAnalyzer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PeopleFile extends FileAnalyzer {

    // For storing the scanned people
    private ArrayList<Person> people;

    // Constructor
    public PeopleFile() {
        this.people = new ArrayList<Person>();
    }

    public void processDetails() throws FileNotFoundException {
        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {

            // Get the current line being read
            String currentLine = scanner.nextLine();

            // Call the FileAnalyzer's methods
            String name = getLabel(currentLine);
            String[] groupList = getContent(currentLine);

            // Instantiate a new person object
            Person p = new Person(name);

            // Set the person object's groups
            p.setGroups(convertToAList(groupList));

            // Add the person object to the list of people
            people.add(p);
        }

        scanner.close();
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

}
