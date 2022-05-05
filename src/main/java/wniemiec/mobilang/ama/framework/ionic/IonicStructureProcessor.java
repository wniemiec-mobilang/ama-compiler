package wniemiec.mobilang.ama.framework.ionic;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.ama.models.tag.Tag;


class IonicStructureProcessor {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Tag structure;
    private final List<String> inputFields;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicStructureProcessor(Tag structure) {
        this.structure = structure;
        inputFields = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void run() {
        runScreenParametersProcessor();
        runDirectiveProcessor();
        runInputProcessor();
    }

    private void runScreenParametersProcessor() {
        // TODO: replace "mobilang::screen::glossary-desc?id=${data[item].id}" by "glossary-desc/${data[item].id}"
        // TODO: update routing: screen parameters
    }

    private void runDirectiveProcessor() {
        // TODO: call MobiLang Directive parser on HTML attributes
        throw new NoSuchMethodError("Not implemented yet");
    }

    private void runInputProcessor() {
        // TODO: input's devem ter [(ngModel)]="input_<id>" para capturar conteudo
        Tag currentTag = structure;

        if (currentTag.hasAttribute("onclick")) {
            String id = "input_" + currentTag.getAttribute("id");
            currentTag.addAttribute("[(ngModel)]", id);
            inputFields.add(id);
            currentTag.removeAttribute("onclick");
        }

        throw new NoSuchMethodError("Not implemented yet");
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInputIds() {
        return inputFields;
    }
}
