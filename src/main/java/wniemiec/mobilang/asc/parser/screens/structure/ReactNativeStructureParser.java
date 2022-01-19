package wniemiec.mobilang.asc.parser.screens.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import wniemiec.mobilang.asc.models.Tag;

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

        //for (Tag child : root.getChildren()) {
        //    tagsToParse.push(new TagContainer(child, rnRoot));
        //}
        List<Tag> childrenToParse = root.getChildren();
        for (int i = childrenToParse.size()-1; i >= 0; i--) {
            tagsToParse.push(new TagContainer(childrenToParse.get(i), rnRoot));
        }
        
        while (!tagsToParse.empty()) {
            TagContainer unparsedTag = tagsToParse.pop();
            
            Tag parsedTag = convertTagToReactNativeTag(unparsedTag.getChild());

            if (unparsedTag.getParent() != null) {
                unparsedTag.getParent().addChild(parsedTag);
            }
            
            childrenToParse = unparsedTag.getChild().getChildren();
            for (int i = childrenToParse.size()-1; i >= 0; i--) {
                tagsToParse.push(new TagContainer(childrenToParse.get(i), parsedTag));
            }
        }

        return rnRoot;
    }

    private Tag convertTagToReactNativeTag(Tag tag) {
        Tag rnTag = null;
        //return mapping.get(tag.getName()).generateTagWithAttributes(tagAttributes);
        if (tag.getName().equals("body")) {
            rnTag = new Tag("View");
            // TODO: parseBodyAttributes(tag, rnTag);
        }
        else if (tag.getName().equals("div")) {
            rnTag = new Tag("View");
            // TODO: parseDivAttributes(tag, rnTag);
        }
        else if (tag.getName().equals("button")) {
            rnTag = new Tag("TouchableOpacity");

            if (tag.hasAttribute("onclick")) {
                rnTag.addAttribute("onPress", tag.getAttribute("onclick"));
            }
            // TODO: parseButtonAttributes(tag, rnTag);
        }
        else if (tag.getName().equals("img")) {
            rnTag = new Tag("Image");
            rnTag.addAttribute("source", "{{uri: '" + tag.getAttribute("src") + "'}}");
            // TODO: parseImgAttributes(tag, rnTag);
        }
        else if (tag.getName().equals("a")) {
            rnTag = new Tag("TouchableOpacity");
            rnTag.addAttribute("onPress", "() => redirectTo('" + tag.getAttribute("href") + "')");
            
            Tag textTag = new Tag("Text");
            textTag.setValue(tag.getValue());

            rnTag.addChild(textTag);
            // TODO: parseAAttributes(tag, rnTag);
        }
        else if (tag.getName().equals("input")) {
            rnTag = new Tag("TextInput");
            // TODO: parseDivAttributes(tag, rnTag);
        }
        else if (tag.getName().matches("h[0-5]{1}")) {
            rnTag = new Tag("Text");
            rnTag.setValue(tag.getValue());
            // TODO: parseDivAttributes(tag, rnTag);
        }
        else if (tag.getName().equals("p")) {
            rnTag = new Tag("Text");
            rnTag.setValue(tag.getValue());
            // TODO: parseDivAttributes(tag, rnTag);
        }
        else if (tag.getName().isBlank()) {
            rnTag = new Tag("");
        }

        if (rnTag != null) {
            //System.out.println(tag);
            //System.out.println(rnTag);

            if (tag.hasAttribute("id")) {
                rnTag.addAttribute("id", tag.getAttribute("id"));
            }

            if (tag.hasAttribute("class")) {
                rnTag.addAttribute("class", tag.getAttribute("class"));
            }
        }
        else {
            System.out.println("No suitable convertion for tag with name: " + tag.getName());
        }
        
        
        return rnTag;
        //System.out.println(tag);
        //return tag;
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
