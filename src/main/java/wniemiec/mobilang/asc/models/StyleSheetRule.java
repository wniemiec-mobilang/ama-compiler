package wniemiec.mobilang.asc.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Responsible for representing style rule.
 */
public class StyleSheetRule {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<String> selectors;
    private final Map<String, String> declarations;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public StyleSheetRule() {
        selectors = new ArrayList<>();
        declarations = new HashMap<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void addSelector(String selector) {
        selectors.add(selector);
    }

    public void addDeclaration(String property, String value) {
        declarations.put(property, value);
    }

    public boolean hasSelector(String selector) {
        return selectors.contains(selector);
    }

    @Override
    public String toString() {
        return "StyleSheetRule [" 
                + "declarations=" + declarations 
                + ", selectors=" + selectors 
            + "]";
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Map<String, String> getDeclarations() {
        return declarations;
    }
}
