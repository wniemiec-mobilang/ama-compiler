package wniemiec.mobilang.asc.models;

import java.util.ArrayList;
import java.util.List;


/**
 * Responsible for representing a file code.
 */
public class FileCode {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String name;
    private List<String> code;
    

    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public FileCode(String name, List<String> code) {
        this.name = name;
        this.code = code;
    }

    public FileCode() {
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
