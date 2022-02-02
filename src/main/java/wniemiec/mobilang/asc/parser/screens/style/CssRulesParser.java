package wniemiec.mobilang.asc.parser.screens.style;

import org.json.JSONArray;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.StyleSheetRule;


/**
 * Responsible for parsing CSS rules from style node from MobiLang AST.
 */
class CssRulesParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Style style;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public CssRulesParser() {
        style = new Style();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Style parseRules(JSONArray cssRules) {
        for (int i = 0; i < cssRules.length(); i++) {
            parseCssRule(cssRules.getJSONObject(i));
        }

        return style;
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
