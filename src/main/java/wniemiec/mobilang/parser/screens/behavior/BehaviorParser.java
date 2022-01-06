package wniemiec.mobilang.parser.screens.behavior;

import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.data.Node;
import wniemiec.mobilang.parser.Parser;

public class BehaviorParser implements Parser {

    private String contentNode;

    public BehaviorParser(SortedMap<String, List<Node>> tree, Node behaviorNode) {
        contentNode = tree.get(behaviorNode.getId()).get(0).getLabel();
    }

    @Override
    public void parse() throws Exception {
        System.out.println("-----< BEHAVIOR PARSER >-----");
        System.out.println(contentNode);
        System.out.println("-------------------------------\n");
    }
}
