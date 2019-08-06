package tests;

import exceptions.IncorrectFormatException;
import model.Group;
import model.ScheduleFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleFileTest {

    ScheduleFile SF;
    String goodfilePath;
    String badFormatFilePath;

    @BeforeEach
    public void setUp() {

        SF = new ScheduleFile();

        goodfilePath = ".\\src\\test\\storage\\testScheduleInput.txt";

        badFormatFilePath = ".\\src\\test\\storage\\poorFormat.txt";

        // Read and get the file's contents
        try {
            SF.readFile(goodfilePath);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testReadFileThrowIncorrectFormatException() {

        try {
            SF.readFile(badFormatFilePath);
            fail();
        } catch (IncorrectFormatException e) {
            // GOOD
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testProcessDetails() {

        try {
            SF.processDetails();
        } catch (Exception e) {
            fail();
        }

        Map<String, ArrayList<Group>> result = SF.getSchedule();

        Group UBC_2022 = new Group("UBC 2022");
        Group ARTS_2022 = new Group("Arts 2022");
        Group SAUDER_2023 = new Group("Sauder 2023");
        Group BUCS = new Group("BUCS");

        assertEquals(2, result.get("July 2").size());
        assertEquals(3, result.get("July 3").size());

        assertTrue(result.get("July 2").contains(BUCS));
        assertTrue(result.get("July 2").contains(SAUDER_2023));
        assertFalse(result.get("July 2").contains(ARTS_2022));

        assertTrue(result.get("July 3").contains(ARTS_2022));
        assertTrue(result.get("July 3").contains(SAUDER_2023));
        assertTrue(result.get("July 3").contains(UBC_2022));
        assertFalse(result.get("July 3").contains(BUCS));

    }

    @Test
    public void testAddGroupToDay() {

        Group newGroup = new Group("New Group");

        SF.addGroupToDay("July 2", newGroup);

        Map<String, ArrayList<Group>> result = SF.getSchedule();

        assertTrue(result.get("July 2").contains(newGroup));
        assertFalse(result.get("July 3").contains(newGroup));

    }

    @Test
    public void testGetGroupAsStrings() {

        ArrayList<Group> input = SF.getSchedule().get("July 2");

        ArrayList<String> result = SF.getGroupsAsString(input);

        assertEquals(2, result.size());
        assertEquals("BUCS", result.get(0));
        assertEquals("Sauder 2023", result.get(1));

    }

    @Test
    public void testPrintContents() {

        SF.getContents();

    }

    @Test
    public void testGetSampleFormat() {

        SF.getSampleFormat();
    }
}
