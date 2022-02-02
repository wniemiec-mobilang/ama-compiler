package wniemiec.mobilang.asc.framework.parser;

import wniemiec.mobilang.asc.models.Behavior;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;

public interface FrameworkParserFactory {

    FrameworkScreenParser getScreenParser(String name, Tag structure, Style style, Behavior behavior);

}
