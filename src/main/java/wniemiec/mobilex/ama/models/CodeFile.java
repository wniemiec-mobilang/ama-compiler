package wniemiec.mobilex.ama.models;

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
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        CodeFile other = (CodeFile) obj;
        
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } 
        else if (!code.equals(other.code)) {
            return false;
        }

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } 
        else if (!name.equals(other.name)) {
            return false;
        }
        
        return true;
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
