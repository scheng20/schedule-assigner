package model;

import exceptions.IncorrectFormatException;
import tools.FileAnalyzer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PeopleFile extends CustomFile {

    // For storing the scanned people
    private ArrayList<Person> people;

    // For analyzing the lines
    private FileAnalyzer lineReader;

    // Constructor
    public PeopleFile() {
        this.people = new ArrayList<Person>();
        this.lineReader = new FileAnalyzer();
    }

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
            }

        } catch (ArrayIndexOutOfBoundsException e) {

            // This array runtime error would only occur if the file wasn't formatted correctly
            throw new IncorrectFormatException();
        }

        scanner.close();
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void printContents() {

        System.out.println("File loaded successfully! Here are its contents:");

        System.out.println("\nPeople: ");

        for (int i = 0; i < people.size(); i++) {
            Person currentPerson = people.get(i);

            System.out.println("Name: " + currentPerson.getName());
            System.out.println("Groups:" + currentPerson.getGroups());
        }
    }

    public void getSampleFormat() {

        System.out.println("\nThe correct format for a people input file should be:");
        System.out.println("Name: Group1, Group2, Group3");
        System.out.println("\nFor example: ");
        System.out.println("Bob: UBC 2022, Sauder 2021, BUCS");
    }
}
