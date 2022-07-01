package wniemiec.mobilex.ama.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StyleSheetRuleTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private StyleSheetRule styleSheetRule;
    private List<String> selectors;
    private Map<String, String> declarations;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        styleSheetRule = new StyleSheetRule();
        selectors = new ArrayList<>();
        declarations = new HashMap<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetSingleSelector() {
        withSelectors("div");
        buildRule();
        assertHasCorrectSelectors();
    }

    @Test
    void testGetMultipleSelector() {
        withSelectors("div", "p", "h2");
        buildRule();
        assertHasCorrectSelectors();
    }

    @Test
    void testSingleDeclaration() {
        withDeclaration("background-color", "red");
        buildRule();
        assertGetDeclarationsAreCorrect();
    }

    @Test
    void testMultipleDeclarations() {
        withDeclaration("background-color", "red");
        withDeclaration("color", "blue");
        withDeclaration("border", "1px solid #334455");
        buildRule();
        assertGetDeclarationsAreCorrect();
    }

    @Test
    void testMultipleSelectorsAndDeclarations() {
        withSelectors("div", "p", "h2");
        withDeclaration("background-color", "red");
        withDeclaration("color", "blue");
        withDeclaration("border", "1px solid #334455");
        buildRule();
        assertHasCorrectSelectors();
        assertGetDeclarationsAreCorrect();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withSelectors(String... selectors) {
        this.selectors = Arrays.asList(selectors);
    }

    private void buildRule() {
        selectors.forEach(styleSheetRule::addSelector);
        declarations.forEach(styleSheetRule::addDeclaration);
    }

    private void assertHasCorrectSelectors() {
        selectors.forEach(selector -> {
            Assertions.assertTrue(styleSheetRule.hasSelector(selector));
        });
    }

    private void withDeclaration(String property, String value) {
        declarations.put(property, value);
    }

    private void assertGetDeclarationsAreCorrect() {
        Assertions.assertEquals(declarations, styleSheetRule.getDeclarations());
    }
}
