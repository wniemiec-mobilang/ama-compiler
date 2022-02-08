package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.TagBuilder;
import wniemiec.mobilang.asc.models.TagContainer;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.exception.ParserFactoryException;


/**
 * Responsible for parsing structure node from a screen.
 */
public class ReactNativeStructureParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Stack<TagContainer> tagsToParse;
    private Tag structureRoot;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeStructureParser(Tag root) {
        this.structureRoot = root;
        tagsToParse = new Stack<>();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Tag parse() throws ParseException {
        Tag reactNativeTag = convertTagToReactNativeTag(structureRoot);

        parseTagChildren(reactNativeTag);
        
        while (!tagsToParse.empty()) {
            TagContainer unparsedTag = tagsToParse.pop();
            
            parseTag(unparsedTag);
        }

        return reactNativeTag;
    }

    private void parseTagChildren(Tag tag) {
        if (tag == null) {
            return;
        }

        List<Tag> children = tag.getChildren();
        
        for (int i = children.size()-1; i >= 0; i--) {
            TagContainer childTag = new TagContainer(children.get(i), tag);
            
            tagsToParse.push(childTag);
        }
    }

    private void parseTag(TagContainer unparsedTag) throws ParseException {
        Tag parsedTag = convertTagToReactNativeTag(unparsedTag.getChild());

        if (unparsedTag.hasParent()) {
            unparsedTag.addSibling(parsedTag);
        }

        parseTagChildren(unparsedTag.getChild());
    }

    private Tag convertTagToReactNativeTag(Tag tag) throws ParseException {
        String tagType = tag.getName();
        
        try {
            return ReactNativeTagFactory
                .get(tagType)
                .parse(tag);
        } 
        catch (ParserFactoryException e) {
            throw new ParseException("No suitable conversion for tag with name: " + tagType);
        }
    }
}
