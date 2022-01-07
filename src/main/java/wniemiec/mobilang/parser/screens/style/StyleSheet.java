package wniemiec.mobilang.parser.screens.style;

import java.util.ArrayList;
import java.util.List;

public class StyleSheet {

    List<StyleSheetRule> rules = new ArrayList<>();
    
    public void addRule(StyleSheetRule rule) {
        rules.add(rule);
    }

    @Override
    public String toString() {
        return "StyleSheet [rules=" + rules + "]";
    }

    
}
