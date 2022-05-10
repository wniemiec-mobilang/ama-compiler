package wniemiec.mobilang.ama.parser.screens.style;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.Node;
import wniemiec.mobilang.ama.models.Style;


/**
 * Responsible for parsing style node from MobiLang AST.
 */
public class StyleParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String styleNodeContent;
    private final CssRulesParser cssRulesParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Style parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       styleNode Style node
     */
    public StyleParser(SortedMap<String, List<Node>> ast, Node styleNode) {
        styleNodeContent = ast.get(styleNode.getId()).get(0).getLabel();
        cssRulesParser = new CssRulesParser();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Style parse() {
        JSONArray cssRules = parseJson(new JSONObject(styleNodeContent));
        
        return cssRulesParser.parseRules(cssRules);
    }
    
    private JSONArray parseJson(JSONObject jsonStyle) {
        return jsonStyle
            .getJSONObject("stylesheet")
            .getJSONArray("rules");
    }
}