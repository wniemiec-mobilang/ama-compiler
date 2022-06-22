package wniemiec.mobilang.ama.models.tag;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TagCoderTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TagCoder tagCoder;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        tagCoder = new TagCoder();
    }
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testVoidTagWithoutValue() {
        Tag imgTag = Tag.getVoidInstance("img");
       
        Assertions.assertEquals(List.of(
            "<img/>"
        ), tagCoder.toCode(imgTag));
    }

    @Test
    void testVoidTagWithValue() {
        Tag imgTag = Tag.getVoidInstance("img");
        imgTag.setValue("wrong value");
       
        Assertions.assertEquals(List.of(
            "<img/>"
        ), tagCoder.toCode(imgTag));
    }

    @Test
    void testVoidTagWithChildren() {
        Tag imgTag = Tag.getVoidInstance("img");
        Tag htmlTag = Tag.getNormalInstance("html");

        htmlTag.addChild(imgTag);
       
        Assertions.assertEquals(List.of(
            "<img/>"
        ), tagCoder.toCode(imgTag));
    }

    @Test
    void testNormalTagWithValue() {
        Tag pTag = Tag.getNormalInstance("p");
        pTag.setValue("some text");
       
        Assertions.assertEquals(List.of(
            "<p>",
            "some text",
            "</p>"
        ), tagCoder.toCode(pTag));
    }
}
