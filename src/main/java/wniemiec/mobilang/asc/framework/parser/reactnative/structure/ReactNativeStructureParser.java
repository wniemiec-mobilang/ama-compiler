package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import java.util.List;
import java.util.Stack;

import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.models.tag.Tag;
import wniemiec.mobilang.asc.models.tag.TagContainer;
import wniemiec.mobilang.asc.parser.exception.FactoryException;


/**
 * Responsible for parsing structure node from a screen, creating a React 
 * Native tag from it.
 */
public class ReactNativeStructureParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Stack<TagContainer> tagsToParse;
    private final Tag structureRoot;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Structure node parser, creating a React Native tag from it.
     * 
     * @param       root Tag to be parsed.
     */
    public ReactNativeStructureParser(Tag root) {
        this.structureRoot = root;
        tagsToParse = new Stack<>();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Parses the tag, creating a React Native tag from it.
     * 
     * @return      React native tag
     * 
     * @throws      ParseException If parsing failed
     */
    public Tag parse() throws ParseException {
        Tag reactNativeTag = convertTagToReactNativeTag(structureRoot);

        tagsToParse = new Stack<>();
        
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
        Tag parsedTag = convertTagToReactNativeTag(unparsedTag.getTag());

        if (unparsedTag.hasParent()) {
            unparsedTag.replaceTagTo(parsedTag);
        }

        parseTagChildren(parsedTag);
    }

    private Tag convertTagToReactNativeTag(Tag tag) throws ParseException {
        if (isReactNativeTag(tag)) {
            return tag;
        }
        
        String tagType = tag.getName();
        
        try {
            return ReactNativeTagFactory
                .get(tagType)
                .parse(tag);
        } 
        catch (FactoryException e) {
            throw new ParseException("No suitable conversion for tag with name: " + tagType);
        }
    }

    private boolean isReactNativeTag(Tag tag) {
        if (tag.getName().isBlank()) {
            return false;
        }
        
        return Character.isUpperCase(tag.getName().charAt(0));
    }
}
