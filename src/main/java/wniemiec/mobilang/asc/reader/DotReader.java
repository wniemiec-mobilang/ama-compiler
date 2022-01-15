package wniemiec.mobilang.asc.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;

import wniemiec.mobilang.asc.data.Node;

public class DotReader {

    public DotReader() {
    }
    
    public SortedMap<String, List<Node>> read(Path filepath) throws FileNotFoundException {
        SortedMap<String, List<Node>> tree = new TreeMap<>();
        GraphParser parser = new GraphParser(new FileInputStream(filepath.toFile()));
        //Map<String, GraphNode> nodes = parser.getNodes();
        /*List<GraphNode> sortedGraph = nodes
            .values()
            .stream()
            .sorted((node1, node2) -> extractNumberFromNode(node1) - extractNumberFromNode(node2))
            .collect(Collectors.toList());
*/
        /*List<String> labeledNodes = new ArrayList<>();
        Stack<String> currentTag = new Stack<>();
        for (int i = 0; i < sortedGraph.size(); i++) {
            String tag = (String) sortedGraph.get(i).getAttribute("label");

            if (tag.charAt(0) == '<') {
                if (currentTag.size() > 0 && currentTag.peek().equals("style")) {
                    labeledNodes.add("</" + currentTag.peek() + ">");
                    currentTag.pop();
                }
                
                labeledNodes.add(tag);
                currentTag.push(tag.substring(1, tag.length()-1));
            }
            else if (!currentTag.peek().equals("style")) {
                labeledNodes.add(tag);
                labeledNodes.add("</" + currentTag.peek() + ">");
                currentTag.pop();
            }
            else {
                labeledNodes.add(tag);
            }
        }*/
            
        /*Stack<String> dfsNodes = new Stack<>();
        for (int i = sortedGraph.size()-1; i >= 0; i--) {
            //String tag = labeledNodes.get(i);
            String tag = extractNodeLabel(sortedGraph.get(i));

            dfsNodes.push(tag);
        }*/

        Map<String, GraphEdge> edges = parser.getEdges();
        for (GraphEdge edge : edges.values()) {
            GraphNode nodeLeft = edge.getNode1();
            GraphNode nodeRight = edge.getNode2();
            String key = nodeLeft.getId();//extractNodeLabel(nodeLeft); //+ nodeLeft.getId();
            
            if (tree.containsKey(key)) {
                List<Node> children = tree.get(key);
                children.add(new Node(nodeRight));
            }
            else {
                List<Node> children = new ArrayList<>();
                children.add(new Node(nodeRight));
                tree.put(key, children);
            }
            //System.out.println(extractNodeLabel(edge.getNode1()) + "[" + edge.getNode1().getId() + "]" + " -> " + extractNodeLabel(edge.getNode2()) + "[" + edge.getNode2().getId() + "]");
        }


        return tree;
    }

    private int extractNumberFromNode(GraphNode node) {
        return Integer.parseInt(node.getId().substring(1));
    }

    private String extractNodeLabel(GraphNode node) {
        return ((String) node.getAttribute("label"));
    }   
}
