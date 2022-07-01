package wniemiec.mobilex.ama.models;

import java.util.HashMap;
import java.util.Map;
import com.paypal.digraph.parser.GraphNode;


/**
 * Responsible for representing an AST node.
 */
public class Node {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final GraphNode graphNode;
    private Map<String, String> attributes;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Node(GraphNode node) {
        this.graphNode = node;
        this.attributes = null;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return graphNode.toString();
    }

    @Override
    public int hashCode() {
        return graphNode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return graphNode.equals(obj);
    }

    private void initializeAttributes() {
        String label = getLabel();
        attributes = new HashMap<>();

        if (!label.contains(" ")) {
            return;
        }

        String rawAttributes = extractAttributesFrom(label);
        
        parseRawAttributes(rawAttributes);
    }

    private void parseRawAttributes(String rawAttributes) {
        for (String unparsedAttribute : rawAttributes.split(" ")) {
            parseAttribute(unparsedAttribute);
        }
    }

    private void parseAttribute(String unparsedAttribute) {
        String key = unparsedAttribute.split("=")[0];
        String value = unparsedAttribute.split("=")[1];

        attributes.put(key, value);
    }

    private String extractAttributesFrom(String label) {
        int indexFirstSpace = label.indexOf(" ");
        
        return label.substring(indexFirstSpace+1, label.length()-1);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public String getLabel() {
        return ((String) graphNode.getAttribute("label"));
    }

    public String getId() {
        return graphNode.getId();
    }

    public String getAttribute(String attribute) {
        if (attributes == null) {
            initializeAttributes();
        }

        return attributes.containsKey(attribute) 
            ? attributes.get(attribute).replace("\"", "") 
            : "";
    }   
}
