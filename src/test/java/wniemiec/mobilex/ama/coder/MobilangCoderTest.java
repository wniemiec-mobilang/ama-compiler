package wniemiec.mobilex.ama.coder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Project;
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


class MobilangCoderTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<Screen> screens;
    private MockFramework framework;
    private Properties properties;
    private Path location;
    private List<String> dependencies;
    private String screenName;
    private Tag screenStructure;
    private Style screenStyle;
    private Behavior screenBehavior;
    private Project obtainedProject;
    private List<CodeFile> codeFiles;
    private String platform;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        screens = new ArrayList<>();
        framework = new MockFramework();
        properties = new Properties();
        location = null;
        dependencies = new ArrayList<>();
        screenName = null;
        screenStructure = null;
        screenStyle = null;
        screenBehavior = null;
        obtainedProject = null;
        codeFiles = new ArrayList<>();
        platform = null;
    }

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testCreateProject() throws IOException {
        withApplicationName("hello-world");
        withLocation(Path.of("tmp"));
        runFrameworkCreateProject();
        assertProjectWasCreated();
    }

    @Test
    void testAddDependency() throws IOException {
        withDependency("foo/bar");
        withLocation(Path.of("tmp"));
        runFrameworkAddDependency();
        assertDependenciesAreCorrect();
    }

    @Test
    void testGenerateCode() throws CoderException {
        withScreenName("SomeFunnyScreenName");
        withScreenStructure(buildPInsideADivWithValue("some text"));
        withScreenStyle(buildPStyleUsingBlueAndWhite());
        withScreenBehavior(buildDeclarationWithIdAndAssignment("pi", "3.1416"));
        buildScreen();
        runFrameworkGenerateCode();
        assertHasCorrectCodeFiles();
    }

    @Test
    void testGenerateMobileApplicationFor() throws AppGenerationException {
        withPlatform("android");
        withLocation(Path.of("tmp"));
        runFrameworkGenerateMobileApplicationFor();
        assertGeneratedMobileApplicationForIsCorrect();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withApplicationName(String name) {
        properties.setApplicationName(name);
    }

    private void withLocation(Path path) {
        location = path;
    }

    private void runFrameworkCreateProject() throws IOException {
        framework.createProject(properties, location);
    }

    private void assertProjectWasCreated() {
        Assertions.assertTrue(framework.isCreated());
    }

    private void withDependency(String dependency) {
        dependencies.add(dependency);
    }

    private void runFrameworkAddDependency() throws IOException {
        framework.addProjectDependency(getLastDependencyAdded(), location);
    }

    private String getLastDependencyAdded() {
        if (dependencies.isEmpty()) {
            return null;
        }

        return dependencies.get(dependencies.size()-1);
    }

    private void assertDependenciesAreCorrect() {
        Assertions.assertTrue(framework.getDependencies().containsAll(dependencies));
    }

    private void withScreenName(String name) {
        this.screenName = name;
    }

    private Tag buildPInsideADivWithValue(String value) {
        Tag divTag = Tag.getNormalInstance("div");
        Tag pTag = Tag.getNormalInstance("p");

        divTag.addChild(pTag);
        pTag.setValue(value);

        return divTag;
    }

    private void withScreenStructure(Tag structure) {
        screenStructure = structure;
    }

    private Style buildPStyleUsingBlueAndWhite() {
        Style style = new Style();
        StyleSheetRule rule = new StyleSheetRule();

        rule.addSelector("p");
        rule.addDeclaration("background-color", "blue");
        rule.addDeclaration("color", "white");
        style.addRule(rule);

        return style;
    }

    private void withScreenStyle(Style style) {
        screenStyle = style;
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

    private void withScreenBehavior(Behavior behavior) {
        screenBehavior = behavior;
    }

    private void buildScreen() {
        Screen screen = new Screen.Builder()
            .name(screenName)
            .structure(screenStructure)
            .style(screenStyle)
            .behavior(screenBehavior)
            .build();
        
        screens.add(screen);
        codeFiles.add(new CodeFile(screenName, generateScreenCode()));
    }

    private List<String> generateScreenCode() {
        List<String> code = new ArrayList<>();

        code.addAll(screenStructure.toCode());
        code.addAll(screenStyle.toCode());
        code.addAll(screenBehavior.toCode());

        return code;
    }

    private void runFrameworkGenerateCode() throws CoderException {
        obtainedProject = framework.generateCode(screens);
    }

    private void assertHasCorrectCodeFiles() {
        Assertions.assertEquals(codeFiles, obtainedProject.getCodeFiles());
    }

    private void withPlatform(String name) {
        platform = name;
    }

    private void runFrameworkGenerateMobileApplicationFor() 
    throws AppGenerationException {
        framework.generateMobileApplicationFor(platform, location, location);
    }

    private void assertGeneratedMobileApplicationForIsCorrect() {
        Assertions.assertEquals(platform, framework.getGeneratedMobileApplication());
    }
}
