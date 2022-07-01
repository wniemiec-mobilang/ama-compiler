package wniemiec.mobilex.ama.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MobilangDirectiveParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private MobilangDirectiveParser directiveParser;
    private String lineToParse;
    private String parsedLine;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        directiveParser = new MockMobilangDirectiveParser();
        lineToParse = null;
        parsedLine = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseScreenDirectiveWithoutParameters() {
        withLine("mobilang::screen::home");
        doParsing();
        assertParsedLineIs("home");
    }

    @Test
    void testParseScreenDirectiveWithOneParameter() {
        withLine("mobilang::screen::home?id=3");
        doParsing();
        assertParsedLineIs("home?id=3");
    }

    @Test
    void testParseScreenDirectiveWithTwoParameters() {
        withLine("mobilang::screen::home?id=3&hello=world");
        doParsing();
        assertParsedLineIs("home?id=3&hello=world");
    }

    @Test
    void testParseParamDirective() {
        withLine("mobilang::param::id");
        doParsing();
        assertParsedLineIs("id");
    }

    @Test
    void testParseINputDirective() {
        withLine("mobilang::input::id");
        doParsing();
        assertParsedLineIs("id");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withLine(String line) {
        lineToParse = line;
    }

    private void doParsing() {
        parsedLine = directiveParser.parse(lineToParse);
    }

    private void assertParsedLineIs(String expectedCode) {
        assertHasSameLine(expectedCode, parsedLine);
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
