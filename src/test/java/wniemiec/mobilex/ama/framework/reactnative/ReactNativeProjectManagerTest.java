package wniemiec.mobilex.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.io.MockFileManager;
import util.terminal.MockInputTerminal;
import util.terminal.MockOutputTerminal;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.LogLevel;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.util.io.FileManager;


class ReactNativeProjectManagerTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Properties PRE_BUILT_PROPERTIES;
    private static final Path PRE_BUILT_LOCATION;
    private static final Path PRE_BUILT_PROJECT_LOCATION;
    private ReactNativeProjectManager projectManager;
    private MockInputTerminal mockInputTerminal;
    private MockOutputTerminal mockOutputTerminal;
    private MockFileManager mockFileManager;
    private FileManager fileManager;
    private Terminal terminal;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        PRE_BUILT_PROPERTIES = new Properties();
        PRE_BUILT_LOCATION = Path.of("foo", "bar");
        PRE_BUILT_PROJECT_LOCATION = PRE_BUILT_LOCATION.resolve(
            PRE_BUILT_PROPERTIES.getApplicationName()
        );
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        mockInputTerminal = null;
        mockOutputTerminal = null;
        terminal = null;
        mockFileManager = null;
        fileManager = null;
        projectManager = null;
        Consolex.setLoggerLevel(LogLevel.OFF);
    }

    @AfterEach
    void cleanUp() {
        Consolex.setLoggerLevel(LogLevel.INFO);
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testProjectCreator() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        buildProjectManager();
        runProjectCreator(PRE_BUILT_PROPERTIES, PRE_BUILT_LOCATION);
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertMockInputTerminalWas(
            "react-native", 
            "init", 
            PRE_BUILT_PROPERTIES.getApplicationName(),
            "mv", 
            PRE_BUILT_PROPERTIES.getApplicationName(), 
            PRE_BUILT_LOCATION.getFileName().toString(),
            "mv",
            PRE_BUILT_LOCATION.getFileName().toString(),
            PRE_BUILT_LOCATION.getParent().toString()
        );
        assertMockFileManagerExecuted(
            "REMOVE FILE: " + PRE_BUILT_PROPERTIES.getApplicationName() + "/.apt_generated",
            "REMOVE FILE: " + PRE_BUILT_LOCATION.toString() + "/App.js"
        );
    }

    @Test
    void testProjectDependencies() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        buildProjectManager();
        runProjectCreator(PRE_BUILT_PROPERTIES, PRE_BUILT_LOCATION);
        addProjectDependencies(
            PRE_BUILT_PROJECT_LOCATION,
            "dependency1/somelib",
            "@somecompany/foo"
        );
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertMockInputTerminalWas(
            "react-native", 
            "init", 
            PRE_BUILT_PROPERTIES.getApplicationName(),
            "mv", 
            PRE_BUILT_PROPERTIES.getApplicationName(), 
            PRE_BUILT_LOCATION.getFileName().toString(),
            "mv",
            PRE_BUILT_LOCATION.getFileName().toString(),
            PRE_BUILT_LOCATION.getParent().toString(),
            "npm", 
            "install", 
            "--prefix",
            PRE_BUILT_PROJECT_LOCATION.toString(),
            "--save", 
            "dependency1/somelib",
            "npm", 
            "install", 
            "--prefix",
            PRE_BUILT_PROJECT_LOCATION.toString(),
            "--save", 
            "@somecompany/foo"
        );
        assertMockFileManagerExecuted(
            "REMOVE FILE: " + PRE_BUILT_PROPERTIES.getApplicationName() + "/.apt_generated",
            "REMOVE FILE: " + PRE_BUILT_LOCATION.toString() + "/App.js"
        );
    }

    @Test
    void testProjectCreatorWithoutTerminal() throws IOException {
        withTerminal(null);
        withFileManager(buildMockFileManager());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            buildProjectManager();
        });
    }

    @Test
    void testProjectCreatorWithoutFileManager() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            buildProjectManager();
        });
    }

    @Test
    void testProjectCreatorWithoutProperties() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        buildProjectManager();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runProjectCreator(null, PRE_BUILT_LOCATION);
        });
    }

    @Test
    void testProjectCreatorWithoutLocation() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        buildProjectManager();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runProjectCreator(PRE_BUILT_PROPERTIES, null);
        });
    }

    @Test
    void testProjectDependenciesWithoutProjectLocation() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        buildProjectManager();
        runProjectCreator(PRE_BUILT_PROPERTIES, PRE_BUILT_LOCATION);
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            addProjectDependencies(
                null,
                "dependency1/somelib",
                "@somecompany/foo"
            );
        });
    }

    @Test
    void testProjectDependenciesWithoutProjectDependencies() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        buildProjectManager();
        runProjectCreator(PRE_BUILT_PROPERTIES, PRE_BUILT_LOCATION);
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            addProjectDependencies(
                PRE_BUILT_PROJECT_LOCATION,
                (String) null
            );
        });
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private FileManager buildMockFileManager() {
        mockFileManager = new MockFileManager();

        return mockFileManager;
    }

    private void withFileManager(FileManager manager) {
        fileManager = manager;
    }

    private Terminal buildMockTerminal() {
        mockInputTerminal = new MockInputTerminal();
        mockOutputTerminal = new MockOutputTerminal();
        
        return new Terminal(mockInputTerminal, mockOutputTerminal);
    }

    private void withTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    private void buildProjectManager() {
        projectManager = new ReactNativeProjectManager(terminal, fileManager);
    }

    private void runProjectCreator(Properties properties, Path projectLocation)
    throws IOException  {
        projectManager.createProject(properties, projectLocation);
    }

    private void addProjectDependencies(Path location, String... dependencies) 
    throws IOException {
        for (String dependency : dependencies) {
            projectManager.addProjectDependency(dependency, location);
        }
    }

    private void assertTerminalHistoryIsEmpty() {
        Assertions.assertTrue(terminal.getHistory().isEmpty());
    }

    private void assertTerminalErrorHistoryIsEmpty() {
        Assertions.assertTrue(terminal.getErrorHistory().isEmpty());
    }

    private void assertMockInputTerminalWas(String... actions) {
        assertCodeEquals(mockInputTerminal.getLog(), actions);
    }

    private void assertCodeEquals(List<String> obtained, String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, obtained);
        assertHasSameLines(expectedCode, obtained);
    }

    private void assertHasSameSize(List<String> expected, List<String> obtained) {
        Assertions.assertEquals(expected.size(), obtained.size());
    }

    private void assertHasSameLines(List<String> expected, List<String> obtained) {
        for (int i = 0; i < expected.size(); i++) {            
            assertHasSameLine(expected.get(i), obtained.get(i));
        }
    }

    private void assertHasSameLine(String expected, String obtained) {
        Assertions.assertEquals(
            removeWhiteSpaces(expected),
            removeWhiteSpaces(obtained)
        );
    }

    private String removeWhiteSpaces(String text) {
        return text.replaceAll("[\\s\\t]+", "");
    }

    private void assertMockFileManagerExecuted(String... actions) {
        assertCodeEquals(mockFileManager.getLog(), actions);
    }
}
