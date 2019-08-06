package tests;

import model.ScheduleFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFile {
    ScheduleFile SF;
    String goodfilePath;
    String badFormatFilePath;

    @BeforeEach
    void setUp() {

        SF = new ScheduleFile();

        goodfilePath = ".\\src\\test\\storage\\testScheduleInput.txt";

        badFormatFilePath = ".\\src\\test\\storage\\poorFormat.txt";

        // Read and get the file's contents
        try {
            SF.readFile(goodfilePath);
        } catch (Exception e) {

        }
    }

    @Test
    void testPrintContents() {

        SF.getContents();

    }
}
