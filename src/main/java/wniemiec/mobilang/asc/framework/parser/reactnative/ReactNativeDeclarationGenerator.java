package wniemiec.mobilang.asc.framework.parser.reactnative;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.Variable;
import wniemiec.data.java.Encryptors;
import wniemiec.data.java.Encryptor;

public class ReactNativeDeclarationGenerator {
    public List<Variable> generate(Tag root) {
        List<Variable> declarations = new ArrayList<>();

        Stack<Tag> tagsToParse = new Stack<>();

        tagsToParse.add(root);

        while (!tagsToParse.empty()) {
            Tag currentTag = tagsToParse.pop();
            //String tagName = currentTag.getName();
            String currentTagStyledComponent = generateStyledComponentFor(currentTag);

            // Sets unique name for current tag
            Encryptor md5Encryptor = Encryptors.md5();
            String uniqueName = md5Encryptor.encrypt(String.valueOf(new Date().getTime() + Math.round(Math.random() * 9999)));
            currentTag.setName(uniqueName);

            // Add it to declarations as styled component
            Variable tagDeclaration = new Variable(
                currentTag.getName(), 
                "const", 
                currentTagStyledComponent
            );
            declarations.add(tagDeclaration);

            for (Tag child : currentTag.getChildren()) {
                tagsToParse.add(child);
            }
        }

        return declarations;
    }

    private String generateStyledComponentFor(Tag tag) {
        StringBuilder sb = new StringBuilder();

        sb.append("styled.");
        sb.append(tag.getName());
        sb.append('`');
        sb.append('\n');
        
        for (Map.Entry<String, String> tagStyle : tag.getStyle().entrySet()) {
            sb.append(tagStyle.getKey());
            sb.append(": ");
            sb.append(tagStyle.getValue());
            sb.append(';');
            sb.append('\n');
        }
        
        sb.append("`");
        
        return sb.toString();
    }
}
