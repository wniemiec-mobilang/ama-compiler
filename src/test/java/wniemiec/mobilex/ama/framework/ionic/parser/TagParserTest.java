package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;


abstract class TagParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        code = new ArrayList<>();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    protected void withCode(String... lines) {
        code = Arrays.asList(lines);
    }

    protected void doParsing() {
        parseCode(code);
    }

    protected abstract void parseCode(List<String> code);

    protected void assertCodeEquals(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);
        List<String> obtainedCode = getParsedCode();

        assertHasSameSize(expectedCode, obtainedCode);
        assertHasSameLines(expectedCode, obtainedCode);
    }

    protected abstract List<String> getParsedCode();

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
