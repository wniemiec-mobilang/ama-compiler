package wniemiec.mobilang.ama.framework.ionic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import wniemiec.mobilang.ama.models.tag.Tag;


class IonicStructureParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Tag structure;
    private final List<String> inputFields;
    private final List<String> parsedCode;
    private final IonicMobiLangDirectiveParser directiveParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicStructureParser(Tag structure) {
        this.structure = structure;
        inputFields = new ArrayList<>();
        parsedCode = new ArrayList<>();
        directiveParser = new IonicMobiLangDirectiveParser();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<String> parse() {
        runInputProcessor();
        runDirectiveParser();

        return parsedCode;
    }

    private void runInputProcessor() {
        runStructureProcessor(currentTag -> {
            if (currentTag.hasAttribute("onclick")) {
                String id = "input_" + currentTag.getAttribute("id");
                currentTag.addAttribute("[(ngModel)]", id);
                inputFields.add(id);
                currentTag.removeAttribute("onclick");
            }
        });
    }

    private void runStructureProcessor(Consumer<Tag> tagHandler) {
        Queue<Tag> toParse = new LinkedList<>();
        
        toParse.add(structure);

        while (!toParse.isEmpty()) {
            Tag currentTag = structure;
    
            tagHandler.accept(currentTag);

            currentTag.getChildren().forEach(toParse::add);
        }
    }

    private void runDirectiveParser() {
        parsedCode.addAll(directiveParser.parse(structure.toCode()));
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInputIds() {
        return inputFields;
    }
}
