package wniemiec.mobilex.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.models.Style;
import wniemiec.mobilex.ama.models.StyleSheetRule;
import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.Literal;
import wniemiec.mobilex.ama.models.tag.Tag;
import wniemiec.mobilex.ama.util.io.FileManager;


class ReactNativeFrameworkTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Properties PRE_BUILT_PROPERTIES;
    private static final Path PRE_BUILT_LOCATION;
    private static final Path PRE_BUILT_PROJECT_LOCATION;
    private static final int INDEX_ANDROID;
    private static final int INDEX_IOS;
    private static final String TAG_ID;
    private ReactNativeFramework reactNativeFramework;
    private MockInputTerminal mockInputTerminal;
    private MockOutputTerminal mockOutputTerminal;
    private MockFileManager mockFileManager;
    private FileManager fileManager;
    private Terminal terminal;
    private List<Screen> screens;
    private List<CodeFile> obtainedCode;
    private Path sourceCodePath;
    private Path mobileOutput;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        PRE_BUILT_PROPERTIES = new Properties();
        PRE_BUILT_LOCATION = Path.of("foo", "bar");
        PRE_BUILT_PROJECT_LOCATION = PRE_BUILT_LOCATION.resolve(
            PRE_BUILT_PROPERTIES.getApplicationName()
        );
        INDEX_ANDROID = 0;
        INDEX_IOS = 1;
        TAG_ID = "tagid";
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
        reactNativeFramework = null;
        screens = new ArrayList<>();
        obtainedCode = new ArrayList<>();
        
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
        buildReactNativeFramework();
        runProjectCreator(PRE_BUILT_PROPERTIES, PRE_BUILT_LOCATION);
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
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
        assertFileManagerExecuted(
            "REMOVE FILE: " + PRE_BUILT_PROPERTIES.getApplicationName() + "/.apt_generated",
            "REMOVE FILE: " + PRE_BUILT_LOCATION.toString() + "/App.js"
        );
    }

    @Test
    void testProjectDependencies() throws IOException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        buildReactNativeFramework();
        runProjectCreator(PRE_BUILT_PROPERTIES, PRE_BUILT_LOCATION);
        addProjectDependencies(
            PRE_BUILT_PROJECT_LOCATION,
            "dependency1/somelib",
            "@somecompany/foo"
        );
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
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
        assertFileManagerExecuted(
            "REMOVE FILE: " + PRE_BUILT_PROPERTIES.getApplicationName() + "/.apt_generated",
            "REMOVE FILE: " + PRE_BUILT_LOCATION.toString() + "/App.js"
        );
    }

    @Test
    void testSingleScreen() throws CoderException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withScreen(new Screen.Builder()
            .name("about")
            .structure(buildButtonWithOnClickAndValue("click me"))
            .style(buildButtonStyleUsingBlueAndWhite())
            .behavior(buildDeclarationWithIdAndAssignment("hello", "world"))
            .build()
        );
        buildReactNativeFramework();
        runCodeGeneration();
        assertCodeFileHasName(
            "android/app/src/main/assets/about.html",
            "ios/assets/about.html"
        );
        assertAndroidCodeEquals(
            "<!DOCTYPE html>",
            "<html>",
            "    <head>",
            "        <title>about</title>",
            "            <style>",
            "                button { padding: 0; }",
            "                button {",
            "                    background-color: blue;",
            "                    color: white;",
            "                }",
            "            </style>",
            "    </head>",
            "    <body>",
            "        <button onclick=\"alert('hey!! you pressed the button!')\" id=\"" + TAG_ID + "\">",
            "            click me", 
            "        </button>",
            "    </body>",
            "    <script>",
            "\"use strict\";",
            "",
            "        var hello = \"world\";",
            "    </script>",
            "</html>"
        );
    }

    @Test
    void testAndroidGeneration() throws AppGenerationException {
        withTerminal(buildMockTerminal());
        withFileManager(buildMockFileManager());
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");
        buildReactNativeFramework();
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

    private void buildReactNativeFramework() {
        reactNativeFramework = new ReactNativeFramework(terminal, fileManager);
    }

    private void runProjectCreator(Properties properties, Path projectLocation)
    throws IOException  {
        reactNativeFramework.createProject(properties, projectLocation);
    }

    private void addProjectDependencies(Path location, String... dependencies) 
    throws IOException {
        for (String dependency : dependencies) {
            reactNativeFramework.addProjectDependency(dependency, location);
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

    private void withScreen(Screen screen) {
        screens.add(screen);
    }

    private Tag buildButtonWithOnClickAndValue(String value) {
        Tag buttonTag = Tag.getNormalInstance("button");
        
        buttonTag.addAttribute("onclick", "alert('hey!! you pressed the button!')");
        buttonTag.setValue(value);
        buttonTag.addAttribute("id", TAG_ID);
        
        return buttonTag;
    }

    private Style buildButtonStyleUsingBlueAndWhite() {
        Style style = new Style();
        StyleSheetRule rule = new StyleSheetRule();

        rule.addSelector("button");
        rule.addDeclaration("background-color", "blue");
        rule.addDeclaration("color", "white");
        style.addRule(rule);

        return style;
    }

    private Behavior buildDeclarationWithIdAndAssignment(String id, String assignment) {
        Declaration declaration = buildLiteralDeclaration(id, assignment);

        return buildBehaviorWith(declaration);
    }

    private Declaration buildLiteralDeclaration(String id, String assignment) {
        Declarator declarator = new Declarator(
            "string", 
            "let", 
            id, 
            Literal.ofString(assignment)
        );
        
        return new Declaration("let", declarator);
    }

    private Behavior buildBehaviorWith(Instruction... declarations) {
        return new Behavior(Arrays.asList(declarations));
    }

    private void runCodeGeneration() throws CoderException {
        obtainedCode = reactNativeFramework.generateCode(screens).getCodeFiles();
    }

    private void assertCodeFileHasName(String... names) {
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(names[i], obtainedCode.get(i).getName());
        }
    }

    private void assertAndroidCodeEquals(String... lines) {
        assertCodeEquals(INDEX_ANDROID, lines);
    }

    private void assertCodeEquals(int index, String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, obtainedCode.get(index).getCode());
        assertHasSameLines(expectedCode, obtainedCode.get(index).getCode());
    }

    private void assertIosCodeEquals(String... lines) {
        assertCodeEquals(INDEX_IOS, lines);
    }

    private void withSourceCodePath(String location) {
        sourceCodePath = Path.of(location);
    }

    private void withMobileOutputPath(String location) {
        mobileOutput = Path.of(location);
    }

    private void runAppGenerator(String platform) throws AppGenerationException {
        reactNativeFramework.generateMobileApplicationFor(
            platform, 
            sourceCodePath, 
            mobileOutput
        );
    }
}
