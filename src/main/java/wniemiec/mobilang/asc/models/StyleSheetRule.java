package wniemiec.mobilang.asc.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wniemiec.util.java.StringUtils;


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

    public List<String> toCode() {
        List<String> code = new ArrayList<>();

        code.add(buildHeader());
        code.add(buildBody());

        return code;
    }

    private String buildHeader() {
        StringBuilder code = new StringBuilder();
        
        code.append(StringUtils.implode(selectors, ","));
        code.append(' ');
        code.append('{');

        return code.toString();
    }

    private String buildBody() {
        StringBuilder code = new StringBuilder();

        for (Map.Entry<String, String> entry : declarations.entrySet()) {
            code.append(entry.getKey());
            code.append(':');
            code.append(' ');
            code.append(entry.getValue());
            code.append(';');
            code.append('\n');
        }

        code.append('}');

        return code.toString();
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
