package wniemiec.mobilang.asc.parser.screens.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.RawTag;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing structure node from MobiLang AST.
 */
public class StructureParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String structureNodeContent;
    private boolean astFromMobilang;
    private Stack<RawTag> tagsToParse;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    /**
     * Structure parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       structureNode Structure node
     */
    public StructureParser(SortedMap<String, List<Node>> ast, Node structureNode) {
        structureNodeContent = ast.get(structureNode.getId()).get(0).getLabel();
        astFromMobilang = true;
    }

    public StructureParser(String ast) {
        this.structureNodeContent = ast;
        astFromMobilang = false;
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Tag parse() throws ParseException {
        JSONObject bodyTag = getBodyTag();
        
        return parseBodyTag(bodyTag);
    }

    private JSONObject getBodyTag() throws ParseException {
        if (astFromMobilang) {  
            return getBodyTagFromStructureNodeContent();
        }

        return new JSONObject(structureNodeContent);
    }

    private JSONObject getBodyTagFromStructureNodeContent() 
    throws ParseException {
        try {
            return getBodyTagFromStructureNode(new JSONObject(structureNodeContent));
        } 
        catch (Exception e) {
            throw new ParseException("JSON parsing - " + e.getMessage());
        }
    }

    private JSONObject getBodyTagFromStructureNode(JSONObject json) 
    throws ParseException {
        JSONObject htmlTag = getHtmlTag(json);
        
        return getBodyTagFromHtmlTag(htmlTag);
    }

    private JSONObject getHtmlTag(JSONObject structureJson) throws ParseException {
        JSONObject htmlTagContent = structureJson
            .getJSONObject("content")
            .getJSONArray("children")
            .getJSONObject(0)
            .getJSONObject("content");

        validateHtmlTag(htmlTagContent);
        
        return htmlTagContent;
    }

    private void validateHtmlTag(JSONObject htmlTagContent) throws ParseException {
        if (!htmlTagContent.getString("name").equals("html")) {
            throw new ParseException("HTML tag not found");
        }
    }

    private JSONObject getBodyTagFromHtmlTag(JSONObject htmlTagContent) 
    throws ParseException {
        JSONObject bodyTag = htmlTagContent
            .getJSONArray("children")
            .getJSONObject(0);

        validateBodyTag(bodyTag);

        return bodyTag;
    }

    private void validateBodyTag(JSONObject bodyTag) throws ParseException {
        JSONObject bodyTagContent = bodyTag.getJSONObject("content");

        if (!bodyTagContent.getString("name").equals("body")) {
            throw new ParseException("BODY tag not found");
        }
    }

    private Tag parseBodyTag(JSONObject jsonBodyTag) {
        Tag bodyTag = null;
        
        tagsToParse = new Stack<>();
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

