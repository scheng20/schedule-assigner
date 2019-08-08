package tests;

import exceptions.EmptyFileException;
import exceptions.IncorrectFormatException;
import model.Group;
import model.PeopleFile;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleFileTest {

    private PeopleFile PF;

    private String goodfilePath;
    private String badFormatFilePath;
    private String emptyfilePath;

    private String savePath;

    // FOR MANUALLY CREATING THE FILES
    private FileWriter peopleFileWriter;
    private FileWriter emptyFileWriter;
    private FileWriter badFormatWriter;

    @BeforeEach
    public void setUp() {

        PF = new PeopleFile();

        try {

            // TIME TO MANUALLY INJECT FILE DATA!
            // Injecting into people input file
            peopleFileWriter = new FileWriter("generatedPeopleInputFile.txt");
            peopleFileWriter.write("Bob: UBC 2022, Arts 2022, Sauder 2023\n");
            peopleFileWriter.write("Rob: UBC 2022, Sauder 2023, BUCS\n");
            peopleFileWriter.write("Fred: UBC 2022, Sauder 2023");

            peopleFileWriter.close();

            // Injecting into empty file
            emptyFileWriter = new FileWriter("generatedEmptyFile.txt");
            emptyFileWriter.close();

            // Injecting into bad format file
            badFormatWriter = new FileWriter("generatedBadFormatFile.txt");
            badFormatWriter.write("asdfjaksdf");
            badFormatWriter.close();


        } catch (Exception e) {
            System.out.println("Something went terribly wrong and I want to cry :( ");
        }

        savePath = "generatedSave.txt";
        goodfilePath = "generatedPeopleInputFile.txt";
        emptyfilePath = "generatedEmptyFile.txt";
        badFormatFilePath = "generatedBadFormatFile.txt";

    }

    @Test
    public void testReadFileNoException() {

        try {
            PF.readFile(goodfilePath);
            // GOOD

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testReadFileThrowFileNotFoundException() {

        try {
            PF.readFile("This path doesn't exist!");
            fail();

        } catch (FileNotFoundException e) {
            // GOOD
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testReadFileThrowIncorrectFormatException() {

        try {
            PF.readFile(badFormatFilePath);
            fail();
        } catch (IncorrectFormatException e) {
            // GOOD
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testReadFileThrowEmptyFileException() {

        try {
            PF.readFile(emptyfilePath);
            fail();

        } catch (EmptyFileException e) {
            // GOOD
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSaveFileNoException() {

        try {

            PF.saveFile(savePath, "Bob: UBC 2022");
            PF.readFile(savePath);

            assertTrue(PF.getPeople().size() == 1);
            assertEquals(PF.getPeople().get(0).getName(), "Bob");

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testHandleException() {

        Exception notFound = new FileNotFoundException();
        Exception empty = new EmptyFileException();
        Exception format = new IncorrectFormatException();

        assertEquals("That file is not found! Please enter a valid location!",PF.handleException(notFound));
        assertEquals("File cannot be empty! Please add some content to the file.",PF.handleException(empty));
        assertEquals("The file's contents are formatted incorrectly!",PF.handleException(format));

    }

    @Test
    public void testProcessDetails() {

        try {
            PF.readFile(goodfilePath);

            assertEquals(PF.getPeople().size(), 3);
            assertEquals(PF.getPeople().get(0).getName(), "Bob");
            assertEquals(PF.getPeople().get(1).getName(), "Rob");
            assertEquals(PF.getPeople().get(2).getName(), "Fred");

            Group UBC_2022 = new Group("UBC 2022");
            Group ARTS_2022 = new Group("Arts 2022");
            Group SAUDER_2023 = new Group("Sauder 2023");
            Group BUCS = new Group("BUCS");

            assertEquals(3, PF.getPeople().get(0).getGroups().size());
            assertTrue(PF.getPeople().get(0).getGroups().contains(UBC_2022));
            assertTrue(PF.getPeople().get(0).getGroups().contains(ARTS_2022));
            assertTrue(PF.getPeople().get(0).getGroups().contains(SAUDER_2023));
            assertFalse(PF.getPeople().get(0).getGroups().contains(BUCS));

            assertEquals(3, PF.getPeople().get(1).getGroups().size());
            assertTrue(PF.getPeople().get(1).getGroups().contains(UBC_2022));
            assertTrue(PF.getPeople().get(1).getGroups().contains(BUCS));
            assertTrue(PF.getPeople().get(1).getGroups().contains(SAUDER_2023));
            assertFalse(PF.getPeople().get(1).getGroups().contains(ARTS_2022));

            assertEquals(2, PF.getPeople().get(2).getGroups().size());
            assertTrue(PF.getPeople().get(2).getGroups().contains(UBC_2022));
            assertTrue(PF.getPeople().get(2).getGroups().contains(SAUDER_2023));
            assertFalse(PF.getPeople().get(2).getGroups().contains(ARTS_2022));
            assertFalse(PF.getPeople().get(2).getGroups().contains(BUCS));

        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testGetSampleFormat() {

        String expectedOutput = "\nThe correct format for a people input file should be:"
                + "Name: Group1, Group2, Group3"
                + "\nFor example: "
                + "Bob: UBC 2022, Sauder 2021, BUCS";

        assertEquals(expectedOutput, PF.getSampleFormat());

    }

    @Test
    public void testGetPeople() {
        try {
            PF.readFile(goodfilePath);
            ArrayList<Person> result = PF.getPeople();

            assertEquals(3, result.size());
            assertEquals("Bob", result.get(0).getName());
            assertEquals("Rob", result.get(1).getName());
            assertEquals("Fred", result.get(2).getName());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testClearPeople() {
        try {
            PF.readFile(goodfilePath);

            assertEquals(3, PF.getPeople().size());

            PF.clearPeople();

            assertEquals(0, PF.getPeople().size());

        } catch (Exception e) {
            fail();
        }
    }
}
