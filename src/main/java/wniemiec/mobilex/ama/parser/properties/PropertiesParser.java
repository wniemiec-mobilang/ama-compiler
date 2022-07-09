package wniemiec.mobilex.ama.parser.properties;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing properties node from MobiLang AST.
 */
public class PropertiesParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String propertiesContent;
    private final Properties propertiesData;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Properties parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       propertiesNode Properties node
     */
    public PropertiesParser(SortedMap<String, List<Node>> ast, Node propertiesNode) {
        propertiesContent = ast.get(propertiesNode.getId()).get(0).getLabel();
        propertiesData = new Properties();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse() throws ParseException {
        try {
            parseJson(new JSONObject(propertiesContent));
        } 
        catch (Exception e) {
            throw new ParseException("JSON parsing - " + e.getMessage());
        }  
    }

    private void parseJson(JSONObject json) {
        parseApplicationName(json);
        parsePlatforms(json);
    }

    private void parseApplicationName(JSONObject json) {
        propertiesData.setApplicationName(json.getString("application_name"));
    }

    private void parsePlatforms(JSONObject json) {
        JSONArray platforms = json.getJSONArray("platforms");
        for (int i = 0; i < platforms.length(); i++) {
            propertiesData.addPlatform(platforms.getString(i));
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Properties getProperties() {
        return propertiesData;
    }
}
