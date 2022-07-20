package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ClassBodyInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ClassBodyInstructionJsonParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ClassBodyInstructionJsonParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ClassBodyInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ClassBodyInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ClassBody(
            instructionParser.parse(jsonObject.getJSONArray("body"))
        );
    }
}