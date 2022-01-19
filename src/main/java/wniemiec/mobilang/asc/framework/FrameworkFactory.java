package wniemiec.mobilang.asc.framework;

import wniemiec.mobilang.asc.coder.framework.FrameworkCoderFactory;
import wniemiec.mobilang.asc.parser.framework.FrameworkParserFactory;

public interface FrameworkFactory {

    FrameworkParserFactory getParserFactory();
    FrameworkCoderFactory getCoderFactory();
}
