package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ArrayPatternInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ArrayPatternInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONArray elements;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ArrayPatternInstructionJsonParser.getInstance();
        parsedInstruction = null;
        elements = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithMultipleElements() throws ParseException, IOException {
        withElement(buildIdentifier("n1"));
        withElement(buildIdentifier("n2"));
        withElement(buildIdentifier("n3"));
        doParsing();
        assertParsedCodeIs("[n1, n2, n3]");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private void withElement(JSONObject element) {
        elements.put(element);
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildArrayPatternStatement());
    }

    private JSONObject buildArrayPatternStatement() {
        JSONObject instruction = new JSONObject();
        instruction.put("type", "ArrayPattern");
        instruction.put("elements", elements);

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
