package wniemiec.mobilang.asc.framework.coder;

import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.ScreenData;

public abstract class FrameworkScreensCoder {

    protected List<ScreenData> screensData;

    protected FrameworkScreensCoder(List<ScreenData> screensData) {
        this.screensData = screensData;
    }
    
    public abstract List<FileCode> generateCode();
}
