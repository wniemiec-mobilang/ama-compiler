package wniemiec.mobilang.asc.framework.reactnative;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import wniemiec.mobilang.asc.framework.FrameworkScreenParser;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.Variable;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.html.HtmlParser;
import wniemiec.mobilang.asc.parser.screens.behavior.AssignmentExpression;
import wniemiec.mobilang.asc.parser.screens.behavior.Behavior;
import wniemiec.mobilang.asc.parser.screens.behavior.Instruction;
import wniemiec.mobilang.asc.parser.screens.structure.HtmlUtils;
import wniemiec.mobilang.asc.parser.screens.structure.ReactNativeStructureParser;
import wniemiec.data.java.Encryptors;
import wniemiec.data.java.Encryptor;

public class ReactNativeFrameworkScreenParser extends FrameworkScreenParser {

    private List<String> imports;
    private List<Variable> stateDeclarations;
    private List<String> stateBody;
    private List<Variable> declarations;
    private List<String> body;
    private List<String> declaredStateBodyVariables;
    private Map<String, String> symbolTable; // key: var id; value: tag id

    public ReactNativeFrameworkScreenParser(String name, Tag structure, Style style, Behavior behavior) {
        super(name, structure, style, behavior);
    
        imports = new ArrayList<>();
        stateDeclarations = new ArrayList<>();
        stateBody = new ArrayList<>();
        declarations = new ArrayList<>();
        body = new ArrayList<>();
        symbolTable = new HashMap<>();
        declaredStateBodyVariables = new ArrayList<>();
    }

    @Override
    public void parse() throws IOException, ParseException {
        //System.out.println("-----< React Native screen parser >-----");
        
        stylize(structure, style);
        parseStructure(structure);

        parseBehavior();
        parseImports();

        declarations = generateDeclarations(structure);
        body = structure.toCode();
        //System.out.println("----------");
    }

    private void parseStructure(Tag root) {
        // Converts html tags to React native tags
        ReactNativeStructureParser rnStructureParser = new ReactNativeStructureParser(root);
        structure = rnStructureParser.parse();
    }

    private void parseImports() {
        imports.add("import React, { useEffect, useState } from 'react'");
        imports.add("import styled from 'styled-components/native'");
    }

    private List<Variable> generateDeclarations(Tag root) {
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

    private void stylize(Tag tag, Style style) {
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

    private void parseBehavior() throws IOException, ParseException {
        for (Instruction instr : behavior.getCode()) {
            for (String line : instr.toCode().split("\n")) {
                parseBehaviorLine(line);
            }
            //break;
        }

        for (Variable declaration : stateDeclarations) {
            stateBody.add("set" + declaration.getId() + "(_" + declaration.getId() + ")");
        }
        // createCode()
    }

    private void parseBehaviorLine(String code) throws IOException, ParseException {
        // TODO: Compatibility with getElementsByClass or byQuery
        //System.out.println(line.toCode());
        //String code = line.toCode();
        //System.out.println(code);
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
            //System.out.println("# " + code);
            //System.out.println("# " + id);

            //if (stateDeclarations.contains(id)) {
            //if (stateDeclarations.contains(new Variable(id, "state", "[]"))) {
            if (symbolTable.containsKey(id)) {
                //tagId = symbolTable.get(id);
                tagId = symbolTable.get(id);
            }
            else {
                //System.out.println("1 - " + code);
                tagId = extractIdFromGetElementById(code);
                //tagId = tagId.replaceAll("-", "_");
            }

            //tagId = tagId.replaceAll("-", "_");
            String normalizedId = id.replaceAll("-", "_");
            Variable stateVar = new Variable(normalizedId, "state", "[]");

            if (!stateDeclarations.contains(stateVar)) {
                stateDeclarations.add(stateVar);
            }

            String innerHtmlAssignment = code.substring(code.indexOf("=")+1);
            innerHtmlAssignment = parseInnerHtml(innerHtmlAssignment); // (convert html to rn tags) }--> CONTENT CANNOT BE STRING OR TEMPLATE; IT MUST BE TAG!
            

            if (!declaredStateBodyVariables.contains("let _" + normalizedId)) {
                Tag refTag = structure.getTagWithId(tagId);

                stateBody.add("let _" + normalizedId + "=[" + refTag.toChildrenCode() + "]");
                declaredStateBodyVariables.add("let _" + normalizedId);
            }

            if (code.contains(".innerHTML=")) { 
                stateBody.add("_" + normalizedId + "=[" + innerHtmlAssignment + "]");
            }
            else if (code.contains(".innerHTML+=")) { 
                stateBody.add("_" + normalizedId + ".push(" + innerHtmlAssignment + ")");
            }

            //stateBody.add("set" + tagId + "(_" + tagId + ")");
            code = "";
        }

        if (code.matches("(const|var|let)[\\s\\t]+[A-z0-9_$]+.+document\\.getElementById\\(.+\\)")) { // const glossary=document.getElementById("glossary")
            Pattern p = Pattern.compile(".*(getElementById\\(\\\"(.+)\\\"\\)).*");
            Matcher m = p.matcher(code);

            if (m.matches()) {
                String id = m.group(2);
                //code = code.replaceAll("document\\.getElementById\\(.+\\)", id); // const glossary=mobilang:tag:id:glossary
                String normalizedId = id.replaceAll("-", "_");
                
                Variable stateVar = new Variable(normalizedId, "state", "[]");

                if (!stateDeclarations.contains(stateVar)) {
                    stateDeclarations.add(stateVar);
                    
                    String varName = code.split(" ")[1].split("=")[0];
                    symbolTable.put(varName, id);
                }
                //stateDeclarations.add(id, "[]");
                code = "";
            }
        }
        else if (code.matches("document\\.getElementById\\(.+\\)\\..+")) { //document.getElementById("back-btn").onclick=() =>
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
    private String parseInnerHtml(String innerHtmlAssignment) throws IOException, ParseException {
        if (innerHtmlAssignment.matches("\"[\\s\\t]*\"")) {
            return "";
        }

        String rawHtml = innerHtmlAssignment.replaceAll("`", "");
        //System.out.println("# "  + rawHtml);
                
        Tag root = HtmlUtils.parse(rawHtml);
        stylize(root, style);
        
        // TODO: mandar para o behavior parse 'root' (pd ter behavior para alguma tag)
        // TODO: tem q verificar se tem style para alguma tag do 'root'
        // TODO: converter para tags React Native
        ReactNativeStructureParser rnStructureParser = new ReactNativeStructureParser(root);
        Tag rnTag = rnStructureParser.parse();

        StringBuilder sb = new StringBuilder();

        for (String line : rnTag.toCode()) {
            sb.append(line);
        }

        return sb.toString();
        
        
        
        //ReactNativeStructureParser parser = new ReactNativeStructureParser(root);
        //Tag rnRoot = parser.parse();

        // TODO: call RnStructureParser(rawHtml)


        //return HtmlUtils.stringify(rnRoot);
        //return "";
        //return "`" + rawHtml + "`";
    }

    private String extractIdFromGetElementById(String line) {
        String id = "";
        Pattern p = Pattern.compile(".*(getElementById\\(\\\"(.+)\\\"\\)).*");
        Matcher m = p.matcher(line);

        if (m.matches()) {
            id = m.group(2);
        }

        return id;
    }

    public List<String> getDeclaredStateBodyVariables() {
        return declaredStateBodyVariables;
    }

    public Map<String, String> getSymbolTable() {
        return symbolTable;
    }

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
