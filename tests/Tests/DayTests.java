package Tests;

import Models.Day;
import Models.Group;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DayTests
{
    private LocalDate date;
    private Day day;
    private Group g1;
    private Group g2;
    private Group g3;
    private Group g4;

    @Before
    public void setup()
    {
        date = LocalDate.now();
        day = new Day(date, true, 3);

        g1 = new Group("TestGroup1", "Faculty1", "1", "T");
        g2 = new Group("TestGroup2", "Faculty2", "2", "T");
        g3 = new Group("TestGroup3", "Faculty3", "3", "N");
        g4 = new Group("TestGroup4", "Faculty4", "4", "N");

    }

    @Test
    public void testAddGroup()
    {
        day.addGroup(g1);
        assertTrue(day.addedGroups.contains(g1));
        assertTrue(day.sharedGroups.contains(g1.name));
        assertTrue(day.sharedFaculties.contains(g1.faculty));
        assertTrue(day.sharedYears.contains(g1.year));
        assertTrue(day.shareSlots == 2);
        assertTrue(g1.shareTimes == 1);

    }

    @Test
    public void testCanAddGroup()
    {
        day.addGroup(g1);
        day.addGroup(g1);
        day.addGroup(g1);

        assertFalse(day.canAdd(g1));
    }

    @Test
    public void testDeleteAll()
    {
        day.addGroup(g1);
        day.addGroup(g2);
        day.addGroup(g3);
        day.addGroup(g4);

        day.deleteAllGroups();

        assertEquals(0, day.sharedGroups.size());
    }
}
