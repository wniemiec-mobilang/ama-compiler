import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import src/screens/GlossaryScreen.js from ../screens/src/screens/GlossaryScreen.js
import src/screens/GlossarydescScreen.js from ../screens/src/screens/GlossarydescScreen.js
import src/screens/HomeScreen.js from ../screens/src/screens/HomeScreen.js

const Stack = createStackNavigator();

const MainStack = () => {
    return (
        <Stack.Navigator screenOptions={{headerShown: false}}>
            <Stack.Screen name="src/screens/GlossaryScreen.js" component={src/screens/GlossaryScreen.js} />
            <Stack.Screen name="src/screens/GlossarydescScreen.js" component={src/screens/GlossarydescScreen.js} />
            <Stack.Screen name="src/screens/HomeScreen.js" component={src/screens/HomeScreen.js} />
        </Stack.Navigator>
    );
}

export default MainStack;
