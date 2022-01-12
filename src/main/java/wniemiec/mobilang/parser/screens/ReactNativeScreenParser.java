package wniemiec.mobilang.parser.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import wniemiec.mobilang.parser.screens.behavior.Behavior;
import wniemiec.mobilang.parser.screens.structure.Tag;
import wniemiec.mobilang.parser.screens.style.StyleSheet;

public class ReactNativeScreenParser {

    Tag structure;
    StyleSheet style;
    Behavior behavior;

    public ReactNativeScreenParser(Tag structure, StyleSheet style, Behavior behavior) {
        this.structure = structure;
        this.style = style;
        this.behavior = behavior;
    }

    public void parse() {
        System.out.println("-----< React Native screen parser >-----");
        
        structure.print();
        System.out.println(style);
        //behavior.print();
        
        System.out.println("\n\n");
        stylize(structure, style);

        structure.print();
        System.out.println("----------");
    }

    public void stylize(Tag tag, StyleSheet style) {
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
            System.out.println(currentTag);
            System.out.println(selectors);

            Map<String, String> rules = style.getRulesForSelector(selectors);
            currentTag.setStyle(rules);

            for (Tag child : currentTag.getChildren()) {
                tagsToParse.add(child);
            }
        }
    }

    /*
    private StyledTag stylize(Tag tag, StyleSheet style) {
        StyledTag styledTag = new StyledTag(tag);



        return styledTag;
    }*/

}
