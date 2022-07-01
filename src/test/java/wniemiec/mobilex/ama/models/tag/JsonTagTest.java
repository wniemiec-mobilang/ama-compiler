package wniemiec.mobilex.ama.models.tag;

import java.util.Arrays;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.tag.JsonTag;
import wniemiec.mobilex.ama.models.tag.Tag;


class JsonTagTest extends TagTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private JsonTag jsonTag;
    private JSONObject jsonObject;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
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
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
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

    private void assertGetTagIsCorrect() {
        Assertions.assertEquals(jsonObject, jsonTag.getTag());
    }
}
