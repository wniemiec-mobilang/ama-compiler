package wniemiec.mobilang.asc.models;

import java.util.ArrayList;
import java.util.List;

public class FileCode {
    
    private String name;
    private List<String> code;
    
    public FileCode(String name, List<String> code) {
        this.name = name;
        this.code = code;
    }

    public FileCode() {
        this("", new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public List<String> getCode() {
        return code;
    }
}
