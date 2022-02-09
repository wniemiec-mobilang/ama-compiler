package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.framework.coder.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PersistenceData;


/**
 * Responsible for generating React Native framework code for persistence.
 */
public class ReactNativeFrameworkPersistenceCoder extends FrameworkPersistenceCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<FileCode> coreCodes;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFrameworkPersistenceCoder(PersistenceData persistenceData) {
        super(persistenceData);
        coreCodes = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public List<FileCode> generateCode() {
        generateStoreCode();
        generateReducers();

        return coreCodes;
    }

    private void generateReducers() {
        Consolex.writeWarning("React Native reducers has not been implemented yet");
        
        List<String> indexCode = new ArrayList<>(Arrays.asList(
            "import { combineReducers } from 'redux';",
            "import StandardReducer from './StandardReducer'';",
            "",
            "export default combineReducers({",
            "  StandardReducer",
            "});"
        ));

        coreCodes.add(new FileCode("src/reducers/index.js", indexCode));

        List<String> standardReducerCode = new ArrayList<>(Arrays.asList(
            "import 'react-native-get-random-values';",
            "import { v4 as uuid } from 'uuid';",
            "",
            "const initialState = {",
            "",
            "};",
            "",
            "export default (state=initialState, action) => {",
            "",
            "  switch(action.type) {",
            "    default:",
            "      return state;",
            "  }",
            "};"
        ));

        coreCodes.add(new FileCode("src/reducers/StandardReducer.js", standardReducerCode));
    }


    private void generateStoreCode() {
        List<String> code = new ArrayList<>(Arrays.asList(
            "import { createStore } from 'redux';",
            "import AsyncStorage from '@react-native-async-storage/async-storage';",
            "import { persistStore, persistReducer } from 'redux-persist';",
            "import Reducers from './reducers';",
            "",
            "const persistedReducer = persistReducer({",
            "  key: 'root',",
            "  storage: AsyncStorage,",
            "  whitelist: ['UserReducer']",
            "}, Reducers);",
            "",
            "const store = createStore(persistedReducer);",
            "const persistor = persistStore(store);",
            "",
            "export { store, persistor };"
        ));

        coreCodes.add(new FileCode("src/Store.js", code));
    }
}
