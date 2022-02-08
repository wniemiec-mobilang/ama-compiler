package wniemiec.mobilang.asc.framework.parser.reactnative;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.asc.framework.parser.FrameworkScreenParser;
import wniemiec.mobilang.asc.framework.parser.reactnative.structure.ReactNativeStructureParser;
import wniemiec.mobilang.asc.models.Behavior;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.Variable;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing screen of React Native framework.
 */
class ReactNativeFrameworkScreenParser extends FrameworkScreenParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> imports;
    private List<Variable> declarations;
    private List<String> body;
    private List<Variable> stateDeclarations;
    private List<String> stateBody;
    private ReactNativeStyleApplicator styleApplicator;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFrameworkScreenParser(
        String name, 
        Tag structure, 
        Style style, 
        Behavior behavior
    ) {
        super(name, structure, style, behavior);
    
        stateDeclarations = new ArrayList<>();
        stateBody = new ArrayList<>();
        imports = new ArrayList<>();
        declarations = new ArrayList<>();
        body = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void parse() throws IOException, ParseException {
        applyStyleInStructure();
        parseStructure();
        parseBehavior();
        setUpImports();
        setUpDeclarations();
        setUpBody();
    }

    private void setUpBody() {
        body = structure.toCode();
    }

    private void setUpDeclarations() {
        ReactNativeDeclarationGenerator declarationGenerator = new ReactNativeDeclarationGenerator();

        declarations = declarationGenerator.generate(structure);
    }

    private void applyStyleInStructure() {
        styleApplicator = new ReactNativeStyleApplicator(style);
        styleApplicator.apply(structure);
    }

    private void parseStructure() throws ParseException {
        ReactNativeStructureParser rnStructureParser = new ReactNativeStructureParser(structure);
        
        structure = rnStructureParser.parse();
    }   
 
    private void parseBehavior() throws IOException, ParseException {
        ReactNativeBehaviorParser behaviorParser = new ReactNativeBehaviorParser(
            behavior.getCode(), 
            structure, 
            styleApplicator
        );
        
        behaviorParser.parse();
        
        stateDeclarations = behaviorParser.getStateDeclarations();
        stateBody = behaviorParser.getStateBody();
    }

    private void setUpImports() {
        ReactNativeImportGenerator importGenerator = new ReactNativeImportGenerator();

        imports = importGenerator.generate();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    @Override
    public ScreenData getScreenData() {
        return new ScreenData(
            name, 
            imports, 
            stateDeclarations, 
            stateBody, 
            declarations, 
            body
        );
    }
}
