package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class VariableDeclarationInstructionJsonParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private VariableDeclarationInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONArray declarations;
    private String kind;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = VariableDeclarationInstructionJsonParser.getInstance();
        parsedInstruction = null;
        kind = null;
        declarations = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseSingleDeclaration() 
    throws ParseException, IOException {
        withKind("let");
        withDeclaration("undefined", "x");
        doParsing();
        assertParsedCodeIs("let x");
    }

    @Test
    void testParseSingleDeclarationWithInitialization() 
    throws ParseException, IOException {
        withKind("let");
        withDeclaration("number", "x", buildLiteral(0));
        doParsing();
        assertParsedCodeIs("let x = 0");
    }

    @Test
    void testParseMultiDeclarations() 
    throws ParseException, IOException {
        withKind("let");
        withDeclaration("undefined", "x");
        withDeclaration("undefined", "y");
        withDeclaration("undefined", "z");
        doParsing();
        assertParsedCodeIs("let x, y, z");
    }

    @Test
    void testParseMultiDeclarationsAndInitializations() 
    throws ParseException, IOException {
        withKind("let");
        withDeclaration("undefined", "x");
        withDeclaration("number", "y", buildLiteral(0));
        withDeclaration("number", "z", buildLiteral(2));
        doParsing();
        assertParsedCodeIs("let x, y = 0, z = 2");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withKind(String kind) {
        this.kind = kind;
    }

    private void withDeclaration(String type, String name) {
        withDeclaration(type, name, null);
    }

    private void withDeclaration(String type, String name, JSONObject value) {
        declarations.put(buildDeclarator(type, name, value));
    }

    private JSONObject buildDeclarator(String type, String name, JSONObject value) {
        JSONObject declarator = new JSONObject();
        JSONObject idObject = new JSONObject();

        declarator.put("type", "Identifier");
        declarator.put("id", idObject);
        declarator.put("init", value);

        idObject.put("type", type);
        idObject.put("name", name);

        return declarator;
    }

    private JSONObject buildLiteral(Object value) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Literal");
        expression.put("value", value);

        return expression;
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildVariableDeclaration());
    }

    private JSONObject buildVariableDeclaration() {
        JSONObject assignmentExpression = new JSONObject();

        assignmentExpression.put("kind", kind);
        assignmentExpression.put("declarations", declarations);

        return assignmentExpression;
    }

    private void assertParsedCodeIs(String code) {
        assertHasSameLine(parsedInstruction.toCode(), code);
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
}
