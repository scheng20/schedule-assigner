package tests;

import exceptions.EmptyFileException;
import exceptions.FileException;
import exceptions.IncorrectFormatException;
import model.PeopleFile;
import model.ScheduleFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionsTest {

    ScheduleFile schedule;
    PeopleFile people;

    String nonExistFilePath;
    String emptyFilePath;
    String badFormatFilePath;
    String workingFilePath;

    @BeforeEach
    public void setUp() {

        schedule = new ScheduleFile();
        people = new PeopleFile();

        nonExistFilePath = "";

        // .txt files located in tests folder
        /*
        emptyFilePath = ".\\src\\test\\tests\\justEmpty.txt";
        badFormatFilePath = ".\\src\\test\\tests\\poorFormat.txt";
        workingFilePath = ".\\src\\test\\tests\\testScheduleInput.txt";*/

        // .txt. files located in storage folder
        /*
        emptyFilePath = ".\\storage\\justEmpty.txt";
        badFormatFilePath = ".\\storage\\poorFormat.txt";
        workingFilePath = ".\\storage\\testScheduleInput.txt";
         */

        emptyFilePath = "./justEmpty.txt";
        badFormatFilePath = "./poorFormat.txt";
        workingFilePath = "./testScheduleInput.txt";

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
