package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import wniemiec.mobilang.asc.framework.coder.FrameworkCoreCoder;
import wniemiec.mobilang.asc.models.FileCode;


/**
 * Responsible for generating React Native framework code for core.
 */
public class ReactNativeFrameworkCoreCoder extends FrameworkCoreCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<FileCode> coreCodes;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFrameworkCoreCoder(Collection<String> screensName) {
        super(screensName);
        coreCodes = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public List<FileCode> generateCode() {
        generateIndexCode();
        generateAppCode();
        generateNavigators();

        return coreCodes;
    }

    private void generateIndexCode() {
        List<String> code = new ArrayList<>();

        buildIndexImports(code);
        buildIndexAppRegistry(code);

        coreCodes.add(new FileCode("index.js", code));
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
        buildAppStorage(code);
        buildAppNavigation(code);

        coreCodes.add(new FileCode("src/App.js", code));
    }

    private void buildAppImports(List<String> code) {
        code.add("import 'react-native-gesture-handler';");
        code.add("import React from 'react';");
        code.add("import { PersistGate } from 'redux-persist/es/integration/react';");
        code.add("import { Provider } from 'react-redux';");
        code.add("import { store, persistor } from './Store';");
        code.add("import { NavigationContainer } from '@react-navigation/native';");
        code.add("import MainStack from './navigators/MainStack';");
        code.add("");
    }

    private void buildAppExport(List<String> code) {
        code.add("const App = () => {");
        code.add("    return (");
        code.add("        <Storage>");
        code.add("          <Navigation />");
        code.add("        </Storage>");
        code.add("    );");
        code.add("  }");
        code.add("");
        code.add("export default App;");
        code.add("");
    }

    private void buildAppStorage(List<String> code) {
        code.add("const Storage = ({ children }) => (");
        code.add("  <Provider store={store}>");
        code.add("    <PersistGate loading={null} persistor={persistor}>");
        code.add("      { children }");
        code.add("    </PersistGate>");
        code.add("  </Provider>");
        code.add(");");
        code.add("");
    }

    private void buildAppNavigation(List<String> code) {
        code.add("const Navigation = ({ children }) => (");
        code.add("    <NavigationContainer>");
        code.add("        <MainStack />");
        code.add("    </NavigationContainer>");
        code.add(");");
        code.add("");
    }

    private void generateNavigators() {
        List<String> code = new ArrayList<>();

        buildNavigatorsImports(code);
        buildNavigatorsStackNavigator(code);
        buildNavigatorsMainStack(code);
        buildNavigatorsExport(code);

        coreCodes.add(new FileCode("src/navigators/MainStack.js", code));
    }

    private void buildNavigatorsImports(List<String> code) {
        code.add("import React from 'react';");
        code.add("import { createStackNavigator } from '@react-navigation/stack';");

        for (String screenName : screensName) {
            code.add("import " + screenName + " from ../screens/" + screenName);
        }
        
        code.add("");
    }

    private void buildNavigatorsStackNavigator(List<String> code) {
        code.add("const Stack = createStackNavigator();");
        code.add("");
    }

    private void buildNavigatorsMainStack(List<String> code) {
        code.add("const MainStack = () => {");
        code.add("    return (");
        code.add("        <Stack.Navigator screenOptions={{headerShown: false}}>");
        
        for (String screenName : screensName) {
            code.add(buildStackNavigatorScreenTag(screenName));
        }
        
        code.add("        </Stack.Navigator>");
        code.add("    );");
        code.add("}");
        code.add("");
    }

    private String buildStackNavigatorScreenTag(String screenName) {
        StringBuilder code = new StringBuilder();

        code.append("            <Stack.Screen name=\"");
        code.append(screenName);
        code.append("\" component={");
        code.append(screenName);
        code.append("} />");

        return code.toString();
    }

    private void buildNavigatorsExport(List<String> code) {
        code.add("export default MainStack;");
    }
}
