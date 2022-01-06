package wniemiec.mobilang.parser.screens.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ReactNativeStructureParser {

    Map<String, TagBuilder> mapping = new HashMap<>();
    Tag root;

    public ReactNativeStructureParser(Tag root) {
        this.root = root;
        mapping.put("body", TagBuilder.of("View"));
        mapping.put("div", TagBuilder.of("View"));
        mapping.put("button", TagBuilder.of("TouchableOpacity"));
        mapping.put("a", TagBuilder.of("TouchableHighlight"));
    }

    
    // Returns root tag
    public Tag parse() {
        //parseTag(root);
        Tag rnRoot = convertTagToReactNativeTag(root);
        Stack<TagContainer> tagsToParse = new Stack<>();

        for (Tag child : root.getChildren()) {
            tagsToParse.push(new TagContainer(child, rnRoot));
        }
        
        while (!tagsToParse.empty()) {
            TagContainer unparsedTag = tagsToParse.pop();
            
            Tag parsedTag = convertTagToReactNativeTag(unparsedTag.getChild());
            unparsedTag.getParent().addChild(parsedTag);
            
            for (Tag child : unparsedTag.getChild().getChildren()) {
                tagsToParse.push(new TagContainer(child, parsedTag));
            }
        }

        return rnRoot;
    }

    private Tag convertTagToReactNativeTag(Tag tag) {
        //Tag rnTag = null;
        //return mapping.get(tag.getName()).generateTagWithAttributes(tagAttributes);
        //if (tag.getName().equals("body")) {
        //    rnTag = new Tag("View");
        //}
        
        
        //return rnTag;
        System.out.println(tag);
        return tag;
    }

}

class TagContainer {
    private Tag child;
    private Tag parent;
    
    public TagContainer(Tag child, Tag parent) {
        this.child = child;
        this.parent = parent;
    }

    public Tag getChild() {
        return child;
    }

    public void setChild(Tag child) {
        this.child = child;
    }

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    
}
