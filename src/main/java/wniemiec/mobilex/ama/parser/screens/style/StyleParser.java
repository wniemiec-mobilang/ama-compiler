package wniemiec.mobilex.ama.parser.screens.style;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.Style;


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
        styleNodeContent = extractStyleContentFrom(ast, styleNode);
        cssRulesParser = new CssRulesParser();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private String extractStyleContentFrom(SortedMap<String, List<Node>> ast, Node styleNode) {
        if (!ast.containsKey(styleNode.getId())) {
            return "{}";
        }

        return ast.get(styleNode.getId()).get(0).getLabel();
    }

    public Style parse() {
        if (isEmptyJson(styleNodeContent)) {
            return new Style();
        }

        JSONArray cssRules = parseJson(new JSONObject(styleNodeContent));
        
        return cssRulesParser.parseRules(cssRules);
    }
    
    private boolean isEmptyJson(String jsonContent) {
        return jsonContent.equals("{}");
    }

    private JSONArray parseJson(JSONObject jsonStyle) {
        return jsonStyle
            .getJSONObject("stylesheet")
            .getJSONArray("rules");
    }
}
