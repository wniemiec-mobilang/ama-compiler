package wniemiec.mobilang.parser;

import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.data.Node;

public class StructureParser implements Parser {

    private String contentNode;

    public StructureParser(SortedMap<String, List<Node>> tree, Node structureNode) {
        contentNode = tree.get(structureNode.getId()).get(0).getLabel();
    }

    @Override
    public void parse() throws Exception {
        System.out.println("-----< STRUCTURE PARSER >-----");
        System.out.println(contentNode);
        System.out.println("-------------------------------\n");
    }
}
