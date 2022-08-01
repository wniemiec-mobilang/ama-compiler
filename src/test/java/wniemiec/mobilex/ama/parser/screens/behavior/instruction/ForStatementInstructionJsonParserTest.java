package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ForStatementInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ForStatementInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONObject init;
    private JSONObject test;
    private JSONObject update;
    private JSONObject body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ForStatementInstructionJsonParser.getInstance();
        parsedInstruction = null;
        init = null;
        test = null;
        update = null;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithInitAndTestAndUpdateAndBody() throws ParseException, IOException {
        withInit(buildVariableDeclaration("let", "i", buildLiteral(0)));
        withTest(buildIdentifier("hasItems"));
        withUpdate(buildPostIncrementExpression("i"));
        withBody(buildExpressionStatement(buildPostIncrementExpression("total")));
        doParsing();
        assertParsedCodeIs("for (let i = 0; hasItems; i++) total++;");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildVariableDeclaration(String kind, String name, JSONObject value) {
        JSONObject expression = new JSONObject();
        JSONArray declarations = new JSONArray();

        declarations.put(buildDeclarator("undefined", name, value));
        expression.put("type", "VariableDeclaration");
        expression.put("kind", kind);
        expression.put("declarations", declarations);

        return expression;
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

    private void withInit(JSONObject value) {
        init = value;
    }

    private void withTest(JSONObject expression) {
        test = expression;
    }

    private JSONObject buildPostIncrementExpression(String label) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "UpdateExpression");
        instruction.put("operator", "++");
        instruction.put("prefix", false);
        instruction.put("argument", buildIdentifier(label));

        return instruction;
    }

    private void withUpdate(JSONObject expression) {
        update = expression;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private JSONObject buildExpressionStatement(JSONObject expression) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ExpressionStatement");
        instruction.put("expression", expression);

        return instruction;
    }

    private void withBody(JSONObject instruction) {
        body = instruction;
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildForStatement());
    }

    private JSONObject buildForStatement() {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ForStatement");
        instruction.put("init", init);
        instruction.put("test", test);
        instruction.put("update", update);
        instruction.put("body", body);

        return instruction;
    }

    private void assertParsedCodeIs(String code) {
        assertHasSameLine(code, parsedInstruction.toCode());
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
