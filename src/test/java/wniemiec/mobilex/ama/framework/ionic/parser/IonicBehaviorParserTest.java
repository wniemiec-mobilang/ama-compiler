package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.ExpressionStatement;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.Literal;
import wniemiec.mobilex.ama.models.behavior.TemplateElement;
import wniemiec.mobilex.ama.models.behavior.TemplateLiteral;


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
    void testBehaviorWithParamDirective() throws CoderException {
        withBehavior(buildDeclarationWithIdAndAssignment("id", "mobilang::param::id"));
        doParsing();
        assertCodeEquals("let id = this.routeParams.snapshot.params.q.split('id__eq__')[1].split('&')[0];");
    }

    @Test
    void testBehaviorWithInputDirective() throws CoderException {
        withBehavior(buildDeclarationWithIdAndAssignment("foo", "mobilang::input::foo"));
        doParsing();
        assertCodeEquals("let foo = this.__input_foo;");
    }

    @Test
    void testNullBehavior() throws CoderException {
        withBehavior(null);
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            doParsing();
        });
    }

    @Test
    void testBehaviorWithEvent() throws CoderException {
        withBehavior(buildTemplateLiteralWithoutExpression("<button onclick=\"alert('hi!')\">"));
        doParsing();
        assertCodeEquals(
            "`<button id=\"" + getGeneratedId(0) + "\">`;"
            + "document.getElementById(\"" + getGeneratedId(0) + "\").onclick=()=>alert('hi!')"
        );
    }

    @Test
    void testBehaviorWithStyle() throws CoderException {
        withBehavior(buildTemplateLiteralWithoutExpression("document.getElementById('body').style.opacity=0"));
        doParsing();
        assertCodeEquals("`document.getElementById('body').style.opacity=\"0\"`;");
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

    private Behavior buildTemplateLiteralWithoutExpression(String text) {
        List<Expression> quasis = List.of(new TemplateElement(text, false));
        TemplateLiteral templateLiteral = new TemplateLiteral(new ArrayList<>(), quasis);
        
        return buildBehaviorWith(new ExpressionStatement(templateLiteral));
    }

    private String getGeneratedId(int index) {
        return parser.getGeneratedIds().get(index);
    }
}
