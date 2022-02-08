package wniemiec.mobilang.asc.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing an instruction from code of behavior node from 
 * MobiLang AST.
 */
public interface InstructionJsonParser {

    /**
     * Parses an instruction from code of behavior node from MobiLang AST.
     * 
     * @param       jsonObject Code from behavior node
     * 
     * @return      Parsed instruction
     * 
     * @throws      JSONException If jsonObject cannot be parsed
     * @throws      ParseException If an error occurs while parsing the code
     */
    Instruction parse(JSONObject jsonObject) throws JSONException, ParseException;
}
