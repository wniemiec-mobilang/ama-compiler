package wniemiec.mobilang.ama.models.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Stack;

import org.junit.jupiter.api.Test;

class TagTest {

    @Test
    void testClone() {
        Tag htmlTag = new Tag("html");
        Tag headTag = new Tag("head");
        Tag titleTag = new Tag("title", new HashMap<>(), "foo title");
        Tag bodyTag = new Tag("body");
        Tag divTag = new Tag("div");
        Tag pTag = new Tag("p", new HashMap<>(), "some text");

        htmlTag.addChild(headTag);
        headTag.addChild(titleTag);
        htmlTag.addChild(bodyTag);
        bodyTag.addChild(divTag);
        divTag.addChild(pTag);

        Tag clonedTag = htmlTag.clone();

        assertEquals(htmlTag, clonedTag);
    }
}
