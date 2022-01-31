package wniemiec.mobilang.asc.parser.screens.style;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.StyleSheetRule;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing style node from MobiLang AST.
 */
public class StyleParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Node styleNode;
    private Style style;


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
        styleNode = ast.get(styleNode.getId()).get(0);
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Style parse() {
        style = new Style();

        parseJson(getStyleNodeContent());

        return style;
    }

    private String getStyleNodeContent() {
        return styleNode.getLabel();
    }

    private void parseJson(String json) {
        JSONArray cssRules = getCssRules(new JSONObject(json));
        
        parseCssRules(cssRules);
    }

    private JSONArray getCssRules(JSONObject styleRoot) {
        return styleRoot
            .getJSONObject("stylesheet")
            .getJSONArray("rules");
    }

    private void parseCssRules(JSONArray cssRules) {
        for (int i = 0; i < cssRules.length(); i++) {
            parseCssRule(cssRules.getJSONObject(i));
        }
    }   

    private void parseCssRule(JSONObject cssRule) {
        StyleSheetRule rule = new StyleSheetRule();
        
        parseRuleSelectors(rule, cssRule);
        parseRuleDeclarations(rule, cssRule);

        style.addRule(rule);
    }

    private void parseRuleSelectors(StyleSheetRule rule, JSONObject cssRule) {
        JSONArray selectors = cssRule.getJSONArray("selectors");

        for (int i = 0; i < selectors.length(); i++) {
            rule.addSelector(selectors.getString(i));
        }
    }

    private void parseRuleDeclarations(StyleSheetRule rule, JSONObject cssRule) {
        JSONArray declarations = cssRule.getJSONArray("declarations");

        for (int i = 0; i < declarations.length(); i++) {
            String property = getDeclarationProperty(declarations.getJSONObject(i));
            String value = getDeclarationValue(declarations.getJSONObject(i));

            rule.addDeclaration(property, value);
        }
    }

    private String getDeclarationProperty(JSONObject declaration) {
        return declaration.getString("property");
    }

    private String getDeclarationValue(JSONObject declaration) {
        return declaration.getString("value");
    }
}
