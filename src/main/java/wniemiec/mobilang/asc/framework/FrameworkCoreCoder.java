package wniemiec.mobilang.asc.framework;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wniemiec.mobilang.asc.models.FileCode;

public abstract class FrameworkCoreCoder {

    protected Collection<String> screensName;

    protected FrameworkCoreCoder(Collection<String> screensName) {
        this.screensName = screensName;
    }
    
    public abstract List<FileCode> generateCode();
}
