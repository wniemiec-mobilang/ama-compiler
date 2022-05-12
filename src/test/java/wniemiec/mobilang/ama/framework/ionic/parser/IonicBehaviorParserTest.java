package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
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
import wniemiec.mobilang.ama.models.behavior.ExpressionStatement;
import wniemiec.mobilang.ama.models.behavior.Identifier;
import wniemiec.mobilang.ama.models.behavior.Instruction;
import wniemiec.mobilang.ama.models.behavior.Literal;
import wniemiec.mobilang.ama.models.behavior.Variable;
import wniemiec.mobilang.ama.models.tag.Tag;


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
    /*
        let id = "mobilang::param::id";
        let foo = "mobilang::input::foo"
        function sum(n1, n2) {
            return n1 + n2;
        }
        */
    @Test
    void testParamDirective() throws CoderException {
        withBehavior(buildDeclarationWithIdAndAssignment("id", "mobilang::param::id"));
        doParsing();
        assertCodeEquals(
            "\"use strict\";",
            "",
            "var id = this.routeParams.snapshot.params.q.split('id=')[1].split('&')[0];"
        );
    }

    private Behavior buildDeclarationWithIdAndAssignment(String id, String assignment) {
        Declaration declaration = buildLiteralDeclaration(id, assignment);

        return buildBehaviorWith(declaration);
    }

    private Declaration buildLiteralDeclaration(String id, String assignment) {
        Declarator declarator = new Declarator(
            "string", 
            "let", 
            id, 
            new Literal(assignment)
        );
        
        return new Declaration("let", declarator);
    }

    private Behavior buildBehaviorWith(Instruction... declarations) {
        return new Behavior(Arrays.asList(declarations));
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
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

    
}
