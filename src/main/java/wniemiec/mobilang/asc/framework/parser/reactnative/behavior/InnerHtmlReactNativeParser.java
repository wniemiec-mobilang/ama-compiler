package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wniemiec.mobilang.asc.framework.parser.reactnative.structure.ReactNativeStructureParser;
import wniemiec.mobilang.asc.framework.parser.reactnative.style.ReactNativeStyleApplicator;
import wniemiec.mobilang.asc.models.behavior.Variable;
import wniemiec.mobilang.asc.models.tag.Tag;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.html.HtmlParser;
import wniemiec.mobilang.asc.parser.screens.structure.StructureParser;
import wniemiec.mobilang.asc.utils.StringUtils;


/**
 * Responsible for parsing innerHtml code from behavior code on React Native 
 * framework.
 */
class InnerHtmlReactNativeParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<String> declaredStateBodyVariables;
    private final List<Variable> stateDeclarations;
    private final List<String> stateBody;
    private final Tag structure;
    private final Map<String, String> symbolTable;
    private final ReactNativeStyleApplicator styleApplicator;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public InnerHtmlReactNativeParser(
        List<Variable> stateDeclarations, 
        List<String> stateBody, 
        Map<String, String> symbolTable,
        Tag structure,
        ReactNativeStyleApplicator styleApplicator
    ) {
        this.symbolTable = symbolTable;
        this.stateDeclarations = stateDeclarations;
        this.stateBody = stateBody;
        this.structure = structure;
        this.styleApplicator = styleApplicator;
        declaredStateBodyVariables = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Parses inner html code using the following approach:
     * 
     *     <id> = <attribution>
     * 1. if <id> is in symbolTable
     * 2. then replace <id> by its content obtained from symbolTable
     * 3. create '<id>' as list if it has not been created
     * 4. create local var '_<id>' as list if it has not been created
     * 5. '_<id>'.push(<attribution>) 
     * 6. set<id>(_<id>)
     * 
     * @param       code Inner html code
     * 
     * @return      Parsed code
     * 
     * @throws      IOException If code is invalid
     * @throws      ParseException If parsing failed
     */
    public String parse(String code) throws IOException, ParseException {
        String htmlTarget = extractInnerHtmlTarget(code);
        String targetTagId = parseInnerHtmlTarget(htmlTarget, code);
        String normalizedHtmlTarget = normalizeIdentifier(htmlTarget);
        
        parseInnerHtmlVariable(normalizedHtmlTarget, targetTagId);
        parseInnerHtmlAssignment(normalizedHtmlTarget, code);

        return "";
    }

    private String extractInnerHtmlTarget(String parsedCode) {
        return parsedCode.split(".innerHTML")[0];
    }

    private String parseInnerHtmlTarget(String htmlTarget, String code) {
        Variable stateVar = buildStateVariable(htmlTarget);

        if (!stateDeclarations.contains(stateVar)) {
            stateDeclarations.add(stateVar);
        }

        return getTagIdFromHtmlTarget(htmlTarget, code);
    }

    private Variable buildStateVariable(String tagId) {
        String normalizedTagId = normalizeIdentifier(tagId);
        
        return new Variable(normalizedTagId, "state", "[]");
    }

    private String normalizeIdentifier(String identifier) {
        if (!identifier.contains("-")) {
            return identifier;
        }
        
        StringBuilder normalizedId = new StringBuilder();

        for (String term : identifier.split("-")) {
            normalizedId.append(StringUtils.capitalize(term));
        }

        normalizedId.setCharAt(0, Character.toUpperCase(normalizedId.charAt(0)));

        return normalizedId.toString();
    }

    private String getTagIdFromHtmlTarget(String htmlTarget, String code) {
        if (symbolTable.containsKey(htmlTarget)) {
            return symbolTable.get(htmlTarget);
        }
        
        return extractIdFromGetElementById(code);
    }

    private String extractIdFromGetElementById(String line) {
        String id = "";
        Pattern p = Pattern.compile(".*(getElementById\\(\\\"(.+)\\\"\\)).*");
        Matcher m = p.matcher(line);

        if (m.matches()) {
            id = m.group(2);
        }

        return id;
    }
    
    private void parseInnerHtmlVariable(String htmlTarget, String targetTagId) {
        if (wasHtmlTargetDeclared(htmlTarget)) {
            return;
        }

        String variableDeclaration = buildHtmlTargetVariable(htmlTarget);
        String variableAttribution = buildHtmlTargetAttribution(targetTagId);

        declaredStateBodyVariables.add(variableDeclaration);
        stateBody.add(variableDeclaration + variableAttribution);
    }

    private String buildHtmlTargetAttribution(String targetTagId) {
        StringBuilder code = new StringBuilder();
        Tag referedTag = structure.getTagWithId(targetTagId);

        code.append("=[");
        code.append(referedTag.toChildrenCode());
        code.append(']');

        return code.toString();
    }

    private boolean wasHtmlTargetDeclared(String htmlTarget) {
        String variableDeclaration = buildHtmlTargetVariable(htmlTarget);

        return declaredStateBodyVariables.contains(variableDeclaration);
    }

    private String buildHtmlTargetVariable(String htmlTarget) {
        return "let _" + htmlTarget;
    }

    private void parseInnerHtmlAssignment(String htmlTarget, String parsedCode) 
    throws IOException, ParseException {
        String innerHtmlAssignment = extractInnerHtmlAssignment(parsedCode);
        String parsedHtmlAssignment = parseInnerHtmlAssignment(innerHtmlAssignment); 
        
        if (parsedCode.contains(".innerHTML=")) { 
            stateBody.add(buildInnerHtmlDeclarationCode(htmlTarget, parsedHtmlAssignment));
        }
        else if (parsedCode.contains(".innerHTML+=")) { 
            stateBody.add(buildInnerHtmlAppendCode(htmlTarget, parsedHtmlAssignment));
        }
    }

    private String buildInnerHtmlDeclarationCode(String target, String assignment) {
        StringBuilder code = new StringBuilder();

        code.append('_');
        code.append(target);
        code.append("=[");
        code.append(assignment);
        code.append(']');

        return code.toString();
    }

    private String buildInnerHtmlAppendCode(String target, String assignment) {
        StringBuilder code = new StringBuilder();

        code.append('_');
        code.append(target);
        code.append(".push(");
        code.append(assignment);
        code.append(')');

        return code.toString();
    }

    private String extractInnerHtmlAssignment(String parsedCode) {
        return parsedCode.substring(parsedCode.indexOf("=")+1);
    }

    private String parseInnerHtmlAssignment(String innerHtmlAssignment) 
    throws IOException, ParseException {
        if (isEmptyString(innerHtmlAssignment)) {
            return "";
        }

        Tag htmlTag = parseHtml(innerHtmlAssignment);
        Tag reactNativeTag = convertHtmlToReactNative(htmlTag);

        return buildTagCode(reactNativeTag);
    }

    private boolean isEmptyString(String innerHtmlAssignment) {
        return innerHtmlAssignment.matches("\"[\\s\\t]*\"");
    }

    private Tag parseHtml(String html) throws IOException, ParseException {
        String ast = buildAstForHtml(html);
        Tag tag = parseHtmlAst(ast);
        
        applyStyleTo(tag);

        return tag;
    }

    private String buildAstForHtml(String html) throws IOException {
        String normalizedHtml = html.replace("`", "");
        HtmlParser htmlParser = new HtmlParser();
        
        return htmlParser.parse(normalizedHtml);
    }

    private Tag parseHtmlAst(String ast) throws ParseException {
        StructureParser structureParser = new StructureParser(ast);
        
        return structureParser.parse();
    }

    private void applyStyleTo(Tag root) {
        styleApplicator.apply(root);
    }

    private Tag convertHtmlToReactNative(Tag htmlTag) throws ParseException {
        ReactNativeStructureParser parser = new ReactNativeStructureParser(htmlTag);
        Tag rnTag = parser.parse();
        
        // TODO: mandar para o behavior parse 'root' (pd ter behavior para alguma tag)

        return rnTag;
    }

    private String buildTagCode(Tag rnTag) {
        StringBuilder code = new StringBuilder();

        for (String line : rnTag.toCode()) {
            code.append(line);
        }

        return code.toString();
    }
}
