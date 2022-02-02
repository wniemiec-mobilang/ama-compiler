package wniemiec.mobilang.asc.models.behavior;

public class Declarator {
 
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

    public String toCode() {
        if (init == null) {
            //return idName + "=" + "null";
            return idName;
        }

        return idName + "=" + init.toCode();
    }
}