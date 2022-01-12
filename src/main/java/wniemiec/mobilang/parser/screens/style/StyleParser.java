package wniemiec.mobilang.parser.screens.style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilang.data.Node;
import wniemiec.mobilang.parser.Parser;
import wniemiec.mobilang.parser.screens.structure.Tag;

public class StyleParser /*implements Parser*/ {

    private String contentNode;

    public StyleParser(SortedMap<String, List<Node>> tree, Node styleNode) {
        contentNode = tree.get(styleNode.getId()).get(0).getLabel();
    }

    
    public StyleSheet parse() throws Exception {
        //System.out.println("-----< STYLE PARSER >-----");
        //System.out.println(contentNode);
        //System.out.println("-------------------------------\n");
        return parseJson(contentNode);
    }

    private StyleSheet parseJson(String json) throws Exception {
        StyleSheet styleSheet = new StyleSheet();
        JSONObject obj = new JSONObject(json);
        //JSONObject root = obj.getJSONObject("content");
        //JSONArray child = root.getJSONArray("children");
        
        JSONArray cssRules = obj
            .getJSONObject("stylesheet")
            .getJSONArray("rules");

        for (int i = 0; i < cssRules.length(); i++) {
            StyleSheetRule rule = parseCssRule(cssRules.getJSONObject(i));
            styleSheet.addRule(rule);
        }
        

        //System.out.println(cssRules);

        //System.out.println(styleSheet);

        return styleSheet;
    }


    private StyleSheetRule parseCssRule(JSONObject cssRule) {
        StyleSheetRule rule = new StyleSheetRule();
        JSONArray selectors = cssRule.getJSONArray("selectors");
        JSONArray declarations = cssRule.getJSONArray("declarations");
        
        parseRuleSelectors(rule, selectors);
        parseRuleDeclarations(rule, declarations);
        
        //System.out.println("Selectors: " + parsedSelectors);
        //System.out.println("Declarations: " + parsedCssDeclarations);

        return rule;
    }


    private void parseRuleSelectors(StyleSheetRule rule, JSONArray selectors) {
        for (int i = 0; i < selectors.length(); i++) {
            String selector = selectors.getString(i);
            rule.addSelector(selector);
        }
    }


    private void parseRuleDeclarations(StyleSheetRule rule, JSONArray declarations) {
        for (int i = 0; i < declarations.length(); i++) {
            String property = declarations.getJSONObject(i).getString("property");
            String value = declarations.getJSONObject(i).getString("value");

            rule.addDeclaration(property, value);
        }
    }
}
