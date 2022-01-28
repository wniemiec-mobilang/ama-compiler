package wniemiec.mobilang.asc.parser.properties;

import java.util.List;
import java.util.SortedMap;

import org.json.JSONObject;

import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class PropertiesParser implements Parser {

    private String propertiesContent;
    private PropertiesData propertiesData;

    public PropertiesParser(SortedMap<String, List<Node>> tree, Node propertiesNode) {
        propertiesContent = tree.get(propertiesNode.getId()).get(0).getLabel();
        propertiesData = new PropertiesData();
    }
    
    @Override
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
