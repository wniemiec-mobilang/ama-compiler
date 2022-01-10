package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;

class Declaration extends Instruction {

    String kind;
    List<Declarator> declarations = new ArrayList<>();

    public Declaration(String type, String kind, List<Declarator> declarations) {
        super(type);
        this.kind = kind;

        if (declarations != null)
            this.declarations = declarations;
    }

    @Override
    public String toString() {
        return "Declaration [declarations=" + declarations + ", kind=" + kind + "]";
    }
    
    
}
