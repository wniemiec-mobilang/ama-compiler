package wniemiec.mobilang.asc.parser.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wniemiec.mobilang.asc.parser.html.HtmlParser;
import wniemiec.mobilang.asc.parser.screens.behavior.AssignmentExpression;
import wniemiec.mobilang.asc.parser.screens.behavior.Behavior;
import wniemiec.mobilang.asc.parser.screens.behavior.Instruction;
import wniemiec.mobilang.asc.parser.screens.structure.HtmlUtils;
import wniemiec.mobilang.asc.parser.screens.structure.ReactNativeStructureParser;
import wniemiec.mobilang.asc.parser.screens.structure.Tag;
import wniemiec.mobilang.asc.parser.screens.style.StyleSheet;

public class ReactNativeScreenParser {

    Tag structure;
    StyleSheet style;
    Behavior behavior;

    Set<String> imports;
    Set<String> styledTagDeclarations;
    Set<String> stateDeclarations;
    Queue<String> stateBody;
    Map<String, String> symbolTable; // key: var id; value: tag id

    public ReactNativeScreenParser(Tag structure, StyleSheet style, Behavior behavior) {
        this.structure = structure;
        this.style = style;
        this.behavior = behavior;
        imports = new HashSet<>();
        styledTagDeclarations = new HashSet<>();
        stateDeclarations = new HashSet<>();
        stateBody = new LinkedList<>();
        symbolTable = new HashMap<>();
    }

    public void parse() {
        System.out.println("-----< React Native screen parser >-----");
        
        //structure.print();
        //System.out.println(style);
        
        //System.out.println("\n\n");
        //stylize(structure, style);
        //structure.print();
        //behavior.print();
        //System.out.println("\n\n");
        parseBehavior();
        System.out.println("\nState declarations: " + stateDeclarations);
        System.out.println("State body: " + stateBody);
        System.out.println("----------");
    }

    private void stylize(Tag tag, StyleSheet style) {
        Stack<Tag> tagsToParse = new Stack<>();

        tagsToParse.add(tag);

        while (!tagsToParse.empty()) {
            Tag currentTag = tagsToParse.pop();
            List<String> selectors = new ArrayList<>();

            if (currentTag.hasAttribute("id")) {
                selectors.add("#" + currentTag.getAttribute("id"));

                Tag parent = currentTag.getFather();
                String lastFatherSelector = "";
                while (parent != null) {
                    if (parent.hasAttribute("id")) {
                        selectors.add("#" + parent.getAttribute("id") + " " + lastFatherSelector + currentTag.getName());
                        lastFatherSelector = "#" + parent.getAttribute("id") + " " + lastFatherSelector;
                    }

                    if (parent.hasAttribute("class")) {
                        selectors.add("." + parent.getAttribute("class") + " " + lastFatherSelector + currentTag.getName());
                        lastFatherSelector = "." + parent.getAttribute("class") + " " + lastFatherSelector;
                    }

                    parent = parent.getFather();
                }
            }

            if (currentTag.hasAttribute("class")) { 
                selectors.add("." + currentTag.getAttribute("class"));

                Tag parent = currentTag.getFather();
                String lastFatherSelector = "";
                while (parent != null) {
                    if (parent.hasAttribute("id")) {
                        selectors.add("#" + parent.getAttribute("id") + " " + lastFatherSelector + currentTag.getName());
                        lastFatherSelector = "#" + parent.getAttribute("id") + " " + lastFatherSelector;
                    }
                    
                    if (parent.hasAttribute("class")) {
                        selectors.add("." + parent.getAttribute("class") + " " + lastFatherSelector + currentTag.getName());
                        lastFatherSelector = "." + parent.getAttribute("class") + " " + lastFatherSelector;
                    }

                    parent = parent.getFather();
                }
            }

            selectors.add(currentTag.getName());

            Tag parent = currentTag.getFather();
            String lastFatherSelector = "";
            while (parent != null) {
                selectors.add(parent.getName() + " " + lastFatherSelector + currentTag.getName());
                lastFatherSelector = parent.getName() + " " + lastFatherSelector;
            
                parent = parent.getFather();
            }

            if (currentTag.getName().equals("body")) {
                selectors.add("*");
            }

            // TODO: Compatibility with id and class mixed
            // Ex: #glossary-content .item .item-content p
            //System.out.println(currentTag);
            //System.out.println(selectors);

            Map<String, String> rules = style.getRulesForSelector(selectors);
            currentTag.setStyle(rules);

            for (Tag child : currentTag.getChildren()) {
                tagsToParse.add(child);
            }
        }
    }

    private void parseBehavior() {
        for (Instruction line : behavior.getCode()) {
            parseBehaviorLine(line);
            //break;
        }

        // createCode()
    }

    private void parseBehaviorLine(Instruction line) {
        // TODO: Compatibility with getElementsByClass or byQuery
        System.out.println(line.toCode());
        String code = line.toCode();
        //String rnCode = code;

        
        if (code.contains("window.location.href")) {
            code = code.replace("window.location.href", "props.route.params.query");
        }
        else if (code.contains("innerHTML")) { 
            // <id> = <attribution>
            //   1. if <id> is in symbolTable
            //   2. then replace <id> by its content obtained from symbolTable
            //   3. create '<id>' as list if it has not been created
            //   4. create local var '_<id>' as list if it has not been created
            //   5. '_<id>'.push(<attribution>) 
            //   6. set<id>(_<id>)

            String id = code.split(".innerHTML")[0];
            String tagId = "";

            if (symbolTable.containsKey(id)) {
                tagId = symbolTable.get(id);
            }
            else {
                tagId = extractIdFromGetElementById(code);
            }

            tagId = tagId.replaceAll("-", "_");

            if (!stateDeclarations.contains(tagId)) {
                stateDeclarations.add(tagId);
            }

            String innerHtmlAssignment = code.substring(code.indexOf("=")+1);
            innerHtmlAssignment = parseInnerHtml(innerHtmlAssignment); // (convert html to rn tags) }--> CONTENT CANNOT BE STRING OR TEMPLATE; IT MUST BE TAG!

            stateBody.add("let _" + tagId + "=" + tagId);

            if (code.contains(".innerHTML=")) { 
                stateBody.add("_" + tagId + "=[" + innerHtmlAssignment + "]");
            }
            else if (code.contains(".innerHTML+=")) { 
                stateBody.add("_" + tagId + ".push(" + innerHtmlAssignment + ")");
            }

            stateBody.add("set" + tagId + "(_" + tagId + ")");
        }

        if (code.matches("(const|var|let)[\\s\\t]+[A-z0-9_$]+.+document\\.getElementById\\(.+\\)")) { // const glossary=document.getElementById("glossary")
            Pattern p = Pattern.compile("(getElementById\\(\\\"(.+)\\\"\\))");
            Matcher m = p.matcher(code);

            if (m.matches()) {
                String id = m.group(2);
                code = code.replace("document\\.getElementById\\(.+\\)", id); // const glossary=mobilang:tag:id:glossary
            }
        }
        else if (code.matches("document\\.getElementById\\(.+\\)\\.")) { //document.getElementById("back-btn").onclick=() =>
            String tagId = extractIdFromGetElementById(code);
            String tagProperty = code.substring(code.indexOf(").")+1);
            String tagPropertyName = tagProperty.split("=")[0];
            String tagPropertyCode = tagProperty.split("=")[1];

            // 1. find tag with id == tagId
            // 2. tag.addProperty(tagProperty)
            Tag refTag = structure.getTagWithId(tagId);
            refTag.addAttribute(tagPropertyName, tagPropertyCode);
            
            code = ""; // Skips this line
        }

        if (code.matches("(const|var|let)[\\s\\t]+[A-z0-9_$]+.+")) {
            String id = code.split(" ")[1].split("=")[0];
            symbolTable.put(id, code);
        }

        if (!code.isEmpty()) {
            stateBody.add(code);
        }
    }

    // converts literal string or template string to string with rn tags
    private String parseInnerHtml(String innerHtmlAssignment) {
        if (innerHtmlAssignment.matches("\"[\\s\\t]*\"")) {
            return innerHtmlAssignment;
        }

        String rawHtml = innerHtmlAssignment.replaceAll("`", "");
        System.out.println("# "  + rawHtml);
                
        Tag root = HtmlUtils.parse(rawHtml);
        root.print();

        System.out.println(root.toCode());
        // TODO: mandar para o behavior parse 'root'
        //return "`" + HtmlUtils.stringify(root) + "`";
        
        
        
        //ReactNativeStructureParser parser = new ReactNativeStructureParser(root);
        //Tag rnRoot = parser.parse();

        // TODO: call RnStructureParser(rawHtml)


        //return HtmlUtils.stringify(rnRoot);
        //return "";
        return "`" + rawHtml + "`";
    }

    private String extractIdFromGetElementById(String line) {
        String id = "";
        Pattern p = Pattern.compile("(getElementById\\(\\\"(.+)\\\"\\))");
        Matcher m = p.matcher(line);

        if (m.matches()) {
            id = m.group(2);
        }

        return id;
    }
}
