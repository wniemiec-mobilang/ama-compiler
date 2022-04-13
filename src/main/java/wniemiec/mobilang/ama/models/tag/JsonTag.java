package wniemiec.mobilang.ama.models.tag;

import org.json.JSONObject;


/**
 * Responsible for representing a json tag in json format along with its parent.
 */
public class JsonTag {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final JSONObject jsonObject;
    private final Tag parent;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public JsonTag(JSONObject jsonObject, Tag parent) {
        this.jsonObject = jsonObject;
        this.parent = parent;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void addChild(Tag child) {
        if (!hasParent()) {
            return;
        }

        parent.addChild(child);
    }

    public boolean hasParent() {
        return (parent != null);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public JSONObject getTag() {
        return jsonObject;
    }

    public Tag getParent() {
        return parent;
    }
}