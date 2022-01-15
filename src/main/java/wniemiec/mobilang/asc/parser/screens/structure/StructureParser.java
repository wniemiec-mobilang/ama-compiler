package wniemiec.mobilang.asc.parser.screens.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Stack;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilang.asc.data.Node;
import wniemiec.mobilang.asc.parser.Parser;

// Strategy: DFS
public class StructureParser /*implements Parser*/ {

    private String contentNode;
    private boolean astFromMobilang;

    public StructureParser(SortedMap<String, List<Node>> tree, Node structureNode) {
        contentNode = tree.get(structureNode.getId()).get(0).getLabel();
        astFromMobilang = true;
    }

    public StructureParser(String ast) {
        this.contentNode = ast;
        astFromMobilang = false;
    }

    //@Override
    public Tag parse() throws Exception {
        //System.out.println("-----< STRUCTURE PARSER >-----");
        //System.out.println(contentNode);
        //System.out.println("-------------------------------\n");
        if (astFromMobilang) {
            return parseJson(contentNode);    
        }

        JSONObject obj = new JSONObject(contentNode);
        System.out.println(contentNode);
        //System.out.println(parseRootRawTag(obj));
        return parseRootRawTag(obj);
    }

    private Tag parseJson(String json) throws Exception {
        JSONObject obj = new JSONObject(json);
        //JSONObject root = obj.getJSONObject("content");
        //JSONArray child = root.getJSONArray("children");
        
        JSONObject htmlTagContent = obj
            .getJSONObject("content")
            .getJSONArray("children")
            .getJSONObject(0)
            .getJSONObject("content");

        if (!htmlTagContent.getString("name").equals("html"))
            throw new Exception("HTML tag not found");

        JSONObject bodyTag = htmlTagContent
            .getJSONArray("children")
            .getJSONObject(0);

        JSONObject bodyTagContent = bodyTag.getJSONObject("content");

        if (!bodyTagContent.getString("name").equals("body"))
            throw new Exception("BODY tag not found");

        Tag rootTag = parseRootRawTag(bodyTag);
        return rootTag;
        //System.out.println("Parse completed!");
        //rootTag.print();
        /*System.out.println("Converting to react native tags");
        
        ReactNativeStructureParser rnParser = new ReactNativeStructureParser(rootTag);
        Tag rnRootTag = rnParser.parse();

        System.out.println("Completed!");
        rnRootTag.print();*/

        //return rnRootTag;
    }

    private Tag parseRootRawTag(JSONObject rootRawTag) {
        Stack<RawTag> tagsToParse = new Stack<>();

        tagsToParse.push(new RawTag(rootRawTag, null));
        Tag bodyTag = null;

        while (!tagsToParse.empty()) {
            RawTag currenRawTag = tagsToParse.pop();
            JSONObject currentTag = currenRawTag.getJsonObject();
            String nodeType = currentTag.getString("nodeType");

            if (nodeType.equals("text")) {
                String tagContent = currentTag
                    .getJSONObject("content")
                    .getJSONObject("value")
                    .getString("content");
                
                currenRawTag.getParent().setValue(tagContent);
            }
            else {
                JSONObject tagContent = currentTag.getJSONObject("content");
                String tagName = parseTagName(tagContent);
                Map<String, String> tagAttributes = parseTagAttributes(tagContent);

                Tag parsedTag = new Tag(tagName, tagAttributes);
                parseTagChildren(tagsToParse, parsedTag, tagContent);

                if (currenRawTag.getParent() != null) {
                    currenRawTag.getParent().addChild(parsedTag);
                }
                
                //if (parsedTag.getName().equals("body")) { // Gets root tag
                if (bodyTag == null) {
                    bodyTag = parsedTag;
                }
            }
        }

        return bodyTag;
    }

    private void parseTagChildren(Stack<RawTag> tagsToParse, Tag parent, JSONObject tagContent) {
        if (!tagContent.has("children"))
            return;

        JSONArray tagChildren = tagContent.getJSONArray("children");

        for (int i = tagChildren.length()-1; i >= 0; i--) {
            //Tag childTag = new Tag(tagChildren.getJSONObject(i));
            //parent.addChild(childTag);
            tagsToParse.push(new RawTag(tagChildren.getJSONObject(i), parent));
        }
    }

    private String parseTagName(JSONObject tagContent) {
        String tagName = "";
        
        if (tagContent.has("name"))
            tagName = tagContent.getString("name");
        return tagName;
    }

    private Map<String, String> parseTagAttributes(JSONObject tag) {
        if (!tag.has("attributes"))
            return new HashMap<>();

        Map<String, String> attributes = new HashMap<>();

        JSONArray jsonAttributes = tag.getJSONArray("attributes");

        for (int i = 0; i < jsonAttributes.length(); i++) {
            JSONObject attributeObject = jsonAttributes.getJSONObject(i);
            String key = attributeObject.getJSONObject("key").getString("content");
            String value = attributeObject.getJSONObject("value").getString("content");
            
            attributes.put(key, value);
        }

        return attributes;
    }
}

