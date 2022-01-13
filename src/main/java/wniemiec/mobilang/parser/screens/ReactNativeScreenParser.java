package wniemiec.mobilang.parser.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
        System.out.println(line.toCode());
    }
}
