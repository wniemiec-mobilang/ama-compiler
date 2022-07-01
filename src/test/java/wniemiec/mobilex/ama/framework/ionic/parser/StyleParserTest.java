package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.framework.ionic.parser.StyleParser;


class StyleParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private StyleParser parser;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = new StyleParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testNumericAssignment() {
        withCode("document.getElementById('body').style.opacity=1;");
        doParsing();
        assertCodeEquals("document.getElementById('body').style.opacity=\"1\";");
    }

    @Test
    void testStringAssignment() {
        withCode("document.getElementById('body').style.color = '#12345678';");
        doParsing();
        assertCodeEquals("document.getElementById('body').style.color = '#12345678';");
    }

    @Test
    void testNumericAssignmentAsString() {
        withCode("document.getElementById(\"body\").style.opacity=\"1\";");
        doParsing();
        assertCodeEquals("document.getElementById(\"body\").style.opacity=\"1\";");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withCode(String... lines) {
        code = Arrays.asList(lines);
    }

    private void doParsing() {
        parser.parse(code);
    }

    private void assertCodeEquals(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, parser.getParsedCode());
        assertHasSameLines(expectedCode, parser.getParsedCode());
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
