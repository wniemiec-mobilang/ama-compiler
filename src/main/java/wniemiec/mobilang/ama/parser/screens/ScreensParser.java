package wniemiec.mobilang.ama.parser.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.ama.models.Node;
import wniemiec.mobilang.ama.models.ScreenData;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Responsible for parsing screens node from MobiLang AST.
 */
public class ScreensParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final SortedMap<String, List<Node>> ast;
    private final List<Node> screens;
    private final List<ScreenData> screensData;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Screens parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       screensNode Screens node
     */
    public ScreensParser(
        SortedMap<String, List<Node>> ast, 
        Node screensNode
    ) {
        this.ast = ast;
        screens = ast.get(screensNode.getId());
        screensData = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse() throws ParseException, IOException {
        for (Node screen : screens) {   
            ScreenParser screenParser = new ScreenParser(
                ast, 
                screen
            );
            
            screenParser.parse();
            screensData.add(screenParser.getScreenData());
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<ScreenData> getScreensData() {
        return screensData;
    }
}
