package wniemiec.mobilex.ama.models.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.tag.Tag;
import wniemiec.mobilex.ama.models.tag.TagCoder;


class TagCoderTest extends TagTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TagCoder tagCoder;
    private List<String> generatedCode;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        tagCoder = new TagCoder();
        generatedCode = new ArrayList<>();
    }
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testVoidTagWithoutValue() {
        withFirstTag(Tag.getVoidInstance("img"));
        runTagCoder(firstTag);
        assertToCodeIs("<img />");
    }

    @Test
    void testVoidTagWithValue() {
        withFirstTag(buildVoidTagWithValue("img", "wrong value"));
        runTagCoder(firstTag);
        assertToCodeIs("<img />");
    }

    @Test
    void testVoidTagWithChildren() {
        withFirstTag(Tag.getVoidInstance("img"));
        withSecondTag(Tag.getNormalInstance("html"));
        addChildInFirstTag(secondTag);
        runTagCoder(firstTag);
        assertToCodeIs("<img />");
    }

    @Test
    void testVoidTagWithAttributes() {
        withFirstTag(buildVoidTagWithIdAttribute("img", "some-id"));
        runTagCoder(firstTag);
        assertToCodeIs("<img id=\"some-id\"/>");
    }

    @Test
    void testNormalTagWithValue() {
        withFirstTag(buildNormalTagWithValue("p", "some text"));
        runTagCoder(firstTag);
        assertToCodeIs(
            "<p>",
            "some text",
            "</p>"
        );
    }

    @Test
    void testNormalTagWithChildren() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getVoidInstance("img"));
        addChildInFirstTag(secondTag);
        runTagCoder(firstTag);
        assertToCodeIs(
            "<html>",
            "<img/>",
            "</html>"
        );
    }

    @Test
    void testNormalTagWithAttributes() {
        withFirstTag(buildNormalTagWithIdAttribute("div", "some-id"));
        runTagCoder(firstTag);
        assertToCodeIs(
            "<div id=\"some-id\">",
            "</div>"
        );
    }

    @Test
    void testNormalTagWithAttributesAndChildren() {
        withFirstTag(buildNormalTagWithIdAttribute("div", "some-id"));
        withSecondTag(Tag.getVoidInstance("img"));
        addChildInFirstTag(secondTag);
        runTagCoder(firstTag);
        assertToCodeIs(
            "<div id=\"some-id\">",
            "<img/>",
            "</div>"
        );
    }

    @Test
    void testNormalTagWithAttributesAndChildrenWithAttributes() {
        withFirstTag(buildNormalTagWithIdAttribute("div", "some-id"));
        withSecondTag(buildVoidTagWithIdAttribute("img", "other-id"));
        addChildInFirstTag(secondTag);
        runTagCoder(firstTag);
        assertToCodeIs(
            "<div id=\"some-id\">",
            "<img id=\"other-id\"/>",
            "</div>"
        );
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void runTagCoder(Tag tag) {
        generatedCode = tagCoder.toCode(tag);
    }

    private void assertToCodeIs(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, generatedCode);
        assertHasSameLines(expectedCode, generatedCode);
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

    private Tag buildVoidTagWithValue(String tagName, String value) {
        Tag tag = Tag.getVoidInstance(tagName);
        
        tag.setValue(value);

        return tag;
    }
}
