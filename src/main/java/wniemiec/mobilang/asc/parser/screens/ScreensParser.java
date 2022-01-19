package wniemiec.mobilang.asc.parser.screens;

import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.asc.coder.screens.ReactNativeScreenCode;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.framework.FrameworkParserFactory;

public class ScreensParser implements Parser {

    private SortedMap<String, List<Node>> tree;
    private List<Node> screens;
    private FrameworkParserFactory frameworkParserFactory;

    public ScreensParser(SortedMap<String, List<Node>> tree, Node screensNode, FrameworkParserFactory frameworkParserFactory) {
        this.tree = tree;
        screens = tree.get(screensNode.getId());
        this.frameworkParserFactory = frameworkParserFactory;
    }

    @Override
    public void parse() throws Exception {
        /*for (Node screen : screens) {
            ScreenParser screenParser = new ScreenParser(tree, screen);
            
            screenParser.parse();
        }*/

        ScreenParser screenParser = new ScreenParser(
            tree, 
            screens.get(1),
            frameworkParserFactory
        );
            
        screenParser.parse();

        ReactNativeScreenCode rnCode = new ReactNativeScreenCode(
            screenParser.getName(), 
            screenParser.getImports(), 
            screenParser.getDeclarations(),
            screenParser.getStateDeclarations(), 
            screenParser.getStateBody(), 
            screenParser.getBody()
        );

        List<String> code = rnCode.generateCode();

        System.out.println("\n\n----- CODE ----");
        for (String line : code) {
            System.out.println(line);
        }

        // frameworkParser = frameworkParserFactory.getScreensParser();
    }
}
