package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.asc.framework.coder.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.models.PersistenceVariable;


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
        generateReducerIndexCode();
        generateStandardReducerCode();
    }

    private void generateReducerIndexCode() {
        List<String> code = new ArrayList<>();

        code.add("import { combineReducers } from 'redux';");
        code.add("import StandardReducer from './StandardReducer'';");
        code.add("");
        code.add("export default combineReducers({");
        code.add("  StandardReducer");
        code.add("});");

        coreCodes.add(new FileCode("src/reducers/index.js", code));
    }

    private void generateStandardReducerCode() {
        List<String> code = new ArrayList<>();

        buildStandardReducerImport(code);
        buildStandardReducerInitialState(code);
        buildStandardReducerExport(code);

        coreCodes.add(new FileCode("src/reducers/StandardReducer.js", code));
    }

    private void buildStandardReducerImport(List<String> code) {
        code.add("import 'react-native-get-random-values';");
        code.add("import { v4 as uuid } from 'uuid';");
        code.add("");
    }

    private void buildStandardReducerInitialState(List<String> code) {
        code.add("const initialState = {");
        
        for (PersistenceVariable persistence : persistenceData.getVariables()) {
            code.add("  " + persistence.getName() + ": " + persistence.getInitialValue());
        }

        code.add("};");
        code.add("");
    }

    private void buildStandardReducerExport(List<String> code) {
        code.add("export default (state=initialState, action) => {");
        code.add("");
        code.add("  switch(action.type) {");

        for (PersistenceVariable variable : persistenceData.getVariables()) {
            code.add("    case SET_" + variable.getName().toUpperCase() + ":");
            code.add("      return { ...state, " + variable.getName() + ": " + "action.payload" + ";");
        }

        code.add("    default:");
        code.add("      return state;");
        code.add("  }");
        code.add("};");
    }

    private void generateStoreCode() {
        List<String> code = new ArrayList<>();

        buildStoreImports(code);
        buildStorePersistedReducer(code);
        buildStoreCreator(code);
        buildStorePersistor(code);
        buildStoreExport(code);

        coreCodes.add(new FileCode("src/Store.js", code));
    }

    private void buildStoreImports(List<String> code) {
        code.add("import { createStore } from 'redux';");
        code.add("import AsyncStorage from '@react-native-async-storage/async-storage';");
        code.add("import { persistStore, persistReducer } from 'redux-persist';");
        code.add("import Reducers from './reducers';");
        code.add("");
    }

    private void buildStorePersistedReducer(List<String> code) {
        code.add("const persistedReducer = persistReducer({");
        code.add("  key: 'root',");

        if (persistenceData.getType().equals("non-volatile")) {
            code.add("  storage: null,");
        }
        else {
            code.add("  storage: AsyncStorage,");
        }
        
        code.add("  whitelist: ['StandardReducer']");
        code.add("}, Reducers);");
        code.add("");
    }

    private void buildStoreCreator(List<String> code) {
        code.add("const store = createStore(persistedReducer);");
        code.add("");
    }

    private void buildStorePersistor(List<String> code) {
        code.add("const persistor = persistStore(store);");
        code.add("");
    }

    private void buildStoreExport(List<String> code) {
        code.add("export { store, persistor };");
    }
}
