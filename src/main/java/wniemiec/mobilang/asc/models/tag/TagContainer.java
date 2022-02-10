package wniemiec.mobilang.asc.models.tag;


/**
 * Responsible for representing a tag along with its parent.
 */
public class TagContainer {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Tag tag;
    private final Tag parent;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public TagContainer(Tag tag, Tag parent) {
        this.tag = tag;
        this.parent = parent;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void addSibling(Tag sibling) {
        if (!hasParent()) {
            return;
        }

        parent.addChild(sibling);
    }

    public boolean hasParent() {
        return (parent != null);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Tag getTag() {
        return tag;
    }

    public Tag getParent() {
        return parent;
    }
}
