package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PeopleFile implements  FileReader, FileSaver {

    // For opening and reading the file
    private File file;
    private Scanner scanner;

    // For writing and saving the file
    private FileWriter writer;


    @Override
    public void readFile() throws FileNotFoundException {

        file = new File("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\PeopleInput.txt");

        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
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
}
