package wniemiec.mobilex.ama.parser.screens.behavior;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing behavior node from MobiLang AST.
 */
public class BehaviorParser  {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String contentNode;
    private final BlockCodeParser blockCodeParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Behavior parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       behaviorNode Behavior node
     */
    public BehaviorParser(SortedMap<String, List<Node>> ast, Node behaviorNode) {
        contentNode = ast.get(behaviorNode.getId()).get(0).getLabel();
        blockCodeParser = BlockCodeParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Behavior parse() throws ParseException {
        List<Instruction> code = parseJson(new JSONObject(contentNode));

        return new Behavior(code);
    }

    private List<Instruction> parseJson(JSONObject json) throws ParseException {
        JSONArray body = json.getJSONArray("body");

        return blockCodeParser.parse(body);
    }
}
