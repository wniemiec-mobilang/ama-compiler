package wniemiec.mobilang.asc.framework.parser.reactnative.style;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.asc.models.Tag;


/**
 * Responsible for parsing selectors from a tag based on React Native framework.
 */
class ReactNativeSelectorParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String ID_LABEL;
    private static final String CLASS_LABEL;
    private Tag tag;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        ID_LABEL = "id";
        CLASS_LABEL = "class";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeSelectorParser(Tag tag) {
        this.tag = tag;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Parses tag selectors.
     * 
     * @return      Selectors
     */
    public List<String> parse() {
        List<String> selectors = new ArrayList<>();

        buildTagAttributeSelectors(selectors);
        buildDefaultSelectors(selectors);
        buildTagHierarchySelectors(selectors);

        if (isBodyTag()) {
            buildBodyTagSelectors(selectors);
        }
        
        return selectors;
    }

    private void buildTagAttributeSelectors(List<String> selectors) {
        // TODO: Add compatibility with id and class mixed

        if (hasIdAttribute(tag)) {
            parseIdSelector(selectors);
        }

        if (hasClassAttribute(tag)) { 
            parseClassSelector(selectors);
        }
    }

    private boolean hasIdAttribute(Tag tag) {
        return tag.hasAttribute(ID_LABEL);
    }

    private void parseIdSelector(List<String> selectors) {
        Tag parent = tag.getFather();
        String lastFatherSelector = "";

        selectors.add("#" + tag.getAttribute("id"));

        while (parent != null) {
            lastFatherSelector = parseParent(
                selectors, 
                parent, 
                lastFatherSelector
            );
            parent = parent.getFather();
        }
    }

    private String parseParent(
        List<String> selectors, 
        Tag parent, 
        String lastFatherSelector
    ) {
        String selectorCode = lastFatherSelector;

        if (hasIdAttribute(parent)) {
            selectorCode = parseParentIdAttribute(
                selectors, 
                parent, 
                selectorCode
            );
        }

        if (hasClassAttribute(parent)) {
            selectorCode = parseParentClassAttribute(
                selectors, 
                parent, 
                selectorCode
            );
        }
        return selectorCode;
    }

    private String parseParentIdAttribute(
        List<String> selectors, 
        Tag parent, 
        String lastFatherSelector
    ) {
        StringBuilder selectionCode = new StringBuilder();

        selectionCode.append('#');
        selectionCode.append(parent.getAttribute("id"));
        selectionCode.append(' ');
        selectionCode.append(lastFatherSelector);

        selectors.add(selectionCode.toString() + tag.getName());
        
        return selectionCode.toString();
    }

    private String parseParentClassAttribute(
        List<String> selectors, 
        Tag parent, 
        String lastFatherSelector
    ) {
        StringBuilder selectionCode = new StringBuilder();

        selectionCode.append('.');
        selectionCode.append(parent.getAttribute("class"));
        selectionCode.append(' ');
        selectionCode.append(lastFatherSelector);

        selectors.add(selectionCode.toString() + tag.getName());
        
        return selectionCode.toString();
    }

    private boolean hasClassAttribute(Tag tag) {
        return tag.hasAttribute(CLASS_LABEL);
    }

    private void parseClassSelector(List<String> selectors) {
        Tag parent = tag.getFather();
        String lastFatherSelector = "";

        selectors.add("." + tag.getAttribute("class"));

        while (parent != null) {
            lastFatherSelector = parseParent(
                selectors, 
                parent, 
                lastFatherSelector
            );
            parent = parent.getFather();
        }
    }

    private void buildDefaultSelectors(List<String> selectors) {
        selectors.add(tag.getName());
    }

    private void buildTagHierarchySelectors(List<String> selectors) {
        Tag parent = tag.getFather();
        String lastFatherSelector = "";
        
        while (parent != null) {
            lastFatherSelector = parseHierarchyParent(
                selectors, 
                parent, 
                lastFatherSelector
            );
            parent = parent.getFather();
        }
    }

    private String parseHierarchyParent(
        List<String> selectors, 
        Tag parent, 
        String lastFatherSelector
    ) {
        StringBuilder selectionCode = new StringBuilder();

        selectionCode.append(parent.getName());
        selectionCode.append(' ');
        selectionCode.append(lastFatherSelector);

        selectors.add(selectionCode.toString() + tag.getName());
        
        return selectionCode.toString();
    }

    private boolean isBodyTag() {
        return tag.getName().equals("body");
    }

    private void buildBodyTagSelectors(List<String> selectors) {
        selectors.add("*");
    }
}
