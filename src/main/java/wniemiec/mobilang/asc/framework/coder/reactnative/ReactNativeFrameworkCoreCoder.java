package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> code = new ArrayList<>(Arrays.asList(
            "import {AppRegistry} from 'react-native';",
            "import App from './src/App';",
            "import {name as appName} from './app.json';",
            "",
            "AppRegistry.registerComponent(appName, () => App);"
        ));

        coreCodes.add(new FileCode("index.js", code));
    }

    private void generateAppCode() {
        List<String> code = new ArrayList<>(Arrays.asList(
            "import 'react-native-gesture-handler';",
            "import React from 'react';",
            "import { PersistGate } from 'redux-persist/es/integration/react';",
            "import { Provider } from 'react-redux';",
            "import { store, persistor } from './Store';",
            "import { NavigationContainer } from '@react-navigation/native';",
            "import MainStack from './navigators/MainStack';",
            "",
            "const App = () => {",
            "    return (",
            "        <Storage>",
            "          <Navigation />",
            "        </Storage>",
            "    );",
            "  }",
            "",
            "export default App;",
            "",
            "const Storage = ({ children }) => (",
            "  <Provider store={store}>",
            "    <PersistGate loading={null} persistor={persistor}>",
            "      { children }",
            "    </PersistGate>",
            "  </Provider>",
            ");",
            "",
            "const Navigation = ({ children }) => (",
            "    <NavigationContainer>",
            "        <MainStack />",
            "    </NavigationContainer>",
            ");"
        ));

        coreCodes.add(new FileCode("src/App.js", code));
    }

    private void generateNavigators() {
        List<String> code = new ArrayList<>(Arrays.asList(
            "import React from 'react';",
            "import { createStackNavigator } from '@react-navigation/stack';"
        ));

        for (String screenName : screensName) {
            code.add("import " + screenName + " from ../screens/" + screenName);
        }
        
        code.add("");
        code.add("const Stack = createStackNavigator();");
        code.add("");
        code.add("const MainStack = () => {");
        code.add("    return (");
        code.add("        <Stack.Navigator screenOptions={{headerShown: false}}>");
        
        for (String screenName : screensName) {
            code.add("            <Stack.Screen name=\"" + screenName + "\" component={" + screenName + "} />");
        }
        
        code.add("        </Stack.Navigator>");
        code.add("    );");
        code.add("}");
        code.add("");
        code.add("export default MainStack;");

        coreCodes.add(new FileCode("src/navigators/MainStack.js", code));
    }
}
