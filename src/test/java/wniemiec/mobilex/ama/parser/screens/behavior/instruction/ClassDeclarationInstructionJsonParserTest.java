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


class ClassDeclarationInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ClassDeclarationInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private String name;
    private JSONObject superClass;
    private JSONArray body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ClassDeclarationInstructionJsonParser.getInstance();
        parsedInstruction = null;
        name = null;
        superClass = null;
        body = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithName() throws ParseException, IOException {
        withName("StringUtils");
        
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            doParsing();
        });
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withName(String name) {
        this.name = name;
    }

    private void withSuperClass(JSONObject superClass) {
        this.superClass = superClass;
    }

    private void withBody(JSONObject... instructions) {
        Arrays.stream(instructions).forEach(body::put);
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildClassDeclaration());
    }

    private JSONObject buildClassDeclaration() {
        JSONObject instruction = new JSONObject();
        JSONObject id = new JSONObject();

        id.put("name", name);
        instruction.put("type", "ClassDeclaration");
        instruction.put("id", id);
        instruction.put("superClass", superClass);
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
