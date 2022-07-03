package wniemiec.mobilex.ama.parser.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing screens node from MobiLang AST.
 */
public class ScreensParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final SortedMap<String, List<Node>> ast;
    private final List<Node> screenNodes;
    private final List<Screen> screens;


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
        screenNodes = ast.get(screensNode.getId());
        screens = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse() throws ParseException, IOException {
        for (Node screen : screenNodes) {   
            ScreenParser screenParser = new ScreenParser(
                ast, 
                screen
            );
            
            screenParser.parse();
            screens.add(screenParser.getScreenData());
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<Screen> getScreens() {
        return screens;
    }
}
