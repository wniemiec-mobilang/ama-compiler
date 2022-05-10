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
    void testFunctionWithParameters() {
        withCode(
            "function sum(n1, n2) {",
            "    return n1 + n2;",
            "}"
        );
        runConverter();
        assertCodeEquals(
            "const sum = (n1, n2) => {",
            "    return n1 + n2;",
            "}"
        );
    }

    @Test
    void testFunctionWithoutParameters() {
        withCode(
            "function sum() {",
            "    return 2 + 3;",
            "}"
        );
        runConverter();
        assertCodeEquals(
            "const sum = () => {",
            "    return 2 + 3;",
            "}"
        );
    }

    @Test
    void testFunctionWithBreakLineBeforeCurlyBracket() {
        withCode(
            "function sum()",
            "{",
            "    return 2 + 3;",
            "}"
        );
        runConverter();
        assertCodeEquals(
            "const sum = () => ",
            "{",
            "    return 2 + 3;",
            "}"
        );
    }

    @Test
    void testArrowFunction() {
        withCode(
            "const sum = (n1, n2) => {",
            "    return n1 + n2;",
            "}"
        );
        runConverter();
        assertCodeEquals(
            "const sum = (n1, n2) => {",
            "    return n1 + n2;",
            "}"
        );
    }

    @Test
    void testFunctionWithCode() {
        withCode(
            "let res = 0.0",
            "function sum(n1, n2) {",
            "    return n1 + n2;",
            "}",
            "res = sum(4,3)",
            "console.log(res)"
        );
        runConverter();
        assertCodeEquals(
            "let res = 0.0",
            "const sum = (n1, n2) => {",
            "    return n1 + n2;",
            "}",
            "res = sum(4,3)",
            "console.log(res)"
        );
    }

    @Test
    void testCodeWithoutFunction() {
        withCode(
            "let res = 0.0",
            "res = 4 + 3",
            "console.log(res)"
        );
        runConverter();
        assertCodeEquals(
            "let res = 0.0",
            "res = 4 + 3",
            "console.log(res)"
        );
    }

    @Test
    void testEmptyCode() {
        withCode("");
        runConverter();
        assertCodeEquals("");
    }

    @Test
    void testNullCode() {
        withCode();
        runConverter();
        assertCodeEquals();
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
