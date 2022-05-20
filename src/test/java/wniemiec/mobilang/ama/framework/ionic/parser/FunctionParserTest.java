package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FunctionParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private FunctionParser parser;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = new FunctionParser();
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
        doParsing();
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
        doParsing();
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
        doParsing();
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
        doParsing();
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
        doParsing();
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
        doParsing();
        assertCodeEquals(
            "let res = 0.0",
            "res = 4 + 3",
            "console.log(res)"
        );
    }

    @Test
    void testEmptyCode() {
        withCode("");
        doParsing();
        assertCodeEquals("");
    }

    @Test
    void testNullCode() {
        withCode();
        doParsing();
        assertCodeEquals();
    }

    @Test
    void testCodeWithFunctionBelongingToAVariableInDeclaration() {
        withCode(
            "const callMe = function () { alert('Hello!!!'); }",
            "callMe();"
        );
        doParsing();
        assertCodeEquals(
            "const callMe = () => { alert('Hello!!!'); }",
            "callMe();"
        );
    }

    @Test
    void testCodeWithFunctionBelongingToAVariableInAssignment() {
        withCode(
            "let callMe;",
            "callMe = function () { alert('Hello!!!'); }",
            "callMe();"
        );
        doParsing();
        assertCodeEquals(
            "let callMe;",
            "callMe = () => { alert('Hello!!!'); }",
            "callMe();"
        );
    }

    @Test
    void testCodeWithFunctionAsArgument() {
        withCode("alert(function () { return 'Hello!!!'; })");
        doParsing();
        assertCodeEquals("alert(() => { return 'Hello!!!'; })");
    }

    @Test
    void testTemp() {
        String x = "<a href=\"mobilang::screen::glossary-desc?id=${data[item].id}\">Leia mais</a>";
        String directive = "mobilang::screen::([A-z0-9\\-_]+\\?)[^\"']+";

        x.replaceAll(directive, "glossary-desc/id=\\${data[item].id}");
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
