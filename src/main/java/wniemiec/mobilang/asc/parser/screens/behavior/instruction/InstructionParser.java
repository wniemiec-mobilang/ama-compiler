package wniemiec.mobilang.asc.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.exception.FactoryException;


/**
 * Responsible for parsing instructions from behavior node from MobiLang AST.
 */
public class InstructionParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static InstructionParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private InstructionParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static InstructionParser getInstance() {
        if (instance == null) {
            instance = new InstructionParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        String instructionType = extractInstructionType(jsonObject);
        
        try {
            return InstructionJsonParserFactory
                .get(instructionType)
                .parse(jsonObject);
        } 
        catch (FactoryException e) {
            throw new ParseException("Behavior - type not supported: " + instructionType);
        }
    }


    private String extractInstructionType(JSONObject jsonObject) {
        return jsonObject.getString("type");
    }

}
