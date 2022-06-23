package wniemiec.mobilang.ama.models.tag;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TagTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected Tag firstTag;
    protected Tag secondTag;
    protected Tag thirdTag;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        firstTag = null;
        secondTag = null;
        thirdTag = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testClone() {
        Tag htmlTag = Tag.getNormalInstance("html");
        Tag headTag = Tag.getNormalInstance("head");
        Tag titleTag = Tag.getNormalInstance("title");
        titleTag.setValue("foo title");
        Tag bodyTag = Tag.getNormalInstance("body");
        Tag divTag = Tag.getNormalInstance("div");
        Tag pTag = Tag.getNormalInstance("p");
        pTag.setValue("some text");

        htmlTag.addChild(headTag);
        headTag.addChild(titleTag);
        htmlTag.addChild(bodyTag);
        bodyTag.addChild(divTag);
        divTag.addChild(pTag);

        Tag clonedTag = htmlTag.clone();

        Assertions.assertEquals(htmlTag, clonedTag);
    }

    @Test
    void testAddChild() {
        Tag htmlTag = Tag.getNormalInstance("html");
        Tag headTag = Tag.getNormalInstance("head");

        htmlTag.addChild(headTag);

        Assertions.assertEquals(List.of(headTag), htmlTag.getChildren());
        Assertions.assertEquals(htmlTag, headTag.getParent());
    }

    @Test
    void testReplaceChild() {
        Tag htmlTag = Tag.getNormalInstance("html");
        Tag headTag = Tag.getNormalInstance("head");
        Tag bodyTag = Tag.getNormalInstance("body");

        htmlTag.addChild(headTag);
        htmlTag.replaceChild(headTag, bodyTag);
        
        Assertions.assertEquals(List.of(bodyTag), htmlTag.getChildren());
        Assertions.assertEquals(htmlTag, bodyTag.getParent());
        Assertions.assertFalse(headTag.hasParent());
    }

    @Test
    void testAddAttribute() {
        Tag pTag = Tag.getNormalInstance("p");
        String key = "id";
        String value = "article-body";

        pTag.addAttribute(key, value);

        Assertions.assertTrue(pTag.hasAttribute(key));
        Assertions.assertEquals(value, pTag.getAttribute(key));
    }

    @Test
    void testRemoveAttribute() {
        Tag pTag = Tag.getNormalInstance("p");
        String key = "id";
        String value = "article-body";

        pTag.addAttribute(key, value);
        pTag.removeAttribute(key);

        Assertions.assertFalse(pTag.hasAttribute(key));
    }

    @Test
    void testGetTagWithId() {
        Tag htmlTag = Tag.getNormalInstance("html");
        Tag headTag = Tag.getNormalInstance("head");
        Tag bodyTag = Tag.getNormalInstance("body");
        
        Tag pTag = Tag.getNormalInstance("p");
        String id = "article-body";
        pTag.addAttribute("id", id);

        htmlTag.addChild(headTag);
        htmlTag.addChild(bodyTag);
        bodyTag.addChild(pTag);

        Assertions.assertEquals(pTag, htmlTag.getTagWithId(id));
    }

    @Test
    void testIsIdEqualToWithCorrectId() {
        Tag pTag = Tag.getNormalInstance("p");
        String key = "id";
        String value = "article-body";

        pTag.addAttribute(key, value);

        Assertions.assertTrue(pTag.isIdEqualTo(value));
    }

    @Test
    void testIsIdEqualToWithIncorrectId() {
        Tag pTag = Tag.getNormalInstance("p");
        String key = "id";
        String value = "article-body";

        pTag.addAttribute(key, value);

        Assertions.assertFalse(pTag.isIdEqualTo("something"));
    }

    @Test
    void testToCode() {
        Tag htmlTag = Tag.getNormalInstance("html");
        Tag headTag = Tag.getNormalInstance("head");
        Tag titleTag = Tag.getNormalInstance("title");
        titleTag.setValue("foo title");
        Tag bodyTag = Tag.getNormalInstance("body");
        Tag divTag = Tag.getNormalInstance("div");
        Tag pTag = Tag.getNormalInstance("p");
        pTag.setValue("some text");

        htmlTag.addChild(headTag);
        headTag.addChild(titleTag);
        htmlTag.addChild(bodyTag);
        bodyTag.addChild(divTag);
        divTag.addChild(pTag);

        Assertions.assertEquals(List.of(
            "<html>",
            "<head>",
            "<title>",
            "foo title",
            "</title>",
            "</head>",
            "<body>",
            "<div>",
            "<p>",
            "some text",
            "</p>",
            "</div>",
            "</body>",
            "</html>"
        ), htmlTag.toCode());
    }

    @Test
    void testAddChildren() {
        Tag htmlTag = Tag.getNormalInstance("html");
        Tag headTag = Tag.getNormalInstance("head");
        Tag bodyTag = Tag.getNormalInstance("body");
        List<Tag> newChildren = List.of(headTag, bodyTag);

        htmlTag.addChildren(newChildren);

        Assertions.assertEquals(newChildren, htmlTag.getChildren());
    }

    @Test
    void testAddStyle() {
        Tag pTag = Tag.getNormalInstance("p");
        String key = "color";
        String value = "red";
        pTag.addStyle(key, value);

        Assertions.assertTrue(pTag.hasStyle(key));
        Assertions.assertEquals(value, pTag.getStyle(key));
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

    protected void assertChildrenOfFirstTagIs(Tag... tags) {
        Assertions.assertEquals(
            Arrays.asList(tags),
            firstTag.getChildren()
        );
    }

    protected void assertSecondTagParentIs(Tag tag) {
        Assertions.assertEquals(tag, secondTag.getParent());
    }
}
