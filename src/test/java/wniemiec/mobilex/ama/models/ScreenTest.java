package wniemiec.mobilex.ama.models;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.Literal;
import wniemiec.mobilex.ama.models.tag.Tag;
import wniemiec.util.java.StringUtils;


class ScreenTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Screen screen;
    private String name;
    private Tag structure;
    private Style style;
    private Behavior behavior;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        screen = null;
        name = null;
        structure = null;
        style = null;
        behavior = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetStructureAndName() {
        withName("some-funny-screen-name");
        withStructure(buildPInsideADivWithValue("some text"));
        buildScreen();
        assertGetNameIsCorrect();
        assertGetRawNameIsCorrect();
        assertGetStructureIsCorrect();
    }

    @Test
    void testScreenWithoutStructure() {
        withName("some-funny-screen-name");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            buildScreen();
        });
    }

    @Test
    void testScreenWithoutName() {
        withStructure(buildPInsideADivWithValue("some text"));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            buildScreen();
        });
    }

    @Test
    void testSimpleStructureAndStyle() {
        withName("some-funny-screen-name");
        withStructure(buildPInsideADivWithValue("some text"));
        withStyle(buildPStyleUsingBlueAndWhite());
        buildScreen();
        assertGetStructureIsCorrect();
        assertGetStyleIsCorrect();
    }

    @Test
    void testSimpleStructureAndBehavior() {
        withName("some-funny-screen-name");
        withStructure(buildPInsideADivWithValue("some text"));
        withBehavior(buildDeclarationWithIdAndAssignment("pi", "3.1416"));
        buildScreen();
        assertGetStructureIsCorrect();
        assertGetBehaviorIsCorrect();
    }

    @Test
    void testSimpleStructureAndStyleAndBehavior() {
        withName("some-funny-screen-name");
        withStructure(buildPInsideADivWithValue("some text"));
        withStyle(buildPStyleUsingBlueAndWhite());
        withBehavior(buildDeclarationWithIdAndAssignment("pi", "3.1416"));
        buildScreen();
        assertGetNameIsCorrect();
        assertGetRawNameIsCorrect();
        assertGetStructureIsCorrect();
        assertGetStyleIsCorrect();
        assertGetBehaviorIsCorrect();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withName(String name) {
        this.name = name;
    }

    private void withStructure(Tag structure) {
        this.structure = structure;
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

    private void withStyle(Style style) {
        this.style = style;
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

    private void withBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    private void buildScreen() {
        screen = new Screen.Builder()
            .name(name)
            .structure(structure)
            .style(style)
            .behavior(behavior)
            .build();
    }

    private void assertGetNameIsCorrect() {
        Assertions.assertEquals(capitalize(name), screen.getName());
    }

    private String capitalize(String text) {
        String capitalizedText = text.replaceAll("[-_]", " ");

        capitalizedText = StringUtils.capitalize(capitalizedText);
        capitalizedText = capitalizedText.replace(" ", "");

        return capitalizedText;
    }


    private void assertGetRawNameIsCorrect() {
        Assertions.assertEquals(name, screen.getRawName());
    }

    private Tag buildPInsideADivWithValue(String value) {
        Tag divTag = Tag.getNormalInstance("div");
        Tag pTag = Tag.getNormalInstance("p");

        divTag.addChild(pTag);
        pTag.setValue(value);

        return divTag;
    }

    private void assertGetStructureIsCorrect() {
        Assertions.assertEquals(structure, screen.getStructure());
    }

    private void assertGetStyleIsCorrect() {
        Assertions.assertEquals(style, screen.getStyle());
    }

    private void assertGetBehaviorIsCorrect() {
        Assertions.assertEquals(behavior, screen.getBehavior());
    }
}
