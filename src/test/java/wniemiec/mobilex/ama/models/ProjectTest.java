package wniemiec.mobilex.ama.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProjectTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String CODE_FILENAME;
    private Project project;
    private List<CodeFile> codeFiles;
    private Set<String> dependencies;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        CODE_FILENAME = "example.js";
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        project = null;
        codeFiles = new ArrayList<>();
        dependencies = new HashSet<>();
    }

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testProjectWithoutCodeAndWithoutDependencies() {
        withCodeFile(null);
        buildProject();
        assertHasCorrectCodeFiles();
    }

    @Test
    void testProjectWithCodeAndWithoutDependencies() {
        withCodeFile(buildExampleCodeFile(
            "alert('check the console');",
            "console.log('hello world!');"
        ));
        buildProject();
        assertHasCorrectCodeFiles();
    }

    @Test
    void testProjectWithCodeAndWithDependencies() {
        withCodeFile(buildExampleCodeFile(
            "alert('check the console');",
            "console.log('hello world!');"
        ));
        withDependencies(
            "@foo/bar",
            "another-dependency/some-package"
        );
        buildProject();
        assertHasCorrectCodeFiles();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private CodeFile buildExampleCodeFile(String... code) {
        return new CodeFile(
            CODE_FILENAME,
            Arrays.asList(code)
        );
    }
    
    private void withCodeFile(CodeFile codeFile) {
        codeFiles.add(codeFile);
    }

    private void buildProject() {
        project = new Project(codeFiles, dependencies);
    }

    private void assertHasCorrectCodeFiles() {
        Assertions.assertEquals(codeFiles, project.getCodeFiles());
    }

    private void withDependencies(String... projectDependencies) {
        Arrays.stream(projectDependencies).forEach(dependencies::add);
    }

    private void assertHasCorrectDependencies() {
        Assertions.assertEquals(dependencies, project.getDependencies());
    }
}
