package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.framework.ionic.parser.IonicMobilangDirectiveParser;


class IonicMobiLangDirectiveParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private IonicMobilangDirectiveParser directiveParser;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        directiveParser = new IonicMobilangDirectiveParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testScreenDirectiveWithSingleParameterAndDoubleQuotes() {
        withCode(
            "window.location.href(\"mobilang::screen::foo?id=2\")"
        );
        doParsing();
        assertCodeEquals(
            "window.location.href(\"foo/id__eq__2\")"
        );
    }

    @Test
    void testScreenDirectiveWithSingleParameterAndSingleQuotes() {
        withCode(
            "window.location.href('mobilang::screen::foo?id=2')"
        );
        doParsing();
        assertCodeEquals(
            "window.location.href('foo/id__eq__2')"
        );
    }

    @Test
    void testScreenDirectiveWithParametersAndDoubleQuotes() {
        withCode(
            "window.location.href(\"mobilang::screen::foo?id=2&value=something\")"
        );
        doParsing();
        assertCodeEquals(
            "window.location.href(\"foo/id__eq__2&value__eq__something\")"
        );
    }

    @Test
    void testScreenDirectiveWithParametersAndSingleQuotes() {
        withCode(
            "window.location.href('mobilang::screen::foo?id=2&value=something')"
        );
        doParsing();
        assertCodeEquals(
            "window.location.href('foo/id__eq__2&value__eq__something')"
        );
    }

    @Test
    void testScreenDirectiveWithDoubleQuotes() {
        withCode(
            "window.location.href(\"mobilang::screen::foo\")"
        );
        doParsing();
        assertCodeEquals(
            "window.location.href(\"foo\")"
        );
    }

    @Test
    void testScreenDirectiveWithSingleQuotes() {
        withCode(
            "window.location.href('mobilang::screen::foo')"
        );
        doParsing();
        assertCodeEquals(
            "window.location.href('foo')"
        );
    }

    @Test
    void testInputDirectiveWithDoubleQuotes() {
        withCode(
            "const bar = \"mobilang::input::foo\""
        );
        doParsing();
        assertCodeEquals(
            "const bar = this.__input_foo"
        );
    }

    @Test
    void testInputDirectiveWithSingleQuotes() {
        withCode(
            "const bar = \'mobilang::input::foo\'"
        );
        doParsing();
        assertCodeEquals(
            "const bar = this.__input_foo"
        );
    }

    @Test
    void testParamDirectiveWithDoubleQuotes() {
        withCode(
            "const id = \"mobilang::param::id\""
        );
        doParsing();
        assertCodeEquals(
            "const id = this.routeParams.snapshot.params.q.split('id__eq__')[1].split('&')[0]"
        );
    }

    @Test
    void testParamDirectiveWithSingleQuotes() {
        withCode(
            "const id = 'mobilang::param::id'"
        );
        doParsing();
        assertCodeEquals(
            "const id = this.routeParams.snapshot.params.q.split('id__eq__')[1].split('&')[0]"
        );
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withCode(String... lines) {
        code = Arrays.asList(lines);
    }

    private void doParsing() {
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
