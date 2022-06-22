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
        Tag htmlTag = Tag.getNormalInstance("html");
       
        Assertions.assertEquals(List.of(
            "<html>",
            "</html>"
        ), tagCoder.toCode(htmlTag));
    }
}
