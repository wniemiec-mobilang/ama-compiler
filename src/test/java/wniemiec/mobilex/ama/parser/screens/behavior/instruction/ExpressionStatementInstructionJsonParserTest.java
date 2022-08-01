package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ExpressionStatementInstructionJsonParserTest {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ExpressionStatementInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONObject expression;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ExpressionStatementInstructionJsonParser.getInstance();
        parsedInstruction = null;
        expression = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithExpression() throws ParseException, IOException {
        withExpression(buildPostIncrementExpression("total"));
        doParsing();
        assertParsedCodeIs("total++;");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
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

    private void withExpression(JSONObject expression) {
        this.expression = expression;
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildExpressionStatement());
    }

    private JSONObject buildExpressionStatement() {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ExpressionStatement");
        instruction.put("expression", expression);

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
