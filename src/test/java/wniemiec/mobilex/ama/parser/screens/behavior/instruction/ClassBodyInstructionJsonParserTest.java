package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ClassBodyInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ClassBodyInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private List<JSONObject> body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ClassBodyInstructionJsonParser.getInstance();
        parsedInstruction = null;
        body = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithOneMethodDefinition() 
    throws ParseException, IOException {
        withDefinition(
            buildStaticMethodDefinition(
                true,
                buildIdentifier("getFirst"),
                buildFunctionExpression(
                    buildBlockStatement(buildReturnInstruction(buildIdentifier("a"))), 
                    buildIdentifier("a"), 
                    buildIdentifier("b")
                )
            )

        );
        doParsing();
        assertParsedCodeIs("{ static getFirst(a, b) { return a } }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withDefinition(JSONObject statement) {
        body.add(statement);
    }

    private JSONObject buildStaticMethodDefinition(
        boolean computed, 
        JSONObject key, 
        JSONObject value
    ) {
        JSONObject methodDefinition = new JSONObject();

        methodDefinition.put("type", "MethodDefinition");
        methodDefinition.put("static", true);
        methodDefinition.put("computed", computed);
        methodDefinition.put("kind", "method");
        methodDefinition.put("key", key);
        methodDefinition.put("value", value);

        return methodDefinition;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
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

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildClassBody());
    }

    private JSONObject buildClassBody() {
        JSONObject expression = new JSONObject();
        JSONArray jsonBody = new JSONArray();

        body.forEach(jsonBody::put);
        expression.put("type", "ClassBody");
        expression.put("body", jsonBody);

        return expression;
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
