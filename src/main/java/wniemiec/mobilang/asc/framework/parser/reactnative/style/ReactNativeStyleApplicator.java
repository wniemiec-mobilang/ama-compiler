package wniemiec.mobilang.asc.framework.parser.reactnative.style;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for applying screen style in screen tags of React Native 
 * framework.
 */
public class ReactNativeStyleApplicator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Style style;
    private Stack<Tag> tagsToParse;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Screen style applicator.
     * 
     * @param       style StyleSheet
     */
    public ReactNativeStyleApplicator(Style style) {
        this.style = style;
        tagsToParse = new Stack<>();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Applies screen style in a tag.
     * 
     * @param       tag Tag to be styled
     */
    public void apply(Tag tag) {
        tagsToParse = new Stack<>();
        tagsToParse.add(tag);

        while (!tagsToParse.empty()) {
            Tag currentTag = tagsToParse.pop();
            
            parseTag(currentTag);
            parseChildren(currentTag);
        }
    }

    private void parseTag(Tag tag) {
        List<String> selectors = parseSelectors(tag);

        parseRules(tag, selectors);
    }

    private List<String> parseSelectors(Tag tag) {
        ReactNativeSelectorParser parser = new ReactNativeSelectorParser(tag);
        
        return parser.parse();
    }

    private void parseRules(Tag tag, List<String> selectors) {
        Map<String, String> rules = style.getRulesForSelector(selectors);
        
        tag.setStyle(rules);
    }

    private void parseChildren(Tag tag) {
        for (Tag child : tag.getChildren()) {
            tagsToParse.add(child);
        }
    }
}
