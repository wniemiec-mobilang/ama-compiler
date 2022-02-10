package wniemiec.mobilang.asc.parser.persistence;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.persistence.PersistenceData;
import wniemiec.mobilang.asc.models.persistence.PersistenceRecord;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing persistence node from MobiLang AST.
 */
public class PersistenceParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String persistenceContent;
    private final PersistenceData persistenceData;


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

    private void parseJson(JSONObject json) throws JSONException, ParseException {
        if (json.has("type")) {
            persistenceData.setType(json.getString("type"));
        }

        if (json.has("data")) {
            parseData(json.getJSONArray("data"));
        }
    }

    private void parseData(JSONArray data) throws JSONException, ParseException {
        for (int i = 0; i < data.length(); i++) {
            parseDataRecord(data.getJSONObject(i));
        }
    }
    
    private void parseDataRecord(JSONObject dataRecord) throws ParseException {
        DataRecordParser parser = new DataRecordParser(dataRecord);
        PersistenceRecord<?> persistenceRecord = parser.parse();
        
        persistenceData.addRecord(persistenceRecord);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public PersistenceData getPersistenceData() {
        return persistenceData;
    }
}
