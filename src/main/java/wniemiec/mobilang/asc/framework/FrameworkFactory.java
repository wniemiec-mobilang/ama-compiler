package wniemiec.mobilang.asc.framework;

public interface FrameworkFactory {

    FrameworkParserFactory getParserFactory();
    FrameworkCoderFactory getCoderFactory();
}
