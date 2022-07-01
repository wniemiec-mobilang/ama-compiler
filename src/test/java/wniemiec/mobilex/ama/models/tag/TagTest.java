package wniemiec.mobilex.ama.models.tag;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.tag.Tag;


class TagTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected Tag firstTag;
    protected Tag secondTag;
    protected Tag thirdTag;
    private Tag clonedTag;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        firstTag = null;
        secondTag = null;
        thirdTag = null;
        clonedTag = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testClone() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("head"));
        withThirdTag(buildNormalTagWithValue("title", "foo title"));
        addChildInFirstTag(secondTag);
        addChildInSecondTag(thirdTag);
        cloneTag(firstTag);
        assertClonedTagIsEqualTo(firstTag);
    }

    @Test
    void testAddChild() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("head"));
        addChildInFirstTag(secondTag);
        assertChildrenOfFirstTagIs(secondTag);
        assertSecondTagParentIs(firstTag);
    }

    @Test
    void testReplaceChild() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("head"));
        withThirdTag(Tag.getNormalInstance("body"));
        addChildInFirstTag(secondTag);
        replaceChildOfFirstTagWith(secondTag, thirdTag);
        assertChildrenOfFirstTagIs(thirdTag);
        assertSecondTagParentIs(firstTag);
        assertTagHasNoParent(secondTag);
    }

    @Test
    void testAddAttribute() {
        withFirstTag(buildNormalTagWithIdAttribute("p", "some-id"));
        assertFirstTagHasAttribute("id");
        assertFirstTagHasId("some-id");
    }

    @Test
    void testRemoveAttribute() {
        withFirstTag(buildNormalTagWithIdAttribute("p", "some-id"));
        removeAttributeFromFirstTag("id");
        assertFirstTagDoesNotHaveAttribute("id");
    }

    @Test
    void testGetTagWithId() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("body"));
        withThirdTag(buildNormalTagWithIdAttribute("p", "some-id"));
        addChildInFirstTag(secondTag);
        addChildInSecondTag(thirdTag);
        assertGetTagWithIdUsingFirstTagIs(thirdTag, "some-id");
    }

    @Test
    void testIsIdEqualToWithCorrectId() {
        withFirstTag(buildNormalTagWithIdAttribute("p", "some-id"));
        assertFirstTagHasAttribute("id");
        assertIsIdEqualToUsingFirstTagIs("some-id");
    }

    @Test
    void testIsIdEqualToWithIncorrectId() {
        withFirstTag(buildNormalTagWithIdAttribute("p", "some-id"));
        assertFirstTagHasAttribute("id");
        assertIsIdEqualToUsingFirstTagIsNot("some-id");
    }

    @Test
    void testToCode() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("head"));
        withThirdTag(buildNormalTagWithValue("p", "some text"));
        addChildInFirstTag(secondTag);
        addChildInSecondTag(thirdTag);
        assertToCodeUsingFirstTagIs(
            "<body>",
            "<div>",
            "<p>",
            "some text",
            "</p>",
            "</div>",
            "</body>"
        );
    }

    @Test
    void testAddChildren() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("head"));
        withThirdTag(Tag.getNormalInstance("body"));
        addChildrenInFirstTag(secondTag, thirdTag);
        assertChildrenOfFirstTagIs(secondTag, thirdTag);
    }

    @Test
    void testAddStyle() {
        withFirstTag(Tag.getNormalInstance("p"));
        addStyleToFirstTag("color", "red");
        assertFirstTagHasStyle("color");
        assertFirstTagStyleIs("color", "red");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    protected void withFirstTag(Tag tag) {
        firstTag = tag;
    }

    protected void withSecondTag(Tag tag) {
        secondTag = tag;
    }

    protected void withThirdTag(Tag tag) {
        thirdTag = tag;
    }

    protected void addChildInFirstTag(Tag tag) {
        firstTag.addChild(tag);
    }

    protected void addChildInSecondTag(Tag tag) {
        secondTag.addChild(tag);
    }

    private void cloneTag(Tag tag) {
        clonedTag = tag.clone();
    }

    private void assertClonedTagIsEqualTo(Tag tag) {
        Assertions.assertEquals(tag, clonedTag);
    }

    private void replaceChildOfFirstTagWith(Tag child, Tag newChild) {
        firstTag.replaceChild(child, newChild);
    }

    protected void assertChildrenOfFirstTagIs(Tag... tags) {
        Assertions.assertEquals(
            Arrays.asList(tags),
            firstTag.getChildren()
        );
    }

    protected void assertSecondTagParentIs(Tag tag) {
        Assertions.assertEquals(tag, secondTag.getParent());
    }

    protected Tag buildNormalTagWithValue(String tagName, String value) {
        Tag tag = Tag.getNormalInstance(tagName);
        
        tag.setValue(value);

        return tag;
    }

    protected Tag buildVoidTagWithIdAttribute(String tagName, String id) {
        Tag tag = Tag.getVoidInstance(tagName);

        tag.addAttribute("id", id);

        return tag;
    }

    protected Tag buildNormalTagWithIdAttribute(String tagName, String id) {
        Tag tag = Tag.getNormalInstance(tagName);

        tag.addAttribute("id", id);

        return tag;
    }

    private void assertTagHasNoParent(Tag tag) {
        Assertions.assertFalse(tag.hasParent());
    }

    private void assertFirstTagHasAttribute(String attribute) {
        Assertions.assertTrue(firstTag.hasAttribute(attribute));
    }

    private void assertFirstTagHasId(String id) {
        Assertions.assertEquals(id, firstTag.getAttribute("id"));
    }

    private void removeAttributeFromFirstTag(String attribute) {
        firstTag.removeAttribute(attribute);
    }

    private void assertFirstTagDoesNotHaveAttribute(String attribute) {
        Assertions.assertFalse(firstTag.hasAttribute(attribute));
    }

    private void assertGetTagWithIdUsingFirstTagIs(Tag tag, String id) {
        Assertions.assertEquals(tag, firstTag.getTagWithId(id));
    }

    private void assertIsIdEqualToUsingFirstTagIs(String id) {
        Assertions.assertTrue(firstTag.isIdEqualTo(id));
    }

    private void assertIsIdEqualToUsingFirstTagIsNot(String id) {
        Assertions.assertFalse(firstTag.isIdEqualTo(id));
    }

    private void assertToCodeUsingFirstTagIs(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);
        List<String> generatedCode = firstTag.toCode();

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

    private void addChildrenInFirstTag(Tag... children) {
        firstTag.addChildren(Arrays.asList(children));
    }

    private void addStyleToFirstTag(String key, String value) {
        firstTag.addStyle(key, value);
    }

    private void assertFirstTagHasStyle(String key) {
        Assertions.assertTrue(firstTag.hasStyle(key));
    }

    private void assertFirstTagStyleIs(String key, String value) {
        Assertions.assertEquals(value, firstTag.getStyle(key));
    }
}
