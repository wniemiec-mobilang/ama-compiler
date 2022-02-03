package wniemiec.mobilang.asc.framework.parser;

import wniemiec.mobilang.asc.models.Behavior;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;


/**
 * Provides parsers of a framework.
 */
public interface FrameworkParserFactory {

    /**
     * Provides screen parser of a framework.
     * 
     * @param       name Screen name
     * @param       structure Screen structure content
     * @param       style Screen style content
     * @param       behavior Screen behavior content
     * 
     * @return      Screen parser
     */
    FrameworkScreenParser getScreenParser(
        String name, 
        Tag structure, 
        Style style, 
        Behavior behavior
    );
}
