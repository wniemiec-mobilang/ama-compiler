package wniemiec.mobilang.asc.models.tag;

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
        if (child == null) {
            return;
        }
        
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

    public List<String> toCode() {
        List<String> code = new ArrayList<>();

        code.add(buildTagOpen());
        
        if (getValue() != null) {
            code.add(getValue());
        }
        else {
            List<Tag> children = getChildren();

            for (int i = 0; i < children.size(); i++) {
                code.addAll(children.get(i).toCode());
            }
        }

        code.add(buildTagClose());

        /*StringBuilder sb = new StringBuilder();

        sb.append('<');
        sb.append(name);

        if (!attributes.isEmpty()) {
            sb.append(' ');
            sb.append(stringifyAttributes());
        }
        
        sb.append('>');

        if (getValue() != null) {
            sb.append(getValue());
        }
        else {
            List<Tag> children = getChildren();

            for (int i = 0; i < children.size(); i++) {
                sb.append(children.get(i).toCode());
            }
        }

        sb.append("</");
        sb.append(name);
        sb.append('>');
        */
        

        return code;
    }

    private String buildTagOpen() {
        StringBuilder sb = new StringBuilder();

        sb.append('<');
        sb.append(name);

        if (!attributes.isEmpty()) {
            sb.append(' ');
            sb.append(stringifyAttributes());
        }
        
        sb.append('>');

        return sb.toString();
    }

    private String buildTagClose() {
        StringBuilder sb = new StringBuilder();

        sb.append("</");
        sb.append(name);
        sb.append('>');

        return sb.toString();
    }

    private String stringifyAttributes() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            sb.append(attribute.getKey());
            sb.append('=');
            sb.append(attribute.getValue());
            sb.append(' ');
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
    }

    public String toChildrenCode() {
        StringBuilder sb = new StringBuilder();
        List<Tag> children = getChildren();

        for (int i = 0; i < children.size(); i++) {
            sb.append(children.get(i).toCode());
        }

        return sb.toString();
    }

    public Map<String, String> getStyle() {
        return style;
    }
}
