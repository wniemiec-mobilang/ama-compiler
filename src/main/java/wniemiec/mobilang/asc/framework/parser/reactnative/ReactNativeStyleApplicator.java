package wniemiec.mobilang.asc.framework.parser.reactnative;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;


class ReactNativeStyleApplicator {

    private Style style;

    public ReactNativeStyleApplicator(Style style) {
        this.style = style;
    }
    
    public void apply(Tag tag) {
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
}
