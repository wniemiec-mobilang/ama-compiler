package wniemiec.mobilex.ama.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Responsible for representing all the code files of a project along with 
 * their dependencies.
 */
public class Project {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<CodeFile> codeFiles;
    private final Set<String> dependencies;
    

    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public Project(List<CodeFile> codeFiles, Set<String> dependencies) {
        this.codeFiles = codeFiles;
        this.dependencies = dependencies;
    }

    public Project(List<CodeFile> codeFiles) {
        this(codeFiles, new HashSet<>());
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<CodeFile> getCodeFiles() {
        return codeFiles;
    }

    public Set<String> getDependencies() {
        return dependencies;
    }
}
