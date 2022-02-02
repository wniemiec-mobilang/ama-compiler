package wniemiec.mobilang.asc.parser.properties;

import java.util.List;
import java.util.SortedMap;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing properties node from MobiLang AST.
 */
public class PropertiesParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String propertiesContent;
    private PropertiesData propertiesData;


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
        propertiesData = new PropertiesData();
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
        propertiesData.setName(json.getString("application_name"));
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public PropertiesData getPropertiesData() {
        return propertiesData;
    }
}
