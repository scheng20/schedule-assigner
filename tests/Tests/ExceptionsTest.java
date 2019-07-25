package Tests;

import exceptions.EmptyFileException;
import exceptions.FileException;
import exceptions.IncorrectFormatException;
import model.PeopleFile;
import model.ScheduleFile;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.TestCase.fail;

public class ExceptionsTest {

    ScheduleFile schedule;
    PeopleFile people;

    String nonExistFilePath;
    String emptyFilePath;
    String badFormatFilePath;
    String workingFilePath;

    @Before
    public void setUp() {

        schedule = new ScheduleFile();
        people = new PeopleFile();

        nonExistFilePath = "";
        emptyFilePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\"
                + "Personal Project\\project_scheng20\\storage\\testSave.txt";
        badFormatFilePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\"
                + "Personal Project\\project_scheng20\\storage\\testLoad.txt";

        workingFilePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\"
                + "Personal Project\\project_scheng20\\storage\\ScheduleInput.txt";
    }

    @Test
    public void testFileNotFoundException() {

        try {
            schedule.readFile(nonExistFilePath);
            people.readFile(nonExistFilePath);

            fail();
        } catch (FileNotFoundException e) {
            // YAY!
        } catch (FileException e) {
            fail();
        }
    }

    @Test
    public void testEmptyFileException() {

        try {
            schedule.readFile(emptyFilePath);
            people.readFile(emptyFilePath);

            fail();
        } catch (EmptyFileException e) {
            // YAY!
        } catch (FileNotFoundException e) {
            fail();
        } catch (FileException e) {
            fail();
        }
    }

    @Test
    public void testIncorrectFormatException() {

        try {
            schedule.readFile(badFormatFilePath);

            people.readFile(badFormatFilePath);

            fail();
        } catch (IncorrectFormatException e) {
            // YAY!
        } catch (FileNotFoundException e) {
            fail();
        } catch (FileException e) {
            fail();
        }
    }

    @Test
    public void testNoException() {

        try {
            schedule.readFile(workingFilePath);
            people.readFile(workingFilePath);

        } catch (FileNotFoundException e) {
            fail();
        } catch (FileException e) {
            fail();
        }
    }

}
