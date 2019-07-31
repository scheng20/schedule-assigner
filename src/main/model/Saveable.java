package model;

import java.io.IOException;

public interface Saveable {
    public void saveFile(String path, String content) throws IOException;
}
