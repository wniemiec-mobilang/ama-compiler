package wniemiec.mobilang.asc.parser.screens.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.tag.RawTag;
import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing tags from structure node from MobiLang AST.
 */
class TagParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Stack<RawTag> tagsToParse;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public TagParser() {
        tagsToParse = new Stack<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Tag parseBodyTag(JSONObject jsonBodyTag) {
        Tag bodyTag = null;
        
        
        tagsToParse.push(new RawTag(jsonBodyTag, null));

        while (!tagsToParse.empty()) {
            RawTag currentRawTag = tagsToParse.pop();

            if (isValue(currentRawTag)) {
                parseValue(currentRawTag);
            }
            else {
                bodyTag = parseTag(bodyTag, currentRawTag);
            }
        }

        return bodyTag;
    }

    private boolean isValue(RawTag currentRawTag) {
        return getTagType(currentRawTag).equals("text");
    }

    private String getTagType(RawTag currentRawTag) {
        JSONObject currentTag = currentRawTag.getJsonObject();
        
        return currentTag.getString("nodeType");
    }

    private void parseValue(RawTag currentRawTag) {
        String tagContent = extractTagContent(currentRawTag)
            .getJSONObject("value")
            .getString("content");
        
        if (!isEmpty(tagContent)) {
            currentRawTag.getParent().setValue(tagContent);
        }
    }

    private boolean isEmpty(String tagContent) {
        return tagContent.matches("\"[\\s\\t]*(\")?");
    }

    private Tag parseTag(Tag bodyTag, RawTag currentRawTag) {
        Tag parsedTag = parseTagContent(extractTagContent(currentRawTag));

        if (currentRawTag.hasParent()) {
            currentRawTag.addChild(parsedTag);
        }
        
        if (bodyTag == null) {
            bodyTag = parsedTag;
        }

        return bodyTag;
    }

    private JSONObject extractTagContent(RawTag currentRawTag) {
        return currentRawTag.getJsonObject().getJSONObject("content");
    }

    private Tag parseTagContent(JSONObject tagContent) {
        Tag parsedTag = new Tag(
            extractTagName(tagContent), 
            extractTagAttributes(tagContent)
        );
        
        if (hasChildren(tagContent)) {
            parseTagChildren(parsedTag, tagContent);
        }
        
        return parsedTag;
    }

    private String extractTagName(JSONObject tagContent) {
        String tagName = "";
        
        if (tagContent.has("name")) {
            tagName = tagContent.getString("name");
        }

        return tagName;
    }

    private Map<String, String> extractTagAttributes(JSONObject tag) {
        if (!tag.has("attributes")) {
            return new HashMap<>();
        }

        Map<String, String> attributes = new HashMap<>();
        JSONArray jsonAttributes = tag.getJSONArray("attributes");

        for (int i = 0; i < jsonAttributes.length(); i++) {
            String key = extractKeyFromAttribute(jsonAttributes.getJSONObject(i));
            String value = extractValueFromAttribute(jsonAttributes.getJSONObject(i));
            
            attributes.put(key, value);
        }

        return attributes;
    }

    private String extractKeyFromAttribute(JSONObject attributeObject) {
        return attributeObject.getJSONObject("key").getString("content");
    }

    private String extractValueFromAttribute(JSONObject attributeObject) {
        return attributeObject.getJSONObject("value").getString("content");
    }

    private boolean hasChildren(JSONObject jsonTag) {
        return jsonTag.has("children");
    }

    private void parseTagChildren(Tag parent, JSONObject tagContent) {
        JSONArray tagChildren = tagContent.getJSONArray("children");

        for (int i = tagChildren.length()-1; i >= 0; i--) {
            RawTag childRawTag = new RawTag(
                tagChildren.getJSONObject(i), 
                parent
            );
            
            tagsToParse.push(childRawTag);
        }
    }
}
