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


class FunctionDeclarationInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private FunctionDeclarationInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private boolean async;
    private String name;
    private JSONArray params;
    private JSONObject body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = FunctionDeclarationInstructionJsonParser.getInstance();
        parsedInstruction = null;
        async = false;
        name = null;
        params = new JSONArray();;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseAsyncFunctionWithNameAndBodyAndParams() 
    throws ParseException, IOException {
        withAsync(true);
        withName("getFirst");
        withParams(
            buildIdentifier("n1"), 
            buildIdentifier("n2")
        );
        withBody(buildReturnInstruction(buildIdentifier("n1")));
        doParsing();
        assertParsedCodeIs("async function getFirst(n1, n2) return n1");
    }

    @Test
    void testParseSyncFunctionWithNameAndBodyAndParams() 
    throws ParseException, IOException {
        withAsync(false);
        withName("getFirst");
        withParams(
            buildIdentifier("n1"), 
            buildIdentifier("n2")
        );
        withBody(buildReturnInstruction(buildIdentifier("n1")));
        doParsing();
        assertParsedCodeIs("function getFirst(n1, n2) return n1");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withAsync(boolean value) {
        async = value;
    }

    private void withName(String name) {
        this.name = name;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private void withParams(JSONObject... parameters) {
        Arrays.stream(parameters).forEach(params::put);
    }

    private JSONObject buildReturnInstruction(JSONObject argument) {
        JSONObject expression = new JSONObject();

        expression.put("type", "ReturnStatement");
        expression.put("argument", argument);

        return expression;
    }

    private void withBody(JSONObject instruction) {
        body = instruction;
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildFunctionDeclaration());
    }

    private JSONObject buildFunctionDeclaration() {
        JSONObject expression = new JSONObject();
        JSONObject id = new JSONObject();

        id.put("name", name);
        expression.put("type", "FunctionDeclaration");
        expression.put("id", id);
        expression.put("async", async);
        expression.put("params", params);
        expression.put("body", body);

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
