package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AssignmentPropertyTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private AssignmentProperty assignmentProperty;
    private Identifier key;
    private Expression value;
    private boolean shorthand;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        assignmentProperty = null;
        key = null;
        value = null;
        shorthand = false;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithKeyAndValue() {
        withKey(new Identifier("index"));
        withValue(Literal.ofNumber("0"));
        buildAssignmentProperty();
        assertToCodeIs("index: 0");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withKey(Identifier identifier) {
        key = identifier;
    }

    private void withValue(Expression expression) {
        value = expression;
    }

    private void buildAssignmentProperty() {
        assignmentProperty = new AssignmentProperty(key, value, shorthand);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, assignmentProperty.toCode());
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
}
