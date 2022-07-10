package wniemiec.mobilex.ama.util.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


/**
 * Responsible for file manipulation.
 */
public interface FileManager {
    
    void createFile(Path file) throws IOException;
    void removeFile(Path file) throws IOException;
    void append(Path file, List<String> lines) throws IOException;
    List<String> readLines(Path file) throws IOException;
    void write(Path file, List<String> lines) throws IOException;
    boolean exists(Path file);
    void createDirectory(Path path) throws IOException;
    void createDirectories(Path path) throws IOException;
    void copy(Path source, Path destination) throws IOException;
    void removeDirectory(Path file) throws IOException;
}
