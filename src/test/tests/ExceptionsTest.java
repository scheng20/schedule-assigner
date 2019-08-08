package tests;

import exceptions.EmptyFileException;
import exceptions.IncorrectFormatException;
import exceptions.NoPersonInGroupException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class ExceptionsTest {

    @Test
    public void testFileNotFoundException() {

        new FileNotFoundException();
    }

    @Test
    public void testEmptyFileException() {

        new EmptyFileException();
    }

    @Test
    public void testIncorrectFormatException() {

        new IncorrectFormatException();
    }

    @Test
    public void testNoPersonInGroupException() {

        new NoPersonInGroupException();

    }

}
