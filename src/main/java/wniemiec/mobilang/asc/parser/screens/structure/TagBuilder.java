package wniemiec.mobilang.asc.parser.screens.structure;

import java.util.Map;

import wniemiec.mobilang.asc.models.Tag;

public class TagBuilder {

    private String tagName;

    private TagBuilder(String tagName) {
        this.tagName = tagName;
    }

    public static TagBuilder of(String tagName) {
        return new TagBuilder(tagName);
    }

    public Tag generateTagWithAttributes(Map<String, String> tagAttributes) {
        return new Tag(tagName, tagAttributes);
    }

    
}
