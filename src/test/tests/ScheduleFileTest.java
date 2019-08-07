package tests;

import exceptions.IncorrectFormatException;
import model.Group;
import model.ScheduleFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleFileTest {

    ScheduleFile SF;
    String goodfilePath;
    String badFormatFilePath;

    @BeforeEach
    public void setUp() {

        SF = new ScheduleFile();

        goodfilePath = ".\\src\\test\\tests\\testScheduleInput.txt";

        badFormatFilePath = ".\\src\\test\\tests\\poorFormat.txt";

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
            SF.readFile(goodfilePath);
            SF.processDetails();

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

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testAddGroupToDay() {

        try {
            SF.readFile(goodfilePath);
        } catch (Exception e) {
            fail();
        }

        Group newGroup = new Group("New Group");

        SF.addGroupToDay("July 2", newGroup);

        Map<String, ArrayList<Group>> result = SF.getSchedule();

        assertTrue(result.get("July 2").contains(newGroup));
        assertFalse(result.get("July 3").contains(newGroup));

    }

    @Test
    public void testGetGroupAsStrings() {

        try {
            SF.readFile(goodfilePath);
        } catch (Exception e) {
            fail();
        }

        ArrayList<Group> input = SF.getSchedule().get("July 2");

        String result = SF.getGroupsAsString(input);

        assertEquals("BUCS, Sauder 2023", result );

    }

    @Test
    public void testGetSampleFormat() {

        String result = "\nThe correct format for a schedule input file should be:"
                + "Date: Group1, Group2, Group3"
                + "\nFor example: "
                + "July 2: UBC 2022, BUCS, Science 2021";

        assertEquals(result, SF.getSampleFormat());
    }

    @Test
    public void testGetScheduleMapString() {

        Group g1 = new Group ("g1");
        ArrayList<Group> groupList = new ArrayList<>();
        groupList.add(g1);

        Map<String, ArrayList<Group>> input = new HashMap<>();

        input.put("July 1", groupList);

        Map<String, String> result = SF.getScheduleMapString(input);

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("July 1", "g1");

        assertEquals(expectedResult, result);

    }

    @Test
    public void testClearSchedule() {

        try {
            SF.readFile(goodfilePath);
        } catch (Exception e) {
            fail();
        }

        SF.clearSchedule();

        assertTrue(SF.getSchedule().isEmpty());
    }

    // TODO: how to test this?
    @Test
    public void testSaveFile() {

        Map<String, String> input = new HashMap<>();
        input.put("hello", "world");

        String saveFilePath = ".\\src\\test\\tests\\testSave.txt";

        try {
            SF.saveFile(saveFilePath, input);
        } catch (Exception e) {

        }


    }
}
