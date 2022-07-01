package wniemiec.mobilex.ama.parser.screens.structure;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.tag.Tag;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing structure node from MobiLang AST.
 */
public class StructureParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String structureNodeContent;
    private final boolean astFromMobilang;
    private final TagParser tagParser;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    /**
     * Structure parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST with html tag
     * @param       structureNode Structure node
     */
    public StructureParser(SortedMap<String, List<Node>> ast, Node structureNode) {
        this(
            ast.get(structureNode.getId()).get(0).getLabel(), 
            true
        );
    }

    /**
     * Structure parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST with body tag
     */
    public StructureParser(String ast) {
        this(ast, false);
    }

    private StructureParser(String structureNodeContent, boolean astFromMobilang) {
        this.structureNodeContent = structureNodeContent;
        this.astFromMobilang = astFromMobilang;
        tagParser = new TagParser();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Tag parse() throws ParseException {
        JSONObject bodyTag = getBodyTag();
        
        return tagParser.parseBodyTag(bodyTag);
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

        if (hasBodyTag(htmlTag)) {
            return getBodyTagFromHtmlTag(htmlTag);    
        }
        
        return htmlTag;
    }

    private JSONObject getHtmlTag(JSONObject structureJson) throws ParseException {
        JSONObject htmlTagContent = structureJson
            .getJSONObject("content")
            .getJSONArray("children")
            .getJSONObject(0);

        validateHtmlTag(htmlTagContent);
        
        return htmlTagContent;
    }

    private void validateHtmlTag(JSONObject htmlTagContent) throws ParseException {
        if (!htmlTagContent.getJSONObject("content").getString("name").equals("html")) {
            throw new ParseException("HTML tag not found");
        }
    }

    private boolean hasBodyTag(JSONObject htmlTag) {
        return htmlTag
            .getJSONObject("content")
            .getJSONArray("children")
            .getJSONObject(0)
            .getJSONObject("content")
            .getString("name")
            .equals("body");
    }

    private JSONObject getBodyTagFromHtmlTag(JSONObject htmlTagContent) 
    throws ParseException {
        JSONObject bodyTag = htmlTagContent
            .getJSONObject("content")
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
}

