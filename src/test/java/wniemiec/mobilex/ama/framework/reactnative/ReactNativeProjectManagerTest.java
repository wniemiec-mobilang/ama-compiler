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
import wniemiec.mobilex.ama.framework.reactnative.ReactNativeProjectManager;
import wniemiec.mobilex.ama.models.Properties;


class ReactNativeProjectManagerTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ReactNativeProjectManager projectManager;
    private MockInputTerminal mockInputTerminal;
    private MockOutputTerminal mockOutputTerminal;
    private MockFileManager mockFileManager;
    private Terminal terminal;
    private Properties properties;
    private Path projectLocation;
    private String keystorePassword;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        mockInputTerminal = new MockInputTerminal();
        mockOutputTerminal = new MockOutputTerminal();
        terminal = new Terminal(mockInputTerminal, mockOutputTerminal);
        properties = new Properties();
        mockFileManager = new MockFileManager();
        projectManager = new ReactNativeProjectManager(terminal, mockFileManager);
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
        withAppName("myapp");
        withTargetPlatforms("android");
        withLocation("myapp/bar");
        withKeystorePassword("abcdef12");
        runProjectCreator();
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
            "react-native", 
            "init", 
            "myapp",
            "mv", 
            "myapp", 
            "bar",
            "mv",
            "bar",
            "myapp"
        );
        assertFileManagerExecuted(
            "REMOVE FILE: myapp/.apt_generated",
            "REMOVE FILE: myapp/bar/App.js"
        );
    }

    @Test
    void testProjectDependencies() throws IOException {
        withAppName("myapp");
        withTargetPlatforms("android");
        withLocation("myapp/bar");
        withKeystorePassword("abcdef12");
        runProjectCreator();
        addProjectDependencies(
            "dependency1/somelib",
            "@somecompany/foo"
        );
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
            "react-native", 
            "init", 
            "myapp",
            "mv", 
            "myapp", 
            "bar",
            "mv",
            "bar",
            "myapp",
            "npm", 
            "install", 
            "--prefix",
            "myapp/bar",
            "--save", 
            "dependency1/somelib",
            "npm", 
            "install", 
            "--prefix",
            "myapp/bar",
            "--save", 
            "@somecompany/foo"
        );
        assertFileManagerExecuted(
            "REMOVE FILE: myapp/.apt_generated",
            "REMOVE FILE: myapp/bar/App.js"
        );
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withAppName(String name) {
        properties.setApplicationName(name);
    }

    private void withTargetPlatforms(String... platforms) {
        for (String platform : platforms) {
            properties.addPlatform(platform);
        }
    }

    private void withLocation(String location) {
        projectLocation = Path.of(location);
    }

    private void withKeystorePassword(String password) {
        keystorePassword = password;
    }

    private void runProjectCreator() throws IOException  {
        projectManager.createProject(properties, projectLocation);
    }

    private void addProjectDependencies(String... dependencies) throws IOException {
        for (String dependency : dependencies) {
            projectManager.addProjectDependency(dependency, projectLocation);
        }
    }

    private void assertTerminalHistoryIsEmpty() {
        Assertions.assertTrue(terminal.getHistory().isEmpty());
    }

    private void assertTerminalErrorHistoryIsEmpty() {
        Assertions.assertTrue(terminal.getErrorHistory().isEmpty());
    }

    private void assertInputTerminalWas(String... actions) {
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

    private void assertFileManagerExecuted(String... actions) {
        assertCodeEquals(mockFileManager.getLog(), actions);
    }
}
