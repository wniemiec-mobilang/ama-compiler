package wniemiec.mobilang.asc.framework.reactnative;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wniemiec.mobilang.asc.models.CodeFile;


/**
 * Responsible for generating React Native framework code for core.
 */
class ReactNativeCoreCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<CodeFile> coreCodes;
    private final Set<String> dependencies;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeCoreCoder() {
        coreCodes = new ArrayList<>();
        dependencies = new HashSet<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<CodeFile> generateCode() {
        generateIndexCode();
        generateAppCode();

        return coreCodes;
    }

    private void generateIndexCode() {
        List<String> code = new ArrayList<>();

        buildIndexImports(code);
        buildIndexAppRegistry(code);

        coreCodes.add(new CodeFile("index.js", code));
    }

    private void buildIndexImports(List<String> code) {
        code.add("import {AppRegistry} from 'react-native';");
        code.add("import App from './src/App';");
        code.add("import {name as appName} from './app.json';");
        code.add("");
    }

    private void buildIndexAppRegistry(List<String> code) {
        code.add("AppRegistry.registerComponent(appName, () => App);");
    }

    private void generateAppCode() {
        List<String> code = new ArrayList<>();

        buildAppImports(code);
        buildAppExport(code);

        coreCodes.add(new CodeFile("src/App.js", code));
    }

    private void buildAppImports(List<String> code) {
        code.add("import React, { useState } from 'react';");
        code.add("import { Platform, ScrollView, useWindowDimensions } from 'react-native';");
        code.add("import { WebView } from 'react-native-webview';");
        code.add("import IframeRenderer, {iframeModel} from '@native-html/iframe-plugin';");
        code.add("import RenderHTML from 'react-native-render-html';");
        code.add("");

        dependencies.add("react-native-webview@11.17.2");
        dependencies.add("react-native-render-html");
        dependencies.add("@native-html/iframe-plugin");
    }

    private void buildAppExport(List<String> code) {
        code.add("const App = () => {");
        code.add("");
        code.add("  const [content, setContent] = useState(Platform.OS === 'ios' ? './assets/home.html' : 'file:///android_asset/home.html');");
        code.add("");
        code.add("  const renderers = {");
        code.add("    iframe: IframeRenderer,");
        code.add("  };");
        code.add("");
        code.add("  const customHTMLElementModels = {");
        code.add("    iframe: iframeModel,");
        code.add("  };");
        code.add("");
        code.add("  const {width, height} = useWindowDimensions();");
        code.add("");
        code.add("  const html = `");
        code.add("    <iframe allowfullscreen style=\"width:${width}px; height: ${height}px\" src='${content}'></iframe>");
        code.add("  `;");
        code.add("");
        code.add("  const renderProps = {");
        code.add("    a: {");
        code.add("      onPress: (_, href) => {");
        code.add("        setContent('');");
        code.add("        setContent(href);");
        code.add("      }");
        code.add("    },");
        code.add("    iframe: {");
        code.add("      scalesPageToFit: true,");
        code.add("      webViewProps: webViewProps,");
        code.add("    }");
        code.add("  };");
        code.add("");
        code.add("  const webViewProps = {");
        code.add("    originWhitelist: '*',");
        code.add("    javaScriptCanOpenWindowsAutomatically: true,");
        code.add("    allowFileAccessFromFileURLs: true,");
        code.add("    allowFileAccess: true,");
        code.add("    allowUniversalAccessFromFileURLs: true,");
        code.add("    allowingReadAccessToURL: true,");
        code.add("  };");
        code.add("");
        code.add("  return (");
        code.add("      <ScrollView>");
        code.add("        <RenderHTML");
        code.add("          contentWidth={height * 2}");
        code.add("          renderers={renderers}");
        code.add("          customHTMLElementModels={customHTMLElementModels}");
        code.add("          source={{html: html}}");
        code.add("          WebView={WebView}");
        code.add("          defaultWebViewProps={webViewProps}");
        code.add("          renderersProps={renderProps}");
        code.add("        />");
        code.add("      </ScrollView>");
        code.add("  );");
        code.add("}");
        code.add("");
        code.add("export default App;");
        code.add("");
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Set<String> getDependencies() {
        return dependencies;
    }
}
