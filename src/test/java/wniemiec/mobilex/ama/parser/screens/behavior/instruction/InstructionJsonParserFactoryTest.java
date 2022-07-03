package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.parser.exception.FactoryException;


class InstructionJsonParserFactoryTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String instructionType;
    private InstructionJsonParser obtainedInstance;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        instructionType = null;
        obtainedInstance = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testBlockStatementInstruction() throws FactoryException {
        withInstructionType("BlockStatement");
        runFactory();
        assertObtainedInstanceIs(BlockStatementInstructionJsonParser.class);
    }

    @Test
    void testClassDeclarationInstruction() throws FactoryException {
        withInstructionType("ClassDeclaration");
        runFactory();
        assertObtainedInstanceIs(ClassDeclarationInstructionJsonParser.class);
    }

    @Test
    void testExpressionStatementInstruction() throws FactoryException {
        withInstructionType("ExpressionStatement");
        runFactory();
        assertObtainedInstanceIs(ExpressionStatementInstructionJsonParser.class);
    }

    @Test
    void testForInStatementInstruction() throws FactoryException {
        withInstructionType("ForInStatement");
        runFactory();
        assertObtainedInstanceIs(ForInStatementInstructionJsonParser.class);
    }

    @Test
    void testForOfStatementInstruction() throws FactoryException {
        withInstructionType("ForOfStatement");
        runFactory();
        assertObtainedInstanceIs(ForOfStatementInstructionJsonParser.class);
    }

    @Test
    void testForStatementInstruction() throws FactoryException {
        withInstructionType("ForStatement");
        runFactory();
        assertObtainedInstanceIs(ForStatementInstructionJsonParser.class);
    }

    @Test
    void testFunctionDeclarationInstruction() throws FactoryException {
        withInstructionType("FunctionDeclaration");
        runFactory();
        assertObtainedInstanceIs(FunctionDeclarationInstructionJsonParser.class);
    }

    @Test
    void testIfStatementInstruction() throws FactoryException {
        withInstructionType("IfStatement");
        runFactory();
        assertObtainedInstanceIs(IfStatementInstructionJsonParser.class);
    }

    @Test
    void testReturnInstruction() throws FactoryException {
        withInstructionType("ReturnStatement");
        runFactory();
        assertObtainedInstanceIs(ReturnStatementInstructionJsonParser.class);
    }

    @Test
    void testVariableDeclaration() throws FactoryException {
        withInstructionType("VariableDeclaration");
        runFactory();
        assertObtainedInstanceIs(VariableDeclarationInstructionJsonParser.class);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withInstructionType(String type) {
        instructionType = type;
    }

    private void runFactory() throws FactoryException {
        obtainedInstance = InstructionJsonParserFactory.get(instructionType);
    }

    private void assertObtainedInstanceIs(Class<? extends InstructionJsonParser> parserClass) {
        Assertions.assertEquals(obtainedInstance.getClass(), parserClass);
    }
}
