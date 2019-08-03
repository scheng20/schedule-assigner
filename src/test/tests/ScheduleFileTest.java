package tests;

import model.Group;
import model.ScheduleFile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class ScheduleFileTest {

    ScheduleFile SF;
    String goodfilePath;

    @Before
    public void setUp() {
        SF = new ScheduleFile();

        goodfilePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
                + "project_scheng20\\storage\\testScheduleInput.txt";
    }

    @Test
    public void testProcessDetails() {

        try {
            SF.readFile(goodfilePath);

            Map<String, ArrayList<Group>> result = SF.getSchedule();

            assertEquals(2, result.get("July 2").size());
            assertEquals(3, result.get("July 3").size());


        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPrintContents() {
        try {
            SF.readFile(goodfilePath);
            SF.printContents();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetSampleFormat() {
        SF.getSampleFormat();
    }
}
