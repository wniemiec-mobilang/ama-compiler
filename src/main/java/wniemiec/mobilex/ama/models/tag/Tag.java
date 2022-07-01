package wniemiec.mobilex.ama.models.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 * Responsible for representing a tag.
 */
public class Tag implements Cloneable {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Map<String, String> attributes;
    private List<Tag> children;
    private String name;
    private String value;
    private Tag parent;
    private Map<String, String> style;
    private boolean voidTag;
    private TagCoder tagCoder;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public Tag(String name, boolean voidTag) {
        this(name, new HashMap<>(), voidTag);
    }

    private Tag(String name, Map<String, String> tagAttributes, boolean voidTag) {
        this.name = name;
        this.attributes = tagAttributes;
        children = new ArrayList<>();
        style = new HashMap<>();
        this.voidTag = voidTag;
        tagCoder = new TagCoder();
    }
    

    //-------------------------------------------------------------------------
    //		Factories
    //-------------------------------------------------------------------------
    public static Tag getEmptyInstance() {
        return new Tag("null", true);
    }

    public static Tag getNormalInstance(String name) {
        return new Tag(name, false);
    }

    public static Tag getVoidInstance(String name) {
        return new Tag(name, true);
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Tag clone() {
        Tag clonedTag = new Tag(name, attributes, voidTag);

        clonedTag.setStyle(new HashMap<>(style));
        clonedTag.setParent(parent);
        clonedTag.setValue(value);

        this.getChildren().forEach(child -> {
            clonedTag.addChild(child.clone());
        });

        return clonedTag;
    }

    public void addChild(Tag child) {
        if (child == null) {
            return;
        }
        
        children.add(child);
        child.setParent(this);
    }

    public void replaceChild(Tag tag, Tag newTag) {
        children.set(children.indexOf(tag), newTag);
        tag.setParent(null);
        newTag.setParent(this);
    }

    public void addAttribute(String name, String value) {
        if (name.matches(".*[\\[\\]\\(\\)]+.*")) {
            attributes.put(name, value);
        }
        else {
            attributes.put(name.toLowerCase(), value);
        }
    }

    public boolean hasAttribute(String name) {
        return attributes.containsKey(name.toLowerCase());
    }

    /**
     * Searches for a tag with an id. Do a DFS in children if this tag does not 
     * have the provided id.
     * 
     * @param       tagId Tag identifier
     * 
     * @return      Tag with specified id or null if there is no tag with such 
     * identifier
     */
    public Tag getTagWithId(String tagId) {
        if (isIdEqualTo(tagId)) {
            return this;
        }

        Tag refTag = null;
        Stack<Tag> tagsToSearch = new Stack<>();

        tagsToSearch.add(this);

        while (!tagsToSearch.empty() && (refTag == null)) {
            Tag currentTag = tagsToSearch.pop();

            refTag = parseTag(currentTag, tagsToSearch, tagId);
        }

        return refTag;
    }

    private Tag parseTag(Tag currentTag, Stack<Tag> tagsToSearch, String tagId) {
        Tag refTag = null;

        if (currentTag.isIdEqualTo(tagId)) {
            refTag = currentTag;
        }
        else {
            for (Tag child : currentTag.getChildren()) {
                tagsToSearch.add(child);
            }
        }

        return refTag;
    }

    public boolean isIdEqualTo(String text) {
        if (!hasAttribute("id")) {
            return false;
        }

        return getAttribute("id").equals(text);
    }

    public boolean hasParent() {
        return (parent != null);
    }

    public List<String> toCode() {
        return tagCoder.toCode(this);
    }

    public boolean isVoidTag() {
        return voidTag;
    }

    public void addChildren(List<Tag> newChildren) {
        children.addAll(newChildren);
    }

    public void addStyle(String key, String value) {
        style.put(key, value);
    }

    public boolean hasStyle(String attribute) {
        return style.containsKey(attribute);
    }

    public void removeStyle(String attribute) {
        style.remove(attribute);
    }

    public void addStyles(Map<String, String> styles) {
        style.putAll(styles);
    }

    public void removeAttribute(String attribute) {
        attributes.remove(attribute);
    }

    @Override
    public String toString() {
        return "Tag [" 
                + "name=" + name 
                + ", parent=" + ((parent == null) ? "null" : parent) 
                + ", attributes=" + attributes 
                + ", value=" + value 
                + ", style=" + style 
            + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((style == null) ? 0 : style.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if ((obj == null) || getClass() != obj.getClass()) { 
            return false;
        }

        Tag other = (Tag) obj;

        return  hasSameAttributes(other)
                && hasSameChildren(other)
                && hasSameName(other)
                && hasSameStyle(other)
                && hasSameValue(other);
    }

    private boolean hasSameAttributes(Tag other) {
        if ((attributes == null) && (other.attributes == null)) {
            return true;
        }

        return (attributes != null) && attributes.equals(other.attributes);
    }

    private boolean hasSameChildren(Tag other) {
        if ((children == null) && (other.children == null)) {
            return true;
        }

        return (children != null) && children.equals(other.children);
    }

    private boolean hasSameName(Tag other) {
        if ((name == null) && (other.name == null)) {
            return true;
        }

        return (name != null) && name.equals(other.name);
    }

    private boolean hasSameStyle(Tag other) {
        if ((style == null) && (other.style == null)) {
            return true;
        }

        return (style != null) && style.equals(other.style);
    }

    private boolean hasSameValue(Tag other) {
        if ((value == null) && (other.value == null)) {
            return true;
        }

        return (value != null) && value.equals(other.value);
    }


    //-------------------------------------------------------------------------
    //		Getters & Setters
    //-------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Tag> getChildren() {
        return children;
    }

    public void setChildren(List<Tag> newChildren) {
        children = newChildren;
    }

    public String getAttribute(String name) {
        return attributes.get(name);
    }

    public Map<String, String> getStyle() {
        return style;
    }

    public void setStyle(Map<String, String> style) {
        this.style = style;
    }

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getStyle(String attribute) {
        return style.get(attribute);
    }

    public void setVoidTag(boolean voidTag) {
        this.voidTag = voidTag;
    }
}
