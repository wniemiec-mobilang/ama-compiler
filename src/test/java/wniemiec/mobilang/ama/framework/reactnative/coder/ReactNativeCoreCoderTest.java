package wniemiec.mobilang.ama.framework.reactnative.coder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilang.ama.models.CodeFile;


class ReactNativeCoreCoderTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final int INDEX_INDEX_JS;
    private static final int INDEX_APP_JS;
    private ReactNativeCoreCoder coder;
    private List<CodeFile> obtainedCode;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        INDEX_INDEX_JS = 0;
        INDEX_APP_JS = 1;
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        coder = new ReactNativeCoreCoder();
        obtainedCode = new ArrayList<>();
    }
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testIndexFile() {
        runCoder();
        assertCoderGeneratedSomething();
        assertCodeFileHasName(
            "index.js",
            "src/App.js"
        );
        assertIndexCodeEquals(
            "import {AppRegistry} from 'react-native';",
            "import App from './src/App';",
            "import {name as appName} from './app.json';",
            "",
            "AppRegistry.registerComponent(appName, () => App);"
        );
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void runCoder() {
        obtainedCode = coder.generateCode();
    }

    private void assertCoderGeneratedSomething() {
        Assertions.assertFalse(obtainedCode.isEmpty());
    }

    private void assertCodeFileHasName(String... names) {
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(names[i], obtainedCode.get(i).getName());
        }
    }

    private void assertIndexCodeEquals(String... lines) {
        assertCodeEquals(INDEX_INDEX_JS, lines);
    }

    private void assertCodeEquals(int index, String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, obtainedCode.get(0).getCode());
        assertHasSameLines(expectedCode, obtainedCode.get(0).getCode());
    }

    private void assertHasSameSize(List<String> expected, List<String> obtained) {
        Assertions.assertEquals(expected.size(), obtained.size());
    }

    private void assertHasSameLines(List<String> expected, List<String> obtained) {
        for (int i = 0; i < expected.size(); i++) {            
            assertHasSameLine(expected.get(i), obtained.get(i));
        }
    }

    private void assertHasSameLine(String expected, String obtained) {
        Assertions.assertEquals(
            removeWhiteSpaces(expected),
            removeWhiteSpaces(obtained)
        );
    }

    private String removeWhiteSpaces(String text) {
        return text.replaceAll("[\\s\\t]+", "");
    }

    private void assertAppCodeEquals(String... lines) {
        assertCodeEquals(INDEX_APP_JS, lines);
    }
}
