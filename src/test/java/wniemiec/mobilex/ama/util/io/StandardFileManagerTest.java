package wniemiec.mobilex.ama.util.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StandardFileManagerTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path TEMP_DIRECTORY;
    private StandardFileManager fileManager;
    private Path location;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        TEMP_DIRECTORY = Path.of(System.getProperty("java.io.tmpdir"));
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        fileManager = new StandardFileManager();
        location = null;
    }

    @AfterEach
    void cleanUp() throws IOException {
        if (location != null) {
            Files.deleteIfExists(location);
        }
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testRemoveFile() throws IOException {
        withFile(Path.of("foo.bar"));
        createFile();
        removeFile();
        assertFileDoesNotExist(Path.of("foo.bar"));
    }

    @Test
    void testCreateFile() throws IOException {
        withFile(Path.of("foo.bar"));
        createFile();
        assertFileExists(Path.of("foo.bar"));
    }

    @Test
    void testAppendFile() throws IOException {
        withFile(Path.of("foo.bar"));
        createFile();
        writeFile("this is a text inside the file");
        appendFile(" !!!");
        assertFileContent(Path.of("foo.bar"), "this is a text inside the file", " !!!");
    }

    @Test
    void testReadLines() throws IOException {
        withFile(Path.of("foo.bar"));
        createFile();
        writeFile("this is a text inside the file");
        assertFileContent(Path.of("foo.bar"), "this is a text inside the file");
    }

    @Test
    void testWrite() throws IOException {
        withFile(Path.of("foo.bar"));
        createFile();
        writeFile("this is a text inside the file");
        writeFile(" !!!");
        assertFileContent(Path.of("foo.bar"), " !!!");
    }

    @Test
    void testCopy() throws IOException {
        withFile(Path.of("foo.bar"));
        createFile();
        writeFile("this is a text inside the file");
        copyFile(Path.of("foo.bar.copy"));
        assertFileExists(Path.of("foo.bar.copy"));
        assertFileContent(Path.of("foo.bar.copy"), "this is a text inside the file");
    }

    @Test
    void testCreateDirectory() throws IOException {
        withDirectory(Path.of("foo"));
        createDirectory();
        assertDirectoryExists(Path.of("foo"));
    }

    @Test
    void testRemoveDirectory() throws IOException {
        withDirectory(Path.of("foo"));
        createDirectory();
        removeDirectory();
        assertDirectoryDoesNotExist(Path.of("foo"));
    }

    @Test
    void testCreateDirectories() throws IOException {
        withDirectories(Path.of("foo", "bar"));
        createDirectories();
        assertDirectoryExists(Path.of("foo", "bar"));
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withFile(Path path) {
        location = TEMP_DIRECTORY.resolve(path);
    }

    private void createFile() throws IOException {
        Files.deleteIfExists(location);
        fileManager.createFile(location);
    }

    private void removeFile() throws IOException {
        fileManager.removeFile(location);
    }

    private void assertFileDoesNotExist(Path path) {
        Assertions.assertFalse(Files.exists(TEMP_DIRECTORY.resolve(path)));
    }

    private void assertFileExists(Path path) {
        Assertions.assertTrue(Files.exists(TEMP_DIRECTORY.resolve(path)));
    }

    private void appendFile(String... lines) throws IOException {
        fileManager.append(location, Arrays.asList(lines));
    }

    private void assertFileContent(Path path, String... lines) throws IOException {
        List<String> fileContent = Files.readAllLines(TEMP_DIRECTORY.resolve(path));

        Assertions.assertEquals(Arrays.asList(lines), fileContent);
    }

    private void writeFile(String... lines) throws IOException {
        fileManager.write(location, Arrays.asList(lines));
    }

    private void copyFile(Path copyPath) throws IOException {
        Files.deleteIfExists(TEMP_DIRECTORY.resolve(copyPath));
        fileManager.copy(location, TEMP_DIRECTORY.resolve(copyPath));
    }

    private void withDirectory(Path path) {
        location = TEMP_DIRECTORY.resolve(path);
    }

    private void createDirectory() throws IOException {
        Files.deleteIfExists(location);
        fileManager.createDirectory(location);
    }

    private void assertDirectoryExists(Path path) {
        Assertions.assertTrue(Files.exists(TEMP_DIRECTORY.resolve(path)));
    }

    private void removeDirectory() throws IOException {
        fileManager.removeDirectory(location);
    }

    private void assertDirectoryDoesNotExist(Path path) {
        Assertions.assertFalse(Files.exists(TEMP_DIRECTORY.resolve(path)));
    }

    private void withDirectories(Path path) {
        location = TEMP_DIRECTORY.resolve(path);
    }

    private void createDirectories() throws IOException {
        fileManager.createDirectories(location);
    }
}
