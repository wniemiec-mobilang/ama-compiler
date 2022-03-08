package wniemiec.mobilang.asc.framework.parser.reactnative.style;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for filtering styles, leaving only those who are compatible with
 * the React Native framework.
 */
public class ReactNativeStyleFilter {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Set<String> INCOMPATIBLE_ATTRIBUTES;
    private final Tag structure;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        INCOMPATIBLE_ATTRIBUTES = generateIncompatibleAttributes();
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeStyleFilter(Tag structure) {
        this.structure = structure;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private static Set<String> generateIncompatibleAttributes() {
        Set<String> attributes = new HashSet<>();

        attributes.add("font-family");

        return attributes;
    }

    public void filter() {
        Stack<Tag> tagsToParse = new Stack<>();
        tagsToParse.add(structure);

        while (!tagsToParse.empty()) {
            Tag currentTag = tagsToParse.pop();
            
            parseTag(currentTag);

            for (Tag child : currentTag.getChildren()) {
                tagsToParse.add(child);
            }
        }
    }

    private void parseTag(Tag tag) {
        Map<String, String> parsedStyle = new HashMap<>();

        for (Map.Entry<String, String> entry : tag.getStyle().entrySet()) {
            if (isCompatible(entry.getKey())) {
                parsedStyle.put(entry.getKey(), entry.getValue());
            }
        }
        
        tag.setStyle(parsedStyle);
    }

    private boolean isCompatible(String key) {
        return !INCOMPATIBLE_ATTRIBUTES.contains(key);
    }
}
