package wniemiec.mobilang.asc.parser.persistence;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.models.PersistenceRecord;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing persistence node from MobiLang AST.
 */
public class PersistenceParser {

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
        if (json.has("type")) {
            persistenceData.setType(json.getString("type"));
        }

        if (json.has("data")) {
            parseData(json.getJSONArray("data"));
        }
    }

    private void parseData(JSONArray data) {
        for (int i = 0; i < data.length(); i++) {
            parseDataRecord(data.getJSONObject(i));
        }
    }
    
    // TODO: Refactor
    private void parseDataRecord(JSONObject dataRecord) {
        String type = dataRecord.getString("type");
        String name = dataRecord.getString("name");
        
        if (type.equals("int")) {
            Integer intValue = null;

            if (!dataRecord.isNull("initialValue")) {
                intValue = dataRecord.getInt("initialValue");
            }

            PersistenceRecord<Integer> record = new PersistenceRecord<>(
                name,
                intValue
            );
            persistenceData.addRecord(record);
        }
        else if (type.equals("float")) {
            Double floatValue = null;

            if (!dataRecord.isNull("initialValue")) {
                floatValue = dataRecord.getDouble("initialValue");
            }

            PersistenceRecord<Double> record = new PersistenceRecord<>(
                name,
                floatValue
            );

            persistenceData.addRecord(record);
        }
        else if (type.equals("string")) {
            String strValue = null;

            if (!dataRecord.isNull("initialValue")) {
                strValue = dataRecord.getString("initialValue");
            }

            PersistenceRecord<String> record = new PersistenceRecord<>(
                name,
                strValue
            );

            persistenceData.addRecord(record);
        }
        else if (type.equals("object")) {
            JSONObject objValue = null;

            if (!dataRecord.isNull("initialValue")) {
                objValue = dataRecord.getJSONObject("initialValue");
            }

            PersistenceRecord<JSONObject> record = new PersistenceRecord<>(
                name,
                objValue
            );

            persistenceData.addRecord(record);
        }
        else if (type.equals("array")) {
            JSONArray arrValue = null;

            if (!dataRecord.isNull("initialValue")) {
                arrValue = dataRecord.getJSONArray("initialValue");
            }

            PersistenceRecord<JSONArray> record = new PersistenceRecord<>(
                name,
                arrValue
            );

            persistenceData.addRecord(record);
        }

    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public PersistenceData getPersistenceData() {
        return persistenceData;
    }
}
