import 'react-native-gesture-handler';
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import MainStack from './navigators/MainStack';

const App = () => {
    return (
        <Storage>
          <Navigation />
        </Storage>
    );
  }

export default App;

const Navigation = ({ children }) => (
    <NavigationContainer>
        <MainStack />
    </NavigationContainer>
);
