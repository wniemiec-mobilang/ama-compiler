package wniemiec.mobilang.asc.parser.screens.style;

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

    public boolean hasSelector(String selector) {
        /*
        for (String s : selectors) {
            if (s.contains(selector)) {
                return true;
            }
        }

        return false;
        */
        //System.out.println(selectors);
        //System.out.println(selector + ": " + selectors.contains(selector));
        return selectors.contains(selector);
    }

    public Map<String, String> getDeclarations() {
        return declarations;
    }
}
