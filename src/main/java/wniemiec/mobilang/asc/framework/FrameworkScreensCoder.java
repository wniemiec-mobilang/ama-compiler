package wniemiec.mobilang.asc.framework;

import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.models.ScreenData;

public abstract class FrameworkScreensCoder {

    protected List<ScreenData> screensData;

    protected FrameworkScreensCoder(List<ScreenData> screensData) {
        this.screensData = screensData;
    }
    
    public abstract Map<String, List<String>> generateCode();
}
