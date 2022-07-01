package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing class declarations from behavior node from MobiLang 
 * AST.
 */
class ClassDeclarationInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ClassDeclarationInstructionJsonParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ClassDeclarationInstructionJsonParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ClassDeclarationInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ClassDeclarationInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        throw new UnsupportedOperationException("Class declaration parser not implemented yet");
    }
}
