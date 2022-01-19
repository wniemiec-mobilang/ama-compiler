package wniemiec.mobilang.asc.models;

import org.json.JSONObject;

public class RawTag {

    JSONObject jsonObject;
    Tag parent;

    public RawTag(JSONObject jsonObject, Tag parent) {
        this.jsonObject = jsonObject;
        this.parent = parent;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public Tag getParent() {
        return parent;
    }

}