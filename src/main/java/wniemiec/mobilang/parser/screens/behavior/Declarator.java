package wniemiec.mobilang.parser.screens.behavior;

class Declarator {
 
    String type;
    String idKind;
    String idName;
    Expression init;
    public Declarator(String type, String idKind, String idName, Expression init) {
        this.type = type;
        this.idKind = idKind;
        this.idName = idName;
        this.init = init;
    }
    @Override
    public String toString() {
        return "Declarator [idKind=" + idKind + ", idName=" + idName + ", init=" + init + ", type=" + type + "]";
    }

    
}