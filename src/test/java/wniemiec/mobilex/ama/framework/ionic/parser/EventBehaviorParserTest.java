package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.framework.ionic.parser.EventBehaviorParser;


class EventBehaviorParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private EventBehaviorParser parser;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = new EventBehaviorParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testOnClickAndThis() {
        withCode("`<button id=\"foo\" class=\"item\" onclick=\"openDescription(this);\">`");
        doParsing();
        assertCodeEquals("`<button id=\"foo\" class=\"item\">`;document.getElementById(\"foo\").onclick = () => openDescription(document.getElementById(\"foo\"));");
    }

    @Test
    void testOnClickWithCamelCase() {
        withCode("`<button id=\"foo\" class=\"item\" onClick=\"alert('Hello!')\">`");
        doParsing();
        assertCodeEquals("`<button id=\"foo\" class=\"item\">`;document.getElementById(\"foo\").onclick = () => alert('Hello!')");
    }

    @Test
    void testOnClickWithoutId() {
        withCode("`<button class=\"item\" onClick=\"alert('Hello!')\">`");
        doParsing();
        assertCodeEquals("`<button class=\"item\" id=\"" + getGeneratedId(0) + "\">`;document.getElementById(\"" + getGeneratedId(0) + "\").onclick = () => alert('Hello!')");
    }

    @Test
    void testOnClickAndThisWithoutId() {
        withCode("`<button class=\"item\" onclick=\"openDescription(this);\">`");
        doParsing();
        assertCodeEquals("`<button class=\"item\" id=\"" + getGeneratedId(0) + "\">`;" 
                         + "document.getElementById(\"" + getGeneratedId(0) + "\").onclick = () => openDescription(document.getElementById(\"" + getGeneratedId(0) + "\"));");
    }

    @Test
    void testOnClickAsAttribute() {
        withCode("document.getElementById('foo').onclick=()=>alert('bar')");
        doParsing();
        assertCodeEquals("document.getElementById('foo').onclick=()=>alert('bar')");
    }

    @Test
    void testOnClickWithoutIdWithMultipleTags() {
        withCode("`<div><button class=\"item\" onClick=\"alert('Hello!')\"></button>" 
                 + "<button class=\"item\" onClick=\"alert('World!')\"></button></div>`");
        doParsing();
        assertCodeEquals("`<div><button class=\"item\" id=\"" + getGeneratedId(0) + "\"></button>" 
                         + "<button class=\"item\" id=\"" + getGeneratedId(1) + "\"></button></div>`;" 
                         + "document.getElementById(\"" + getGeneratedId(0) + "\").onclick = () => alert('Hello!');"
                         + "document.getElementById(\"" + getGeneratedId(1) + "\").onclick = () => alert('World!')");
    }

    @Test
    void testOnClickWithTemplateStringAndThis() {
        withCode("`<button id=\"foo\" class=\"item\" onclick=\"openDescription(${1});\">`");
        doParsing();
        assertCodeEquals("`<button id=\"foo\" class=\"item\">`;document.getElementById(\"foo\").onclick = () => openDescription(`${1}`);");
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

    private String getGeneratedId(int index) {
        if (index >= parser.getGeneratedIds().size()) {
            return "NULL";
        }

        return parser.getGeneratedIds().get(index);
    }
}
