package wniemiec.mobilang.asc.coder.framework;

import java.util.List;

import wniemiec.mobilang.asc.models.ScreenData;

public abstract class FrameworkScreenCoder {

    protected ScreenData screenData;

    protected FrameworkScreenCoder(ScreenData screenData) {
        this.screenData = screenData;
    }
    
    public abstract List<String> generateCode();
}
