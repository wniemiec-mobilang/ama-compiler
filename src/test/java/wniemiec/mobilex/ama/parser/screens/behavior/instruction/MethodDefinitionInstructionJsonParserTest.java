package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class MethodDefinitionInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private MethodDefinitionInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONObject methodDefinition;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = MethodDefinitionInstructionJsonParser.getInstance();
        parsedInstruction = null;
        methodDefinition = buildMethodDefinition();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithKeyAndValueAndStaticAndComputed() 
    throws ParseException, IOException {
        withKey(buildIdentifier("getFirst"));
        withComputed(true);
        withStatic(true);
        withKind("method");
        withValue(
            buildFunctionExpression(
                buildBlockStatement(buildReturnInstruction(buildIdentifier("a"))), 
                buildIdentifier("a"), 
                buildIdentifier("b")
            )
        );
        doParsing();
        assertParsedCodeIs("static getFirst(a, b) { return a }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildMethodDefinition() {
        JSONObject definition = new JSONObject();

        definition.put("type", "MethodDefinition");

        return definition;
    }

    private void withComputed(boolean value) {
        methodDefinition.put("computed", value);
    }

    private void withStatic(boolean value) {
        methodDefinition.put("static", value);
    }

    private void withKind(String kind) {
        methodDefinition.put("kind", kind);
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private void withKey(JSONObject expression) {
        methodDefinition.put("key", expression);
    }

    private JSONObject buildBlockStatement(JSONObject... statements) {
        JSONObject instruction = new JSONObject();
        JSONArray jsonStatements = new JSONArray();

        Arrays.stream(statements).forEach(jsonStatements::put);
        instruction.put("type", "BlockStatement");
        instruction.put("body", jsonStatements);

        return instruction;
    }

    private JSONObject buildReturnInstruction(JSONObject argument) {
        JSONObject expression = new JSONObject();

        expression.put("type", "ReturnStatement");
        expression.put("argument", argument);

        return expression;
    }

    private JSONObject buildFunctionExpression(JSONObject body, JSONObject... params) {
        JSONObject expression = new JSONObject();
        JSONArray jsonParameters = new JSONArray();

        Arrays.stream(params).forEach(jsonParameters::put);
        expression.put("type", "FunctionExpression");
        expression.put("async", false);
        expression.put("params", jsonParameters);
        expression.put("body", body);

        return expression;
    }


    private void withValue(JSONObject expression) {
        methodDefinition.put("value", expression);
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(methodDefinition);
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
