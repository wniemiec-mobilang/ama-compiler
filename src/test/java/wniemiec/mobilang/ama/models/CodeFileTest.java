package wniemiec.mobilang.ama.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CodeFileTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private CodeFile codeFile;
    private String name;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        codeFile = null;
        name = null;
        code = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetName() {
        withName("app.js");
        buildCodeFile();
        assertGetNameIsCorrect();
    }

    @Test
    void testGetCode() {
        withCode(
            "alert('check the console');",
            "console.log('hello world!');"
        );
        buildCodeFile();
        assertGetCodeIsCorrect();
    }

    @Test
    void testGetNameAndCode() {
        withName("app.js");
        withCode(
            "alert('check the console');",
            "console.log('hello world!');"
        );
        buildCodeFile();
        assertGetNameIsCorrect();
        assertGetCodeIsCorrect();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withName(String filename) {
        name = filename;
    }

    private void buildCodeFile() {
        codeFile = new CodeFile(name, code);
    }

    private void assertGetNameIsCorrect() {
        Assertions.assertEquals(name, codeFile.getName());
    }

    private void withCode(String... lines) {
        code = Arrays.asList(lines);
    }

    private void assertGetCodeIsCorrect() {
        assertHasSameSize(code, codeFile.getCode());
        assertHasSameLines(code, codeFile.getCode());
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
