package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

public class TestAutoBot {

    FileWriter fileWriter;

    @BeforeEach
    public void setup() {

        try {
            fileWriter = new FileWriter("testFile.txt");
            fileWriter.write("Hello World");
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Exception has been caught");
        }
    }

    @Test
    public void runTest(){

    }
}
