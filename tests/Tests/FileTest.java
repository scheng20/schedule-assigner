package Tests;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class FileTest
{
    // For opening and reading the file
    private File fileLoad;
    private File fileSave;
    private Scanner scanner;

    // For writing and saving the file
    private FileWriter writer;

    @Before
    public void setup() throws IOException {

        // Setup Files
        fileLoad = new File("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\testLoad.txt");

        fileSave = new File("D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\testSave.txt");

    }

    @Test
    public void testReadFile() throws FileNotFoundException {

        scanner = new Scanner(fileLoad);

        assertEquals("This file's contents loaded successfully!",scanner.nextLine());
    }

    @Test
    public void testSaveFile() throws IOException {

        // Setup writer
        writer = new FileWriter(fileSave);

        writer.write("Please save this content!");

        scanner = new Scanner(fileSave);

        assertEquals("Please save this content!", scanner.nextLine());

    }

}
