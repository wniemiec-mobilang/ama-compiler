package wniemiec.mobilang.asc.parser.screens.structure;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.tag.Tag;
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
    private TagParser tagParser;


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
}

