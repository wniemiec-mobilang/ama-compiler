package wniemiec.mobilang.ama.models;

import java.util.ArrayList;
import java.util.List;


/**
 * Responsible for representing a file code.
 */
public class CodeFile {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String name;
    private final List<String> code;
    

    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public CodeFile(String name, List<String> code) {
        this.name = name;
        this.code = code;
    }

    public CodeFile() {
        this("", new ArrayList<>());
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public List<String> getCode() {
        return code;
    }
}
