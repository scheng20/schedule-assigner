package tests;

import model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.LineAnalyzer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LineAnalyzerTest {

    private LineAnalyzer LA;
    private String target;

    @BeforeEach
    public void setUp() {
        LA = new LineAnalyzer();
        target = "July 2: UBC 2022, BUCS, Science 2022";
    }

    @Test
    public void testGetLabel() {

        String result = LA.getLabel(target);

        assertEquals("July 2", result);

    }

    @Test
    public void testGetContent() {

        String[] result = LA.getContent(target);
        String[] expected = new String[]{"UBC 2022", "BUCS", "Science 2022"};

        assertEquals(result.length, expected.length);

        for (int i = 0; i < result.length; i++){
            assertEquals(result[i], expected[i]);
        }
    }

    @Test
    public void testConvertToAList() {

        ArrayList<Group> result = LA.convertToAList(LA.getContent(target));

        Group g1 = new Group("UBC 2022");
        Group g2 = new Group("BUCS");
        Group g3 = new Group("Science 2022");
        Group dne = new Group("Does not exist");

        assertTrue(result.size() == 3);
        assertEquals(result.get(0), g1);
        assertEquals(result.get(1), g2);
        assertEquals(result.get(2), g3);
        assertFalse(result.contains(dne));
    }
}
