package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.parser.exception.FactoryException;


class ExpressionJsonParserFactoryTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String expressionType;
    private ExpressionJsonParser obtainedInstance;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        expressionType = null;
        obtainedInstance = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testArrayExpression() throws FactoryException {
        withExpressionType("ArrayExpression");
        runFactory();
        assertObtainedInstanceIs(ArrayExpressionJsonParser.class);
    }

    @Test
    void testArrowFunction() throws FactoryException {
        withExpressionType("ArrowFunction");
        runFactory();
        assertObtainedInstanceIs(ArrowFunctionExpressionJsonParser.class);
    }

    @Test
    void testAssignmentPatternExpression() throws FactoryException {
        withExpressionType("AssignmentPatternExpression");
        runFactory();
        assertObtainedInstanceIs(AssignmentPatternExpressionJsonParser.class);
    }

    @Test
    void testBinaryExpression() throws FactoryException {
        withExpressionType("BinaryExpression");
        runFactory();
        assertObtainedInstanceIs(BinaryExpressionJsonParser.class);
    }

    @Test
    void testCallExpression() throws FactoryException {
        withExpressionType("CallExpression");
        runFactory();
        assertObtainedInstanceIs(CallExpressionJsonParser.class);
    }

    @Test
    void testConditionalExpression() throws FactoryException {
        withExpressionType("ConditionalExpression");
        runFactory();
        assertObtainedInstanceIs(ConditionalExpressionJsonParser.class);
    }

    @Test
    void testFunctionExpression() throws FactoryException {
        withExpressionType("FunctionExpression");
        runFactory();
        assertObtainedInstanceIs(FunctionExpressionJsonParser.class);
    }

    @Test
    void testIdentifierExpression() throws FactoryException {
        withExpressionType("IdentifierExpression");
        runFactory();
        assertObtainedInstanceIs(IdentifierExpressionJsonParser.class);
    }

    @Test
    void testLiteralExpression() throws FactoryException {
        withExpressionType("LiteralExpression");
        runFactory();
        assertObtainedInstanceIs(LiteralExpressionJsonParser.class);
    }

    @Test
    void testLogicalExpression() throws FactoryException {
        withExpressionType("LogicalExpression");
        runFactory();
        assertObtainedInstanceIs(LogicalExpressionJsonParser.class);
    }

    @Test
    void testMemberExpression() throws FactoryException {
        withExpressionType("MemberExpression");
        runFactory();
        assertObtainedInstanceIs(MemberExpressionJsonParser.class);
    }

    @Test
    void testObjectExpression() throws FactoryException {
        withExpressionType("ObjectExpression");
        runFactory();
        assertObtainedInstanceIs(ObjectExpressionJsonParser.class);
    }

    @Test
    void testTemplateElement() throws FactoryException {
        withExpressionType("TemplateElement");
        runFactory();
        assertObtainedInstanceIs(TemplateElementExpressionJsonParser.class);
    }

    @Test
    void testTemplateLiteral() throws FactoryException {
        withExpressionType("TemplateLiteral");
        runFactory();
        assertObtainedInstanceIs(TemplateLiteralExpressionJsonParser.class);
    }

    @Test
    void testUpdateExpression() throws FactoryException {
        withExpressionType("UpdateExpression");
        runFactory();
        assertObtainedInstanceIs(UpdateExpressionJsonParser.class);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withExpressionType(String type) {
        expressionType = type;
    }

    private void runFactory() throws FactoryException {
        obtainedInstance = ExpressionJsonParserFactory.get(expressionType);
    }

    private void assertObtainedInstanceIs(Class<? extends ExpressionJsonParser> parserClass) {
        Assertions.assertEquals(obtainedInstance.getClass(), parserClass);
    }
}
