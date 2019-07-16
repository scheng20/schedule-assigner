package tools;

import model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PeopleFile implements  FileReader, FileSaver {

    // For opening and reading the file
    private File file;
    private Scanner scanner;

    // For writing and saving the file
    private FileWriter writer;

    // For storing the scanned people
    private ArrayList<Person> people;

    public PeopleFile() {
        this.people = new ArrayList<Person>();
    }

    @Override
    public void readFile() throws FileNotFoundException {

        file = new File("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\PeopleInput.txt");

        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String originalLine = scanner.nextLine();
            //System.out.println(originalLine);

            // Split and analyze the current line
            String[] split = originalLine.split(": ");

            // Find the person's name
            String name = split[0];
            Person p = new Person(name);

            // Find the person's groups
            String groups = split[1];
            String[] groupsList = groups.split(", ");

            // Add the groups to the person object
            for (int i = 0; i < groupsList.length; i++) {
                String currentGroup = groupsList[i];
                p.addGroup(currentGroup);
            }

            // Add the person object to the list of person objects
            people.add(p);

        }

        scanner.close();
    }

    @Override
    public void saveFile() throws IOException {

        writer = new FileWriter("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\PeopleOutput.txt");

        writer.write("This is a list of people that you can output!");
        writer.close();
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

}
