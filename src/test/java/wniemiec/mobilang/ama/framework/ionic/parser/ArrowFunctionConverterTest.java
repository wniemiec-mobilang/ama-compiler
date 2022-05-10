package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ArrowFunctionConverterTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ArrowFunctionConverter converter;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        converter = new ArrowFunctionConverter();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testSimpleFunction() {
        withCode(
            "function sum(int n1, int n2) {",
            "    return n1 + n2;",
            "}"
        );
        runConverter();
        assertCodeEquals(
            "const sum = (int n1, int n2) => {",
            "    return n1 + n2;",
            "}"
        );
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withCode(String... lines) {
        code = Arrays.asList(lines);
    }

    private void runConverter() {
        converter.run(code);
    }

    private void assertCodeEquals(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        Assertions.assertEquals(expectedCode.size(), converter.getConvertedCode().size());

        for (int i = 0; i < expectedCode.size(); i++) {
            String expectedLine = expectedCode.get(i);
            String obtainedLine = converter.getConvertedCode().get(i);

            expectedLine = removeWhiteSpaces(expectedLine);
            obtainedLine = removeWhiteSpaces(obtainedLine);
            
            Assertions.assertEquals(expectedLine, obtainedLine);
        }
    }

    private String removeWhiteSpaces(String text) {
        return text.replaceAll("[\\s\\t]+", "");
    }
}
