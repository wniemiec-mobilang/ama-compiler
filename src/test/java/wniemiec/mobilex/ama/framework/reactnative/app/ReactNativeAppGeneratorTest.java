package wniemiec.mobilex.ama.framework.reactnative.app;

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
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.util.io.FileManager;


class ReactNativeAppGeneratorTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ReactNativeAppGenerator appGenerator;
    private MockInputTerminal mockInputTerminal;
    private MockOutputTerminal mockOutputTerminal;
    private MockFileManager mockFileManager;
    private FileManager fileManager;
    private Terminal terminal;
    private Path sourceCodePath;
    private Path mobileOutput;


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
    void testAndroidGeneration() throws AppGenerationException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");
        runAppGenerator("android");
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
            "keytool",
            "-genkeypair",
            "-v",
            "-keystore",
            "myapp/foo/android/app/myapp.keystore",
            "-alias",
            "myapp",
            "-keyalg",
            "RSA",
            "-keysize",
            "2048",
            "-validity",
            "10000",
            "-dname",
            "cn=Unknown,ou=Unknown,o=Unknown,c=Unknown",
            "-storepass",
            "abcdef12",
            "-keypass",
            "abcdef12",
            "gradlew",
            "-p",
            "myapp/foo/android",
            "bundleRelease"
        );
        assertFileManagerExecuted(
            "REMOVE FILE: myapp/foo/android/app/myapp.keystore",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_STORE_FILE=myapp.keystore",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_KEY_ALIAS=myapp",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_STORE_PASSWORD=abcdef12",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_KEY_PASSWORD=abcdef12",
            "APPEND: myapp/foo/android/gradle.properties:org.gradle.jvmargs=-Xmx4608m",
            "WRITE: myapp/foo/android/app/build.gradle:// Modified by SCMA",
            "COPY: myapp/foo/android/app/build/outputs/bundle/release/app-release.aab -> myapp/bar/mobile/android/myapp.aab"
        );
    }

    @Test
    void testUnsupportedPlatform() {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");

        Assertions.assertThrows(AppGenerationException.class, () -> {
            runAppGenerator("and2roid");
        });
    }

    @Test
    void testGenerateApplicationWithoutTerminal() {
        withTerminal(null);
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runAppGenerator("android");
        });
    }

    @Test
    void testGenerateApplicationWithoutFileManager() {
        withTerminal(buildMockTerminal());
        withFileManager(null);
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runAppGenerator("android");
        });
    }

    @Test
    void testGenerateApplicationWithoutSourceCode() {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath(null);
        withMobileOutputPath("myapp/bar");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runAppGenerator("android");
        });
    }

    @Test
    void testGenerateApplicationWithoutOutput() {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runAppGenerator("android");
        });
    }

    @Test
    void testGenerateApplicationWithoutPlatform() {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runAppGenerator(null);
        });
    }

    @Test
    void testGenerateApplicationWithEmptyPlatform() {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            runAppGenerator("");
        });
    }

    @Test
    void testSetNullTerminal() throws AppGenerationException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");
        runAppGenerator("android");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            appGenerator.setTerminal(null);
        });
    }

    @Test
    void testSetNullFileManager() throws AppGenerationException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");
        runAppGenerator("android");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            appGenerator.setFileManager(null);
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
    
    private void withSourceCodePath(String location) {
        sourceCodePath = (location ==  null) ? null : Path.of(location);
    }

    private void withMobileOutputPath(String location) {
        mobileOutput = (location ==  null) ? null : Path.of(location);
    }

    private void runAppGenerator(String platform) throws AppGenerationException {
        appGenerator = new ReactNativeAppGenerator(
            sourceCodePath, 
            mobileOutput,
            terminal,
            fileManager
        );
        
        appGenerator.generateMobileApplicationFor(platform);
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
