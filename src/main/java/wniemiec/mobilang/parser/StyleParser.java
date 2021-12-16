package wniemiec.mobilang.parser;

import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.data.Node;

public class StyleParser implements Parser {

    private String contentNode;

    public StyleParser(SortedMap<String, List<Node>> tree, Node styleNode) {
        contentNode = tree.get(styleNode.getId()).get(0).getLabel();
    }

    @Override
    public void parse() throws Exception {
        System.out.println("-----< STYLE PARSER >-----");
        System.out.println(contentNode);
        System.out.println("-------------------------------\n");
    }
}
