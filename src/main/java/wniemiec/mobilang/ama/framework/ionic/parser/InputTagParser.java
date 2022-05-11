package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.ama.models.tag.Tag;


class InputTagParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<String> inputFields;
    private Tag parsedTag;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public InputTagParser() {
        inputFields = new ArrayList<>();
        parsedTag = Tag.getEmptyInstance();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(Tag tag) {
        parsedTag = tag.clone();
        
        if (tag.getName().equals("input")) {
            parseInputTag(parsedTag);
        }
    }

    private void parseInputTag(Tag tag) {
        if (!tag.hasAttribute("id")) {
            throw new IllegalStateException("Every input tag must have an id");
        }

        String id = "input_" + tag.getAttribute("id");
        
        tag.addAttribute("[(ngModel)]", id);
        
        inputFields.add(id);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInputIds() {
        return inputFields;
    }

    public Tag getParsedTag() {
        return parsedTag;
    }
}
