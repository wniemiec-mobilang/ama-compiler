import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from '../screens/HomeScreen.js'
import GlossarydescScreen from '../screens/GlossarydescScreen.js'
import GlossaryScreen from '../screens/GlossaryScreen.js'

const Stack = createStackNavigator();

const MainStack = () => {
    return (
        <Stack.Navigator screenOptions={{headerShown: false}}>
            <Stack.Screen name="HomeScreen" component={HomeScreen} />
            <Stack.Screen name="GlossarydescScreen" component={GlossarydescScreen} />
            <Stack.Screen name="GlossaryScreen" component={GlossaryScreen} />
        </Stack.Navigator>
    );
}

export default MainStack;
