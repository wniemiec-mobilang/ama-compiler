package wniemiec.mobilang.ama.models.tag;

import java.util.Arrays;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonTagTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Tag firstTag;
    private Tag secondTag;
    private JsonTag jsonTag;
    private JSONObject jsonObject;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        firstTag = null;
        secondTag = null;
        jsonTag = null;
        jsonObject = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testReplaceTagTo() {
        withFirstTag(Tag.getNormalInstance("div"));
        withSecondTag(Tag.getNormalInstance("p"));
        withJsonObject(new JSONObject());
        buildJsonTagUsingAsParent(firstTag);
        addChildInJsonTag(secondTag);
        assertChildrenOfJsonTagParentIs(secondTag);
        assertSecondTagParentIs(firstTag);
    }

    @Test
    void testGetTag() {
        withFirstTag(Tag.getNormalInstance("div"));
        withJsonObject(new JSONObject());
        buildJsonTagUsingAsParent(firstTag);
        assertGetTagIsCorrect();

        Tag divTag = Tag.getNormalInstance("div");
        JSONObject jsonObject = new JSONObject();

        jsonTag = new JsonTag(jsonObject, divTag);
        
        Assertions.assertEquals(jsonObject, jsonTag.getTag());
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withFirstTag(Tag tag) {
        firstTag = tag;
    }

    private void withSecondTag(Tag tag) {
        secondTag = tag;
    }

    private void withJsonObject(JSONObject json) {
        jsonObject = json;
    }

    private void buildJsonTagUsingAsParent(Tag tag) {
        jsonTag = new JsonTag(jsonObject, tag);
    }

    private void addChildInJsonTag(Tag tag) {
        jsonTag.addChild(tag);
    }

    private void assertChildrenOfJsonTagParentIs(Tag... tags) {
        Assertions.assertEquals(
            Arrays.asList(tags),
            jsonTag.getParent().getChildren()
        );
    }

    private void assertSecondTagParentIs(Tag tag) {
        Assertions.assertEquals(tag, secondTag.getParent());
    }

    private void assertGetTagIsCorrect() {
        Assertions.assertEquals(jsonObject, jsonTag.getTag());
    }
}
