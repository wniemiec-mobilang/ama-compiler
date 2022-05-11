package wniemiec.mobilang.ama.models.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import wniemiec.io.java.Consolex;


/**
 * Responsible for representing a tag.
 */
public class Tag implements Cloneable {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<Tag> children;
    private String name;
    private String value;
    private Tag parent;
    private Map<String, String> attributes;
    private Map<String, String> style;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public Tag(String name) {
        this(name, new HashMap<>());
    }

    public Tag(String name, Map<String, String> tagAttributes) {
        this(name, tagAttributes, null);
    }

    public Tag(String name, Map<String, String> tagAttributes, String value) {
        this.name = name;
        this.attributes = tagAttributes;
        this.value = value;
        children = new ArrayList<>();
        style = new HashMap<>();
    }
    

    //-------------------------------------------------------------------------
    //		Factories
    //-------------------------------------------------------------------------
    public static Tag getEmptyInstance() {
        return new Tag("null");
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Tag clone() {
        Tag clonedTag = new Tag(name, attributes, value);

        clonedTag.setStyle(new HashMap<>(style));
        clonedTag.setParent(parent);

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
    }

    public void print() {
        print(this, 0);
    }

    private static void print(Tag tag, int level) {
        for (int i = 0; i < level; i++) {
            Consolex.writeLine("  ");
        }

        Consolex.writeLine(tag);

        for (Tag child : tag.getChildren()) {
            print(child, level+1);
        }
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    public boolean hasAttribute(String name) {
        return attributes.containsKey(name);
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
        List<String> code = new ArrayList<>();

        code.add(buildTagOpen());
        
        if (getValue() != null) {
            code.add(getValue());
        }
        else {
            for (Tag child : getChildren()) {
                code.addAll(child.toCode());
            }
        }

        code.add(buildTagClose());

        return code;
    }

    private String buildTagOpen() {
        StringBuilder code = new StringBuilder();

        code.append('<');
        code.append(name);

        if (!attributes.isEmpty()) {
            code.append(' ');
            code.append(stringifyAttributes());
        }
        
        code.append('>');

        return code.toString();
    }

    private String stringifyAttributes() {
        StringBuilder code = new StringBuilder();

        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            code.append(attribute.getKey());
            code.append('=');

            if (attribute.getValue().startsWith("{")) {
                code.append(attribute.getValue());
            }
            else {
                code.append('\"');
                code.append(attribute.getValue());
                code.append('\"');
            }

            code.append(' ');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private String buildTagClose() {
        StringBuilder code = new StringBuilder();

        code.append("</");
        code.append(name);
        code.append('>');

        return code.toString();
    }

    public String toChildrenCode() {
        StringBuilder code = new StringBuilder();

        for (Tag child : getChildren()) {
            code.append(child.toCode());
        }

        return code.toString();
    }

    public void mergeChildren(List<Tag> newChildren) {
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

    public void mergeStyle(Map<String, String> style) {
        this.style.putAll(style);
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tag other = (Tag) obj;
        if (attributes == null) {
            if (other.attributes != null)
                return false;
        } else if (!attributes.equals(other.attributes))
            return false;
        if (children == null) {
            if (other.children != null)
                return false;
        } else if (!children.equals(other.children))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (style == null) {
            if (other.style != null)
                return false;
        } else if (!style.equals(other.style))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
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

    public String getStyle(String attribute) {
        return style.get(attribute);
    }
}
