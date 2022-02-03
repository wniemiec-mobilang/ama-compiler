package wniemiec.mobilang.asc.framework.parser;

import java.io.IOException;
import wniemiec.mobilang.asc.models.Behavior;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing a screen.
 */
public abstract class FrameworkScreenParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected String name;
    protected Tag structure;
    protected Style style;
    protected Behavior behavior;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Screen parser.
     * 
     * @param       name Screen name
     * @param       structure Screen structure content
     * @param       style Screen style content
     * @param       behavior Screen behavior content
     */
    protected FrameworkScreenParser(
        String name, 
        Tag structure, 
        Style style, 
        Behavior behavior
    ) {
        this.name = name;
        this.structure = structure;
        this.style = style;
        this.behavior = behavior;
    }    


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public abstract void parse() throws ParseException, IOException;
    public abstract ScreenData getScreenData();
}
