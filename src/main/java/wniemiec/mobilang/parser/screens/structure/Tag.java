package wniemiec.mobilang.parser.screens.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Tag {

    private String name;
    private String value;
    private Map<String, String> attributes = new HashMap<>();
    private List<Tag> children = new ArrayList<>();
    private Map<String, String> style = new HashMap<>();
    private Tag father = null;

    public Tag(String name, Map<String, String> tagAttributes) {
        this.name = name;
        this.attributes = tagAttributes;
    }
    
    public Tag(String name) {
        this.name = name;
    }
    




    @Override
    public String toString() {
        return "Tag [name=" + name + ", attributes=" + attributes + ", value=" + value + ", style=" + style + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute(String name) {
        return attributes.get(name);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }

    public void addChild(Tag child) {
        children.add(child);
        child.setFather(this);
    }

    

    public List<Tag> getChildren() {
        return children;
    }

    public void print() {
        parseTag(this, 0);
    }

    private static void parseTag(Tag tag, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }

        System.out.println(tag);

        for (Tag child : tag.getChildren()) {
            parseTag(child, level+1);
        }
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    public boolean hasAttribute(String name) {
        return attributes.containsKey(name);
    }

    public void setStyle(Map<String, String> style) {
        this.style = style;
    }

    public Tag getFather() {
        return father;
    }

    public void setFather(Tag father) {
        this.father = father;
    }

    public boolean hasFather() {
        return (father != null);
    }

    // Searches for a tag with an id. Do a DFS in children if this tag does not have the provided id.
    public Tag getTagWithId(String tagId) {
        if (hasAttribute("id") && getAttribute("id").equals(tagId)) {
            return this;
        }

        Tag refTag = null;
        Stack<Tag> tagsToSearch = new Stack<>();

        tagsToSearch.add(this);

        while (!tagsToSearch.empty() && (refTag == null)) {
            Tag currentTag = tagsToSearch.pop();

            if (currentTag.hasAttribute("id") && currentTag.getAttribute("id").equals(tagId)) {
                refTag = currentTag;
            }
            else {
                for (Tag child : currentTag.getChildren()) {
                    tagsToSearch.add(child);
                }
            }
        }

        return refTag;
    }

    //private Tag getAnyTagWithId(String tagId, )
}
