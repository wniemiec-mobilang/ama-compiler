package wniemiec.mobilang.parser;

import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.data.Node;

public class PropertiesParser implements Parser {

    private String propertiesContent;

    public PropertiesParser(SortedMap<String, List<Node>> tree, Node propertiesNode) {
        propertiesContent = tree.get(propertiesNode.getId()).get(0).getLabel();
    }
    
    @Override
    public void parse() throws Exception {
        System.out.println("-----< PROPERTIES PARSER >-----");
        System.out.println(propertiesContent);
        System.out.println("-------------------------------\n");
    }
}
