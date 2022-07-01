package util.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import wniemiec.mobilex.ama.util.io.FileManager;
import wniemiec.mobilex.ama.util.io.StandardFileManager;


public class MockFileManager implements FileManager {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> log;
    private List<String> mockedReadLines;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MockFileManager() {
        log = new ArrayList<>();
        mockedReadLines = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void removeFile(Path file) throws IOException {
        log.add("REMOVE FILE: " + file.toString());
    }

    @Override
    public void append(Path file, List<String> lines) throws IOException {
        lines.forEach(line -> log.add("APPEND: " + file + ":" + line));
    }

    @Override
    public List<String> readLines(Path file) throws IOException {
        return mockedReadLines;
    }

    @Override
    public void write(Path file, List<String> lines) throws IOException {
        lines.forEach(line -> log.add("WRITE: " + file + ":" + line));
    }

    @Override
    public boolean exists(Path file) {
        return true;
    }

    @Override
    public void createDirectories(Path path) throws IOException {
        log.add("CREATE DIRECTORIES: " + path.toString());
    }

    @Override
    public void copy(Path source, Path destination) throws IOException {
        log.add("COPY: " + source.toString() + " -> " + destination.toString());
    }

    @Override
    public void removeDirectory(Path file) throws IOException {
        log.add("REMOVE DIRECTORY: " + file.toString());
    }

    @Override
    public void createFile(Path file) throws IOException {
        log.add("CREATE FILE: " + file.toString());
    }

    @Override
    public void createDirectory(Path path) throws IOException {
        log.add("CREATE DIRECTORY: " + path.toString());
    }


    //-------------------------------------------------------------------------
    //		Getters & Setters
    //-------------------------------------------------------------------------
    public List<String> getLog() {
        return log;
    }

    public void setMockedReadLines(List<String> lines) {
        mockedReadLines = lines;
    }
}
