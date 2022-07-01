package wniemiec.mobilex.ama.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StyleTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Style style;
    private List<StyleSheetRule> rules;
    private StyleSheetRule rule;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        style = new Style();
        rule = new StyleSheetRule();
        rules = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testAddRule() {
        withSelectors("div");
        withDeclaration("background-color", "red");
        addRule();
        assertHasCorrectRules();
    }

    @Test
    void testAddMultipleRules() {
        withSelectors("div");
        withDeclaration("background-color", "red");
        addRule();
        withSelectors("div", "p");
        withDeclaration("background-color", "blue");
        withDeclaration("color", "purple");
        withDeclaration("border", "1px solid #334455");
        addRule();
        assertHasCorrectRules();
    }

    @Test
    void testGetRulesForSelector() {
        withSelectors("div");
        withDeclaration("background-color", "red");
        addRule();
        assertHasCorrectRulesForSelectors("div");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withSelectors(String... selectors) {
        Arrays.stream(selectors).forEach(rule::addSelector);
    }

    private void withDeclaration(String property, String value) {
        rule.addDeclaration(property, value);
    }

    private void addRule() {
        style.addRule(rule);
        rules.add(rule);
        rule = new StyleSheetRule();
    }

    private void assertHasCorrectRules() {
        Assertions.assertEquals(rules, style.getRules());
    }

    private void assertHasCorrectRulesForSelectors(String... selectors) {
        Assertions.assertEquals(
            rules.get(0).getDeclarations(), 
            style.getRulesForSelector(Arrays.asList(selectors))
        );
    }
}
