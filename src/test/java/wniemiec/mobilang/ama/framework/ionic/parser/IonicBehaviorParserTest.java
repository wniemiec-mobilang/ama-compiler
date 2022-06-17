package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.models.behavior.AssignmentExpression;
import wniemiec.mobilang.ama.models.behavior.Behavior;
import wniemiec.mobilang.ama.models.behavior.BlockStatement;
import wniemiec.mobilang.ama.models.behavior.Declaration;
import wniemiec.mobilang.ama.models.behavior.Declarator;
import wniemiec.mobilang.ama.models.behavior.Expression;
import wniemiec.mobilang.ama.models.behavior.FunctionDeclaration;
import wniemiec.mobilang.ama.models.behavior.Identifier;
import wniemiec.mobilang.ama.models.behavior.Instruction;
import wniemiec.mobilang.ama.models.behavior.Literal;
import wniemiec.mobilang.ama.models.behavior.ReturnStatement;


class IonicBehaviorParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private IonicBehaviorParser parser;
    private Behavior behavior;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = new IonicBehaviorParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParamDirective() throws CoderException {
        withBehavior(buildDeclarationWithIdAndAssignment("id", "mobilang::param::id"));
        doParsing();
        assertCodeEquals("let id = this.routeParams.snapshot.params.q.split('id__eq__')[1].split('&')[0]");
    }

    @Test
    void testInputDirective() throws CoderException {
        withBehavior(buildDeclarationWithIdAndAssignment("foo", "mobilang::input::foo"));
        doParsing();
        assertCodeEquals("let foo = this.__input_foo");
    }

    @Test
    void testNullBehavior() throws CoderException {
        withBehavior(null);
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            doParsing();
        });
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Behavior buildDeclarationWithIdAndAssignment(String id, String assignment) {
        Declaration declaration = buildLiteralDeclaration(id, assignment);

        return buildBehaviorWith(declaration);
    }

    private Declaration buildLiteralDeclaration(String id, String assignment) {
        Declarator declarator = new Declarator(
            "string", 
            "let", 
            id, 
            Literal.ofString(assignment)
        );
        
        return new Declaration("let", declarator);
    }

    private Behavior buildBehaviorWith(Instruction... declarations) {
        return new Behavior(Arrays.asList(declarations));
    }

    private void withBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    private void doParsing() throws CoderException {
        parser.parse(behavior);
    }
    
    private void assertCodeEquals(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, parser.getParsedCode());
        assertHasSameLines(expectedCode, parser.getParsedCode());
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

    private Behavior buildSumFunctionBetweenTwoNumbers() {
        Expression arg1 = new Identifier("arg1");
        Expression arg2 = new Identifier("arg2");
        Instruction functionReturn = buildFunctionReturn(buildSumExpression(arg1, arg2));
        FunctionDeclaration function = buildFunction(
            "sum",
            buildFunctionParameters(arg1, arg2),
            buildFunctionBody(functionReturn)
        );

        return buildBehaviorWith(function);
    }

    private AssignmentExpression buildSumExpression(Expression arg1, Expression arg2) {
        return new AssignmentExpression("+", arg1, arg2);
    }

    private Instruction buildFunctionReturn(Expression returnExpression) {
        return new ReturnStatement(returnExpression);
    }

    private List<Expression> buildFunctionParameters(Expression... params) {
        return Arrays.asList(params);
    }

    private Instruction buildFunctionBody(Instruction... instructions) {
        return new BlockStatement(Arrays.asList(instructions));
    }

    private FunctionDeclaration buildFunction(
        String name, 
        List<Expression> parameters, 
        Instruction body
    ) {
        return new FunctionDeclaration(name, false, parameters, body);
    }
}
