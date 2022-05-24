package wniemiec.mobilang.ama.models.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class TagTest {
    // TODO: refactor this code

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

        assertEquals(htmlTag, clonedTag);
    }
}
