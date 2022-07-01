package wniemiec.mobilex.ama.models.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Responsible for generating code from a tag.
 */
class TagCoder {
    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<String> toCode(Tag tag) {
        List<String> code = new ArrayList<>();

        if (tag.isVoidTag()) {
            code.add(buildVoidTag(tag));
        }
        else {
            code.add(buildTagOpen(tag));

            if (tag.getValue() != null) {
                code.add(tag.getValue());
            }
            else {
                for (Tag child : tag.getChildren()) {
                    code.addAll(toCode(child));
                }
            }

            code.add(buildTagClose(tag));
        }

        return code;
    }

    private String buildVoidTag(Tag tag) {
        return buildTagOpen(tag).replace(">", "/>");
    }

    private String buildTagOpen(Tag tag) {
        StringBuilder code = new StringBuilder();

        code.append('<');
        code.append(tag.getName());

        if (!tag.getAttributes().isEmpty()) {
            code.append(' ');
            code.append(stringifyAttributes(tag));
        }

        code.append('>');

        return code.toString();
    }

    private String stringifyAttributes(Tag tag) {
        StringBuilder code = new StringBuilder();

        for (Map.Entry<String, String> attribute : tag.getAttributes().entrySet()) {
            code.append(attribute.getKey());
            code.append('=');

            if (attribute.getValue().startsWith("{")) {
                code.append(attribute.getValue());
            }
            else {
                code.append('\"');
                code.append(attribute.getValue());
                code.append('\"');
            }

            code.append(' ');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private String buildTagClose(Tag tag) {
        StringBuilder code = new StringBuilder();

        code.append("</");
        code.append(tag.getName());
        code.append('>');

        return code.toString();
    }
}
