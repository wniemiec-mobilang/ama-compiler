package wniemiec.mobilang.ama.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Responsible for representing data from style tag.
 */
public class Style {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<StyleSheetRule> rules;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Style() {
        rules = new ArrayList<>();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void addRule(StyleSheetRule rule) {
        rules.add(rule);
    }

    public Map<String, String> getRulesForSelector(List<String> selectors) {
        Map<String, String> selectorRules = new HashMap<>();

        for (StyleSheetRule rule : rules) {
            for (String selector : selectors) {
                if (rule.hasSelector(selector)) {
                    selectorRules.putAll(rule.getDeclarations());
                }
            }
        }

        return selectorRules;
    }

    public List<String> toCode() {
        List<String> code = new ArrayList<>();

        for (StyleSheetRule rule : rules) {
            code.addAll(rule.toCode());
        }

        return code;
    }

    @Override
    public String toString() {
        return "StyleSheet [rules=" + rules + "]";
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    List<StyleSheetRule> getRules() {
        return rules;
    }
}
