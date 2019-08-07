package tests;

import exceptions.EmptyFileException;
import exceptions.IncorrectFormatException;
import model.Group;
import model.PeopleFile;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleFileTest {

    PeopleFile PF;
    String goodfilePath;
    String badFormatFilePath;
    String emptyfilePath;
    String savePath;

    @BeforeEach
    public void setUp() {

        PF = new PeopleFile();

        goodfilePath = ".\\src\\test\\tests\\testPeopleInput.txt";

        badFormatFilePath = ".\\src\\test\\tests\\poorFormat.txt";

        emptyfilePath = ".\\src\\test\\tests\\justEmpty.txt";

        savePath = ".\\src\\test\\tests\\testSave.txt";
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

        PF.handleException(notFound);
        PF.handleException(empty);
        PF.handleException(format);

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
        PF.getSampleFormat();
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
}
