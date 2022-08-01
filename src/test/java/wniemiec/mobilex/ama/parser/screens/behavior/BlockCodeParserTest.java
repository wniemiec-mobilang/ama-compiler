package wniemiec.mobilex.ama.parser.screens.behavior;

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


class BlockCodeParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private BlockCodeParser parser;
    private List<Instruction> parsedInstructions;
    private JSONArray blockCode;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = BlockCodeParser.getInstance();
        blockCode = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseOneScreenWithStructureAndStyleAndBehavior() 
    throws ParseException, IOException {
        withInstruction(buildIdentifierAssignment("pi", 3.1416));
        doParsing();
        assertParsedCodeIs("pi = 3.1416;");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildIdentifierAssignment(String identifier, Object value) {
        JSONObject assignmentExpression = new JSONObject();

        assignmentExpression.put("type", "ExpressionStatement");
        assignmentExpression.put(
            "expression", 
            buildAssignmentExpression(identifier, value)
        );

        return assignmentExpression;
    }

    private JSONObject buildAssignmentExpression(String identifier, Object value) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Assignment");
        expression.put("operator", "=");
        expression.put("left", buildIdentifier(identifier));
        expression.put("right", buildLiteral(value));

        return expression;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private JSONObject buildLiteral(Object value) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Literal");
        expression.put("value", value);

        return expression;
    }

    private void withInstruction(JSONObject instruction) {
        blockCode.put(instruction);
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstructions = parser.parse(blockCode);
    }

    private void assertParsedCodeIs(String... code) {
        assertCode(generateCodeFromParsedInstructions(), code);
    }

    public List<String> generateCodeFromParsedInstructions() {
        List<String> code = new ArrayList<>();

        for (Instruction instruction : parsedInstructions) {
            code.addAll(extractCodeFromInstruction(instruction));
        }

        return code;
    }

    private List<String> extractCodeFromInstruction(Instruction instruction) {
        List<String> code = new ArrayList<>();

        for (String rawCode : instruction.toCode().split("\n")) {
            code.add(rawCode);
        }

        return code;
    }

    private void assertCode(List<String> obtainedCode, String... expectedCode) {
        List<String> code = Arrays.asList(expectedCode);

        assertHasSameSize(code, obtainedCode);
        assertHasSameLines(code, obtainedCode);
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
}
