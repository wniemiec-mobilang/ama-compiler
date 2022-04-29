package wniemiec.mobilang.ama.framework.ionic;

import java.util.ArrayList;
import java.util.List;

import wniemiec.mobilang.ama.models.tag.Tag;


class IonicStructureProcessor {

    private final Tag structure;
    private final List<String> inputFields;

    public IonicStructureProcessor(Tag structure) {
        this.structure = structure;
        inputFields = new ArrayList<>();
    }

    public void run() {
        runDirectiveProcessor();
        runInputProcessor();
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

    public List<String> getInputFields() {
        return inputFields;
    }
}
