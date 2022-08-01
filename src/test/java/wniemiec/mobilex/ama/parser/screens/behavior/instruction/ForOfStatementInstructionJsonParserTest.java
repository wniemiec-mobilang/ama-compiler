package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ForOfStatementInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ForOfStatementInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONObject left;
    private JSONObject right;
    private JSONObject body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ForOfStatementInstructionJsonParser.getInstance();
        parsedInstruction = null;
        left = null;
        right = null;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithLeftAndRightAndBody() throws ParseException, IOException {
        withLeft(buildVariableDeclaration("let", "item"));
        withRight(buildIdentifier("items"));
        withBody(buildExpressionStatement(buildPostIncrementExpression("total")));
        doParsing();
        assertParsedCodeIs("for (let item of items) total++;");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildVariableDeclaration(String kind, String name) {
        JSONObject expression = new JSONObject();
        JSONArray declarations = new JSONArray();

        declarations.put(buildDeclarator("undefined", name));
        expression.put("type", "VariableDeclaration");
        expression.put("kind", kind);
        expression.put("declarations", declarations);

        return expression;
    }

    private JSONObject buildDeclarator(String type, String name) {
        JSONObject declarator = new JSONObject();
        JSONObject idObject = new JSONObject();

        declarator.put("type", "Identifier");
        declarator.put("id", idObject);

        idObject.put("type", type);
        idObject.put("name", name);

        return declarator;
    }

    private void withLeft(JSONObject value) {
        left = value;
    }

    private void withRight(JSONObject expression) {
        right = expression;
    }

    private JSONObject buildPostIncrementExpression(String label) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "UpdateExpression");
        instruction.put("operator", "++");
        instruction.put("prefix", false);
        instruction.put("argument", buildIdentifier(label));

        return instruction;
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
        parsedInstruction = parser.parse(buildForOfStatement());
    }

    private JSONObject buildForOfStatement() {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ForOfStatement");
        instruction.put("left", left);
        instruction.put("right", right);
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
