package wniemiec.mobilang.asc.framework;

import wniemiec.mobilang.asc.models.StyleSheet;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.parser.screens.behavior.Behavior;

public interface FrameworkParserFactory {

    FrameworkScreenParser getScreenParser(String name, Tag structure, StyleSheet style, Behavior behavior);

}
