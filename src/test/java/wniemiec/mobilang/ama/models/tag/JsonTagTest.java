package wniemiec.mobilang.ama.models.tag;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonTagTest {

    JsonTag jsonTag;
    
    @Test
    void testReplaceTagTo() {
        Tag divTag = Tag.getNormalInstance("div");
        Tag pTag = Tag.getNormalInstance("p");
        JSONObject jsonObject = new JSONObject();

        jsonTag = new JsonTag(jsonObject, divTag);

        jsonTag.addChild(pTag);

        Assertions.assertEquals(
            List.of(pTag),
            jsonTag.getParent().getChildren()
        );
        Assertions.assertEquals(divTag, pTag.getParent());
    }

    @Test
    void testGetTag() {
        Tag divTag = Tag.getNormalInstance("div");
        JSONObject jsonObject = new JSONObject();

        jsonTag = new JsonTag(jsonObject, divTag);
        
        Assertions.assertEquals(jsonObject, jsonTag.getTag());
    }
}
