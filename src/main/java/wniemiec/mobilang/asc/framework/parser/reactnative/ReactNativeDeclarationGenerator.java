package wniemiec.mobilang.asc.framework.parser.reactnative;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import wniemiec.mobilang.asc.models.behavior.Variable;
import wniemiec.mobilang.asc.models.tag.Tag;
import wniemiec.data.java.Encryptors;
import wniemiec.data.java.Encryptor;


/**
 * Responsible for generation tag declarations.
 */
class ReactNativeDeclarationGenerator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Stack<Tag> tagsToParse;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Tag declaration generator.
     */
    public ReactNativeDeclarationGenerator() {
        tagsToParse = new Stack<>();
    }

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Generates tag declarations for a tag and its children.
     * 
     * @param       root tag
     * 
     * @return      Declarations
     */
    public List<Variable> generate(Tag tag) {
        List<Variable> declarations = new ArrayList<>();

        tagsToParse.add(tag);

        while (!tagsToParse.empty()) {
            Tag currentTag = tagsToParse.pop();

            declarations.add(generateTagDeclaration(currentTag));

            for (Tag child : currentTag.getChildren()) {
                tagsToParse.add(child);
            }
        }

        return declarations;
    }

    private Variable generateTagDeclaration(Tag tag) {
        String currentTagStyledComponent = generateStyledComponentFor(tag);
        String uniqueName = generateIdentifier();

        tag.setName(uniqueName);

        return new Variable(
            uniqueName, 
            "const", 
            currentTagStyledComponent
        );
    }

    private String generateStyledComponentFor(Tag tag) {
        StringBuilder code = new StringBuilder();

        code.append(buildStyledComponentDeclaration(tag));
        code.append(buildStyledComponentBody(tag));
        
        return code.toString();
    }

    private String buildStyledComponentDeclaration(Tag tag) {
        StringBuilder code = new StringBuilder();

        code.append("styled.");
        code.append(tag.getName());

        return code.toString();
    }

    private String buildStyledComponentBody(Tag tag) {
        StringBuilder code = new StringBuilder();

        code.append('`');
        code.append('\n');
        code.append(buildStyledComponentBodyRules(tag));
        code.append("`");

        return code.toString();
    }

    private String buildStyledComponentBodyRules(Tag tag) {
        StringBuilder code = new StringBuilder();

        for (Map.Entry<String, String> tagStyle : tag.getStyle().entrySet()) {
            code.append(buildStyledComponentBodyRule(tagStyle));
        }

        return code.toString();
    }

    private String buildStyledComponentBodyRule(Map.Entry<String, String> tagStyle) {
        StringBuilder code = new StringBuilder();

        code.append(tagStyle.getKey());
        code.append(": ");
        code.append(tagStyle.getValue());
        code.append(';');
        code.append('\n');

        return code.toString();
    }

    private String generateIdentifier() {
        Encryptor md5Encryptor = Encryptors.md5();
        Long currentTime = new Date().getTime();
        Long randomNumber = Math.round(Math.random() * 9999);
        
        return md5Encryptor.encrypt(String.valueOf(currentTime + randomNumber));
    }
}
