package wniemiec.mobilang.asc.framework;

import wniemiec.mobilang.asc.framework.coder.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.framework.parser.FrameworkParserFactory;

public interface FrameworkFactory {

    FrameworkParserFactory getParserFactory();
    FrameworkCoderFactory getCoderFactory();
    FrameworkProjectManagerFactory getProjectManagerFactory();
}
