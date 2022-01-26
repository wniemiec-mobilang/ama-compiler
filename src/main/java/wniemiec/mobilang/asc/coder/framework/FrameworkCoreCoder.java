package wniemiec.mobilang.asc.coder.framework;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class FrameworkCoreCoder {

    protected Set<String> screensName;

    protected FrameworkCoreCoder(Set<String> screensName) {
        this.screensName = screensName;
    }
    
    public abstract Map<String, List<String>> generateCode();
}
