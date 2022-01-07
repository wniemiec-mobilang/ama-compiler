package wniemiec.mobilang.parser.screens.style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StyleSheetRule {

    List<String> selectors = new ArrayList<>();
    Map<String, String> declarations = new HashMap<>();

    public void addSelector(String selector) {
        selectors.add(selector);
    }

    public void addDeclaration(String property, String value) {
        declarations.put(property, value);
    }

    @Override
    public String toString() {
        return "StyleSheetRule [declarations=" + declarations + ", selectors=" + selectors + "]";
    }

    
}
