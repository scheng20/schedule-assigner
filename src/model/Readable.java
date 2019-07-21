package model;

import exceptions.FileException;

import java.io.FileNotFoundException;

public interface Readable {
    public void readFile(String path) throws FileNotFoundException, FileException;
}
