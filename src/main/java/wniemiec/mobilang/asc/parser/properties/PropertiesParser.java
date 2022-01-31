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

    private String propertiesContent;
    private PropertiesData propertiesData;


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
    
    public void parse() throws ParseException {
        //System.out.println("-----< PROPERTIES PARSER >-----");
        //System.out.println(propertiesContent);
        //System.out.println("-------------------------------\n");

        try {
            parseJson(propertiesContent);
        } 
        catch (Exception e) {
            throw new ParseException("JSON parsing - " + e.getMessage());
        }  
    }

    private void parseJson(String json) throws Exception {
        JSONObject obj = new JSONObject(json);

        propertiesData.setName(obj.getString("application_name"));
    }

    public PropertiesData getPropertiesData() {
        return propertiesData;
    }
}
