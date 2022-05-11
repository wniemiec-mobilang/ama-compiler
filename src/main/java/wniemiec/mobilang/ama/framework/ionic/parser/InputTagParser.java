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
        
        /*if (currentTag.hasAttribute("onclick")) {
            String id = "input_" + currentTag.getAttribute("id");
            currentTag.addAttribute("[(ngModel)]", id);
            inputFields.add(id);
            currentTag.removeAttribute("onclick");
        }*/
    }

    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInputIds() {
        return inputFields;
    }
}
