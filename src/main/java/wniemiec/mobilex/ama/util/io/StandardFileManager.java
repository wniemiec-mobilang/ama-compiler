package wniemiec.mobilex.ama.util.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class StandardFileManager implements FileManager {
    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void removeFile(Path file) throws IOException {
        Files.deleteIfExists(file);
    }

    @Override
    public void append(Path file, List<String>lines) throws IOException {
        Files.write(
            file, 
            lines, 
            Charset.defaultCharset(), 
            StandardOpenOption.APPEND
        );
    }

    @Override
    public List<String> readLines(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    @Override
    public void write(Path file, List<String>lines) throws IOException {
        Files.write(
            file, 
            lines, 
            Charset.defaultCharset(), 
            StandardOpenOption.WRITE
        );
    }

    @Override
    public boolean exists(Path file) {
        return Files.exists(file);
    }

    @Override
    public void createDirectories(Path path) throws IOException {
        Files.createDirectories(path);
    }

    @Override
    public void copy(Path source, Path destination) throws IOException {
        Files.copy(source, destination);
    }

    @Override
    public void removeDirectory(Path file) throws IOException {
        FileUtils.deleteDirectory(file.toFile());
    }

    @Override
    public void createFile(Path file) throws IOException {
        Files.createFile(file);
    }

    @Override
    public void createDirectory(Path path) throws IOException {
        Files.createDirectory(path);
    }
}
