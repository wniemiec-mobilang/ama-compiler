package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
        Stack<Tag> toParse = new Stack<>();
        
        parsedTag = tag.clone();
        toParse.add(parsedTag);

        while (!toParse.empty()) {
            Tag currentTag = toParse.pop();

            parseTag(currentTag);   

            currentTag.getChildren().forEach(toParse::push);
        }
    }

    private void parseTag(Tag tag) {
        if (tag.hasAttribute("onclick")) {
            parseTagWithOnClick(tag);
        }
    }

    private void parseTagWithOnClick(Tag tag) {
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
