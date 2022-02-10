package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a declarator from behavior code.
 */
public class Declarator implements Instruction {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String type;
    private final String idKind;
    private final String idName;
    private final Expression init;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Declarator(String type, String idKind, String idName, Expression init) {
        this.type = type;
        this.idKind = idKind;
        this.idName = idName;
        this.init = init;
    }

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        if (init == null) {
            return idName;
        }

        return idName + "=" + init.toCode();
    }

    @Override
    public String toString() {
        return "Declarator [" 
                + "idKind=" + idKind 
                + ", idName=" + idName 
                + ", init=" + init 
                + ", type=" + type 
            + "]";
    }
}