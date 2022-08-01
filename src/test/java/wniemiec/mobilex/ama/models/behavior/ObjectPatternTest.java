package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ObjectPatternTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ObjectPattern objectPattern;
    private List<AssignmentProperty> properties;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        objectPattern = null;
        properties = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithOneProperty() {
        withProperty(
            buildAssignmentProperty(
                new Identifier("a"), 
                Literal.ofNumber("2")
            )
        );
        buildObjectPattern();
        assertToCodeIs("{ a: 2 }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private AssignmentProperty buildAssignmentProperty(Identifier key, Expression value) {
        return new AssignmentProperty(key, value, false);
    }
    
    private void withProperty(AssignmentProperty property) {
        properties.add(property);
    }

    private void buildObjectPattern() {
        objectPattern = new ObjectPattern(properties);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, objectPattern.toCode());
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
