package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.Identifier;
import wniemiec.mobilex.ama.models.behavior.TemplateElement;
import wniemiec.mobilex.ama.models.behavior.TemplateLiteral;


class TemplateLiteralTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TemplateLiteral templateLiteral;
    private List<Expression> expressions;
    private List<Expression> quasis;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        templateLiteral = null;
        expressions = new ArrayList<>();
        quasis = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithExpression() {
        withExpression(new Identifier("result"));
        buildTemplateLiteral();
        assertToCodeIs("`${result}`");
    }

    @Test
    void testToCodeWithQuasis() {
        withQuasis(new TemplateElement("2 plus 2 is equals to ", false));
        buildTemplateLiteral();
        assertToCodeIs("`2 plus 2 is equals to `");
    }

    @Test
    void testToCodeWithExpressionAndQuasis() {
        withQuasis(new TemplateElement("2 plus 2 is equals to ", false));
        withExpression(new Identifier("result"));
        buildTemplateLiteral();
        assertToCodeIs("`2 plus 2 is equals to ${result}`");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withQuasis(Expression expression) {
        quasis.add(expression);
    }

    private void withExpression(Expression expression) {
        expressions.add(expression);
    }

    private void buildTemplateLiteral() {
        templateLiteral = new TemplateLiteral(expressions, quasis);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, templateLiteral.toCode());
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
