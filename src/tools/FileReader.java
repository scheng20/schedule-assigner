package tools;

import java.io.FileNotFoundException;

public interface FileReader {

    public void openFile();

    public void readFile() throws FileNotFoundException;
}
