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
        
        if (tag.hasAttribute("onclick")) {
            parseTagWithOnClick(parsedTag);
        }
    }

    private void parseTagWithOnClick(Tag tag) {
        if (!tag.hasAttribute("id")) {
            throw new IllegalStateException("Every input tag must have an id");
        }

        String id = "input_" + tag.getAttribute("id");
        
        tag.addAttribute("[(ngModel)]", id);
        tag.removeAttribute("onclick");
        
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
