package wniemiec.mobilang.asc.parser.persistence;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing persistence node from MobiLang AST.
 */
public class PersistenceParser{

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String persistenceContent;
    private PersistenceData persistenceData;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Persistence parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       persistenceNode Persistence node
     */
    public PersistenceParser(SortedMap<String, List<Node>> ast, Node persistenceNode) {
        persistenceContent = ast.get(persistenceNode.getId()).get(0).getLabel();
        persistenceData = new PersistenceData();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse() throws ParseException {
        try {
            parseJson(new JSONObject(persistenceContent));
        } 
        catch (Exception e) {
            throw new ParseException("JSON parsing - " + e.getMessage());
        }  
    }

    private void parseJson(JSONObject json) {
        persistenceData.setType(json.getString("type"));
    }
    

    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public PersistenceData getPersistenceData() {
        return persistenceData;
    }
}
