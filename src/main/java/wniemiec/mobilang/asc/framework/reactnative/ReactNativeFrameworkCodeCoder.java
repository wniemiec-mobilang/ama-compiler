package wniemiec.mobilang.asc.framework.reactnative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wniemiec.mobilang.asc.framework.FrameworkCoreCoder;

public class ReactNativeFrameworkCodeCoder extends FrameworkCoreCoder {

    private Map<String, List<String>> coreCodes;

    public ReactNativeFrameworkCodeCoder(Set<String> screensName) {
        super(screensName);
        coreCodes = new HashMap<>();
    }

    @Override
    public Map<String, List<String>> generateCode() {
        generateIndexCode();
        generateAppCode();
        generateNavigators();
        //generateReducers();
        //generateStoreCode();

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

        coreCodes.put("index.js", code);
    }

    private void generateAppCode() {
        List<String> code = new ArrayList<>(Arrays.asList(
            "import 'react-native-gesture-handler';",
            "import React from 'react';",
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
            "const Navigation = ({ children }) => (",
            "    <NavigationContainer>",
            "        <MainStack />",
            "    </NavigationContainer>",
            ");"
        ));

        coreCodes.put("src/App.js", code);
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
        
        /*
            "import AppScreen from './src/AppScreen';",
            "import GlossaryDescScreen from './src/GlossaryDescScreen';",
            "import GlossaryScreen from './src/GlossaryScreen';",
            "",
            "const Stack = createStackNavigator();",
            "",
            "const MainStack = () => {",
            "    return (",
            "        <Stack.Navigator screenOptions={{headerShown: false}}>",
            "            <Stack.Screen name=\"AppScreen\" component={AppScreen} />",
            "            <Stack.Screen name=\"GlossaryScreen\" component={GlossaryScreen} />",
            "            <Stack.Screen name=\"GlossaryDescScreen\" component={GlossaryDescScreen} />",
            "        </Stack.Navigator>",
            "    );",
            "}",
            "",
            "export default MainStack;"

        );
        */

        coreCodes.put("src/navigators/MainStack.js", code);
    }

}
