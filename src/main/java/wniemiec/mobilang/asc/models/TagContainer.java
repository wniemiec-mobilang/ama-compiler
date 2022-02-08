package wniemiec.mobilang.asc.models;

public class TagContainer {
    private Tag child;
    private Tag parent;
    
    public TagContainer(Tag child, Tag parent) {
        this.child = child;
        this.parent = parent;
    }

    public boolean hasParent() {
        return (parent != null);
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

    public void addSibling(Tag sibling) {
        parent.addChild(sibling);
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }
}
