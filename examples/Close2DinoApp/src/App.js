import 'react-native-gesture-handler';
import React from 'react';
import { PersistGate } from 'redux-persist/es/integration/react';
import { Provider } from 'react-redux';
import { store, persistor } from './Store';
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

const Storage = ({ children }) => (
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      { children }
    </PersistGate>
  </Provider>
);

const Navigation = ({ children }) => (
    <NavigationContainer>
        <MainStack />
    </NavigationContainer>
);

