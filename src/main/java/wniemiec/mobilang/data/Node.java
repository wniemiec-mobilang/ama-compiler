package wniemiec.mobilang.data;

import java.util.HashMap;
import java.util.Map;

import com.paypal.digraph.parser.GraphNode;

public class Node {

    private GraphNode graphNode;
    private Map<String, String> attributes;
    
    public Node(GraphNode node) {
        this.graphNode = node;
        this.attributes = null;
    }

    public String getLabel() {
        return ((String) graphNode.getAttribute("label"));
    }

    public String getId() {
        return graphNode.getId();
    }

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

    public String getAttribute(String attribute) {
        if (attributes == null)
            initializeAttributes();

        return attributes.containsKey(attribute) ? attributes.get(attribute) : "";
    }

    private void initializeAttributes() {
        String label = getLabel();

        if (!label.contains(" "))
            return;

        int indexFirstSpace = label.indexOf(" ");
        String rawAttributes = label.substring(indexFirstSpace+1, label.length()-1);
            
        attributes = new HashMap<>();

        for (String unparsedAttribute : rawAttributes.split(" ")) {
            String key = unparsedAttribute.split("=")[0];
            String value = unparsedAttribute.split("=")[1];

            attributes.put(key, value);
        }
    }
}
