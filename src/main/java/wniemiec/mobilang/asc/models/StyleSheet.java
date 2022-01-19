package wniemiec.mobilang.asc.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StyleSheet {

    List<StyleSheetRule> rules = new ArrayList<>();
    
    public void addRule(StyleSheetRule rule) {
        rules.add(rule);
    }

    @Override
    public String toString() {
        return "StyleSheet [rules=" + rules + "]";
    }

    public Map<String, String> getRulesForSelector(List<String> selectors) {
        Map<String, String> selectorRules = new HashMap<>();

        for (StyleSheetRule rule : rules) {
            for (String selector : selectors) {
                if (rule.hasSelector(selector)) {
                    selectorRules.putAll(rule.getDeclarations());
                    //System.out.println();
                }
            }
        }

        return selectorRules;
    }

    
}
