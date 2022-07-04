package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class InstructionParserTest {
   
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private InstructionParser parser;
    private JSONObject instruction;
    private Instruction parsedInstruction;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = InstructionParser.getInstance();
        instruction = null;
        parsedInstruction = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testBlockStatementInstruction() throws ParseException, IOException {
        withInstruction(
            buildBlockStatement(
                buildVariableDeclaration("let", "i", buildLiteral(0))
            )
        );
        doParsing();
        assertParsedCodeIs("{ let i = 0 }");
    }

    @Test
    void testClassDeclarationInstruction() throws ParseException, IOException {
        withInstruction(
            buildClassDeclaration(
                "StringUtils",
                null
            )
        );

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            doParsing();
        });
    }

    @Test
    void testExpressionStatementInstruction() throws ParseException, IOException {
        withInstruction(
            buildExpressionStatement(
                buildPostIncrementExpression("i")
            )
        );
        doParsing();
        assertParsedCodeIs("i++");
    }

    @Test
    void testForInStatementInstruction() throws ParseException, IOException {
        withInstruction(
            buildForInInstruction(
                buildVariableDeclaration("let", "item"),
                buildIdentifier("items"),
                buildExpressionStatement(buildPostIncrementExpression("total"))
            )
        );
        doParsing();
        assertParsedCodeIs("for (let item in items) total++");
    }

    @Test
    void testForOfStatementInstruction() throws ParseException, IOException {
        withInstruction(
            buildForOfInstruction(
                buildVariableDeclaration("let", "item"),
                buildIdentifier("items"),
                buildExpressionStatement(buildPostIncrementExpression("total"))
            )
        );
        doParsing();
        assertParsedCodeIs("for (let item of items) total++");
    }

    @Test
    void testForStatementInstruction() throws ParseException, IOException {
        withInstruction(
            buildForInstruction(
                buildVariableDeclaration("let", "i", buildLiteral(0)),
                buildIdentifier("hasItems"),
                buildPostIncrementExpression("i"),
                buildExpressionStatement(buildPostIncrementExpression("total"))
            )
        );
        doParsing();
        assertParsedCodeIs("for (let i = 0; hasItems; i++) total++");
    }

    @Test
    void testAsyncFunctionDeclarationInstruction() throws ParseException, IOException {
        withInstruction(
            buildASyncFunctionDeclaration(
                "getFirst",
                buildFunctionParameters(buildIdentifier("n1"), buildIdentifier("n2")),
                buildReturnInstruction(buildIdentifier("n1"))
            )
        );
        doParsing();
        assertParsedCodeIs("async function getFirst(n1, n2) return n1");
    }

    @Test
    void testIfStatementInstruction() throws ParseException, IOException {
        withInstruction(
            buildIfStatement(
                buildIdentifier("hasValue"), 
                buildReturnInstruction()
            )
        );
        doParsing();
        assertParsedCodeIs("if (hasValue) return");
    }

    @Test
    void testReturnInstruction() throws ParseException, IOException {
        withInstruction(buildReturnInstruction());
        doParsing();
        assertParsedCodeIs("return");
    }

    @Test
    void testVariableDeclaration() throws ParseException, IOException {
        withInstruction(buildVariableDeclaration("const", "pi", buildLiteral(3.1416)));
        doParsing();
        assertParsedCodeIs("const pi = 3.1416");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildLiteral(Object value) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Literal");
        expression.put("value", value);

        return expression;
    }

    private JSONObject buildVariableDeclaration(String kind, String name) {
        return buildVariableDeclaration(kind, name, null);
    }

    private JSONObject buildVariableDeclaration(String kind, String name, JSONObject value) {
        JSONObject expression = new JSONObject();
        JSONArray declarations = new JSONArray();

        declarations.put(buildDeclarator("undefined", name, value));
        expression.put("type", "VariableDeclaration");
        expression.put("kind", kind);
        expression.put("declarations", declarations);

        return expression;
    }

    private JSONObject buildDeclarator(String type, String name, JSONObject value) {
        JSONObject declarator = new JSONObject();
        JSONObject idObject = new JSONObject();

        declarator.put("type", "Identifier");
        declarator.put("id", idObject);
        declarator.put("init", value);

        idObject.put("type", type);
        idObject.put("name", name);

        return declarator;
    }

    private JSONObject buildBlockStatement(JSONObject... instructions) {
        JSONObject instruction = new JSONObject();
        JSONArray body = new JSONArray();

        Arrays.stream(instructions).forEach(body::put);
        instruction.put("type", "BlockStatement");
        instruction.put("body", body);

        return instruction;
    }

    private void withInstruction(JSONObject instruction) {
        this.instruction = instruction;
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(instruction);
    }

    private void assertParsedCodeIs(String code) {
        assertHasSameLine(code, parsedInstruction.toCode());
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

    private JSONObject buildClassDeclaration(String name, JSONArray body) {
        return buildClassDeclaration(name, null, body);
    }

    private JSONObject buildClassDeclaration(
        String name,
        JSONObject superClass,
        JSONArray body
    ) {
        JSONObject instruction = new JSONObject();
        JSONObject id = new JSONObject();

        id.put("name", name);
        instruction.put("type", "ClassDeclaration");
        instruction.put("id", id);
        instruction.put("superClass", superClass);
        instruction.put("body", body);

        return instruction;
    }

    private JSONObject buildExpressionStatement(JSONObject expression) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ExpressionStatement");
        instruction.put("expression", expression);

        return instruction;
    }

    private JSONObject buildForInInstruction(
        JSONObject left,
        JSONObject right,
        JSONObject body
    ) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ForInStatement");
        instruction.put("left", left);
        instruction.put("right", right);
        instruction.put("body", body);

        return instruction;
    }

    private JSONObject buildForOfInstruction(
        JSONObject left,
        JSONObject right,
        JSONObject body
    ) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ForOfStatement");
        instruction.put("left", left);
        instruction.put("right", right);
        instruction.put("body", body);

        return instruction;
    }

    private JSONObject buildPostIncrementExpression(String label) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "UpdateExpression");
        instruction.put("operator", "++");
        instruction.put("prefix", false);
        instruction.put("argument", buildIdentifier(label));

        return instruction;
    }

    private JSONObject buildForInstruction(
        JSONObject init,
        JSONObject test,
        JSONObject update,
        JSONObject body
    ) {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "ForStatement");
        instruction.put("init", init);
        instruction.put("test", test);
        instruction.put("update", update);
        instruction.put("body", body);

        return instruction;
    }

    private JSONArray buildFunctionParameters(JSONObject... parameters) {
        JSONArray jsonArray = new JSONArray();

        Arrays.stream(parameters).forEach(jsonArray::put);

        return jsonArray;
    }

    private JSONObject buildASyncFunctionDeclaration(
        String name, 
        JSONArray params, 
        JSONObject body
    ) {
        return buildFunctionDeclaration(true, name, params, body);
    }

    private JSONObject buildFunctionDeclaration(
        boolean async, 
        String name, 
        JSONArray params, 
        JSONObject body
    ) {
        JSONObject expression = new JSONObject();
        JSONObject id = new JSONObject();

        id.put("name", name);
        expression.put("type", "FunctionDeclaration");
        expression.put("id", id);
        expression.put("async", async);
        expression.put("params", params);
        expression.put("body", body);

        return expression;
    }

    private JSONObject buildSyncFunctionDeclaration(
        String name, 
        JSONArray params, 
        JSONObject body
    ) {
        return buildFunctionDeclaration(false, name, params, body);
    }

    private JSONObject buildIfStatement(JSONObject test, JSONObject consequent) {
        JSONObject expression = new JSONObject();

        expression.put("type", "IfStatement");
        expression.put("test", test);
        expression.put("consequent", consequent);

        return expression;
    }

    private JSONObject buildReturnInstruction() {
        JSONObject expression = new JSONObject();

        expression.put("type", "ReturnStatement");

        return expression;
    }

    private JSONObject buildReturnInstruction(JSONObject argument) {
        JSONObject expression = new JSONObject();

        expression.put("type", "ReturnStatement");
        expression.put("argument", argument);

        return expression;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }
}
