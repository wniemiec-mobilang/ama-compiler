package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import wniemiec.mobilang.asc.framework.parser.reactnative.style.ReactNativeStyleApplicator;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.models.behavior.Variable;
import wniemiec.mobilang.asc.models.tag.Tag;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing screen behavior of React Native framework, creating a 
 * React Native code from it.
 */
public class ReactNativeBehaviorParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final InnerHtmlReactNativeParser innerHtmlParser;
    private final GetElementByIdReactNativeParser getElementByIdParser;
    private final WindowReactNativeParser windowParser;
    private final DeclarationReactNativeParser declarationParser;
    private final DirectiveReactNativeParser directiveParser;
    private final List<Variable> stateDeclarations;
    private final List<String> stateBody;
    private final List<Instruction> behaviorCode;
    private final Tag structure;

    /**
     * key:     Variable id
     * value:   Tag id
     */
    private Map<String, String> symbolTable;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Behavior screen parser, creating a React Native code from it.
     * 
     * @param       behaviorCode Behavior code
     * @param       structure Screen structure tag
     * @param       styleApplicator Style applicator used at structure tag
     */
    public ReactNativeBehaviorParser(
        List<Instruction> behaviorCode,
        Tag structure,
        ReactNativeStyleApplicator styleApplicator
    ) {
        this.behaviorCode = behaviorCode;
        this.structure = structure;
        stateDeclarations = new ArrayList<>();
        stateBody = new ArrayList<>();
        symbolTable = new HashMap<>();
        innerHtmlParser = new InnerHtmlReactNativeParser(
            stateDeclarations, 
            stateBody, 
            symbolTable, 
            structure, 
            styleApplicator
        );
        getElementByIdParser = new GetElementByIdReactNativeParser(
            stateDeclarations, 
            symbolTable, 
            structure
        );
        windowParser = new WindowReactNativeParser();
        declarationParser = new DeclarationReactNativeParser(
            stateBody, 
            symbolTable
        );
        directiveParser = new DirectiveReactNativeParser();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Parses the behavior code, creating React Native code from it.
     * 
     * @throws      IOException If there is some error in behavior code
     * @throws      ParseException If parsing failed
     */
    public void parse() throws IOException, ParseException {
        parseCode();
        parseDeclarations();
        parseTagAttributes();
    }

    private void parseCode() throws IOException, ParseException {
        for (Instruction instruction : behaviorCode) {
            for (String line : extractCodeLines(instruction)) {
                parseCodeLine(line);
            }
        }
    }

    private String[] extractCodeLines(Instruction instruction) {
        return instruction.toCode().split("\n");
    }
    
    private void parseCodeLine(String code) throws IOException, ParseException {
        // TODO: Add compatibility with getElementsByClass or byQuery

        String parsedCode = code;

        if (isWindowCall(parsedCode)) {
            parsedCode = parseWindowCall(parsedCode);
        }
        else if (isInnerHtml(parsedCode)) { 
            parsedCode = parseInnerHtml(parsedCode);
        }

        if (isDeclarationWithGetElementById(parsedCode)) {
            parsedCode = parseDeclarationWithGetElementById(parsedCode);
        }
        else if (isCallFromGetElementById(parsedCode)) {
            parsedCode = parseCallFromGetElementById(parsedCode);
        }

        if (isDeclaration(parsedCode)) {
            parseDeclaration(parsedCode);
        }

        if (hasMobilangDirective(parsedCode)) {
            parsedCode = parseMobilangDirective(parsedCode);
        }

        if (!parsedCode.isEmpty()) {
            stateBody.add(parsedCode);
        }
    }

    private boolean isWindowCall(String code) {
        return code.contains("window.");
    }

    private String parseWindowCall(String code) {
        return windowParser.parse(code);
    }

    private boolean isInnerHtml(String code) {
        return code.contains("innerHTML");
    }

    private String parseInnerHtml(String parsedCode) throws IOException, ParseException {
        return innerHtmlParser.parse(parsedCode);
    }

    private boolean isDeclarationWithGetElementById(String code) {
        return code.matches("(const|var|let)[\\s\\t]+[A-z0-9_$]+.+document\\.getElementById\\(.+\\)");
    }

    private String parseDeclarationWithGetElementById(String code) {
        return getElementByIdParser.parseDeclarationWithGetElementById(code);
    }

    private void parseDeclaration(String code) {
        declarationParser.parseDeclaration(code);
    }

    private boolean isCallFromGetElementById(String code) {
        return code.matches("document\\.getElementById\\(.+\\)\\..+");
    }

    private String parseCallFromGetElementById(String code) {
        return getElementByIdParser.parseCallFromGetElementById(code);
    }
    
    private boolean isDeclaration(String code) {
        return code.matches("(const|var|let)[\\s\\t]+[A-z0-9_$]+.+");
    }

    private void parseDeclarations() {
        declarationParser.parseDeclarations(stateDeclarations);
    }

    private boolean hasMobilangDirective(String code) {
        return code.contains("mobilang:");
    }

    private String parseMobilangDirective(String code) {
        return directiveParser.parse(code);
    }

    private void parseTagAttributes() {
        Stack<Tag> tagsToParse = new Stack<>();

        tagsToParse.push(structure);

        while (!tagsToParse.empty()) {
            Tag currentTag = tagsToParse.pop();

            parseAttributesOf(currentTag);

            for (Tag child : currentTag.getChildren()) {
                tagsToParse.push(child);
            }
        }
    }

    private void parseAttributesOf(Tag tag) {
        for (Map.Entry<String, String> entry : tag.getAttributes().entrySet()) {
            String parsedValue = directiveParser.parse(entry.getValue());

            tag.addAttribute(entry.getKey(), parsedValue);
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<Variable> getStateDeclarations() {
        return stateDeclarations;
    }

    public List<String> getStateBody() {
        return stateBody;
    }
}
