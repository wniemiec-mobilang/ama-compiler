package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class IonicMobiLangDirectiveParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private IonicMobiLangDirectiveParser directiveParser;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        directiveParser = new IonicMobiLangDirectiveParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testScreenDirectiveWithSingleParameterAndDoubleQuotes() {
        withCode(
            "window.location.href(\"mobilang::screen::foo?id=2\")"
        );
        runParser();
        assertCodeEquals(
            "window.location.href(\"foo/id=2\")"
        );
    }

    @Test
    void testScreenDirectiveWithSingleParameterAndSingleQuotes() {
        withCode(
            "window.location.href('mobilang::screen::foo?id=2')"
        );
        runParser();
        assertCodeEquals(
            "window.location.href('foo/id=2')"
        );
    }

    @Test
    void testScreenDirectiveWithParametersAndDoubleQuotes() {
        withCode(
            "window.location.href(\"mobilang::screen::foo?id=2&value=something\")"
        );
        runParser();
        assertCodeEquals(
            "window.location.href(\"foo/id=2&value=something\")"
        );
    }

    @Test
    void testScreenDirectiveWithParametersAndSingleQuotes() {
        withCode(
            "window.location.href('mobilang::screen::foo?id=2&value=something')"
        );
        runParser();
        assertCodeEquals(
            "window.location.href('foo/id=2&value=something')"
        );
    }

    @Test
    void testScreenDirectiveWithDoubleQuotes() {
        withCode(
            "window.location.href(\"mobilang::screen::foo\")"
        );
        runParser();
        assertCodeEquals(
            "window.location.href(\"foo\")"
        );
    }

    @Test
    void testScreenDirectiveWithSingleQuotes() {
        withCode(
            "window.location.href('mobilang::screen::foo')"
        );
        runParser();
        assertCodeEquals(
            "window.location.href('foo')"
        );
    }

    @Test
    void testInputDirectiveWithDoubleQuotes() {
        withCode(
            "const bar = \"mobilang::input::foo\""
        );
        runParser();
        assertCodeEquals(
            "const bar = this._input_foo"
        );
    }

    @Test
    void testInputDirectiveWithSingleQuotes() {
        withCode(
            "const bar = \'mobilang::input::foo\'"
        );
        runParser();
        assertCodeEquals(
            "const bar = this._input_foo"
        );
    }

    @Test
    void testParamDirectiveWithDoubleQuotes() {
        withCode(
            "const id = \"mobilang::param::id\""
        );
        runParser();
        assertCodeEquals(
            "const id = this.routeParams.snapshot.params.q.split('id=')[1].split('&')[0]"
        );
    }

    @Test
    void testParamDirectiveWithSingleQuotes() {
        withCode(
            "const id = 'mobilang::param::id'"
        );
        runParser();
        assertCodeEquals(
            "const id = this.routeParams.snapshot.params.q.split('id=')[1].split('&')[0]"
        );
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withCode(String... lines) {
        code = Arrays.asList(lines);
    }

    private void runParser() {
        directiveParser.parse(code);
    }

    private void assertCodeEquals(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, directiveParser.getParsedCode());
        assertHasSameLines(expectedCode, directiveParser.getParsedCode());
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
