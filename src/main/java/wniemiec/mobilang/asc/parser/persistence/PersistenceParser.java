package wniemiec.mobilang.asc.parser.persistence;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import org.json.JSONObject;

import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class PersistenceParser{

    private String persistenceContent;
    private PersistenceData persistenceData;

    public PersistenceParser(SortedMap<String, List<Node>> tree, Node persistenceNode) {
        persistenceContent = tree.get(persistenceNode.getId()).get(0).getLabel();
        persistenceData = new PersistenceData();
    }

    public void parse() throws ParseException {
        //System.out.println("-----< PERSISTENCE PARSER >-----");
        //System.out.println(persistenceContent);
        //System.out.println("-------------------------------\n");

        try {
            parseJson(persistenceContent);
        } 
        catch (Exception e) {
            throw new ParseException("JSON parsing - " + e.getMessage());
        }  
    }

    private void parseJson(String json) throws Exception {
        JSONObject obj = new JSONObject(json);

        persistenceData.setType(obj.getString("type"));
    }
    

    public PersistenceData getPersistenceData() {
        return persistenceData;
    }
}
