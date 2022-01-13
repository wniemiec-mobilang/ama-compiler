package wniemiec.mobilang.parser.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wniemiec.mobilang.parser.screens.behavior.AssignmentExpression;
import wniemiec.mobilang.parser.screens.behavior.Behavior;
import wniemiec.mobilang.parser.screens.behavior.Instruction;
import wniemiec.mobilang.parser.screens.structure.Tag;
import wniemiec.mobilang.parser.screens.style.StyleSheet;

public class ReactNativeScreenParser {

    Tag structure;
    StyleSheet style;
    Behavior behavior;

    List<String> imports;
    List<String> styledTagDeclarations;
    List<String> stateDeclarations;
    Map<String, String> stateBody;
    Map<String, String> symbolTable;

    public ReactNativeScreenParser(Tag structure, StyleSheet style, Behavior behavior) {
        this.structure = structure;
        this.style = style;
        this.behavior = behavior;
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
            //   3. create '_<id>' as string
            //   4. setState: _<id> += <attribution>
        }

        if (code.matches("(const|var|let)[\\s\\t]+[A-z0-9_$]+.+document\\.getElementById\\(.+\\)")) { // const glossary=document.getElementById("glossary")
            Pattern p = Pattern.compile("(getElementById\\(\\\"(.+)\\\"\\))");
            Matcher m = p.matcher(code);

            if (m.matches()) {
                String id = m.group(2);
                code = code.replace("document\\.getElementById\\(.+\\)", "mobilang:tag:id:" + id); // const glossary=mobilang:tag:id:glossary
            }
        }
        else if (code.matches("document\\.getElementById\\(.+\\)\\.")) { //document.getElementById("back-btn").onclick=() =>
            String tagId = extractIdFromGetElementById(code);
            String tagProperty = code.substring(code.indexOf(")."));

            // 1. find tag with id == tagId
            // 2. tag.addProperty(tagProperty)
            
        }

        if (code.matches("(const|var|let)[\\s\\t]+[A-z0-9_$]+.+")) {
            String id = code.split(" ")[1].split("=")[0];
            symbolTable.put(id, code);
        }
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
