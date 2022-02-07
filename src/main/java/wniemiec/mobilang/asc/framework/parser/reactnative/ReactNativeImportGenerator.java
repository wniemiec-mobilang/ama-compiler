package wniemiec.mobilang.asc.framework.parser.reactnative;

import java.util.ArrayList;
import java.util.List;


/**
 * Responsible for generation screen imports of React Native framework.
 */
class ReactNativeImportGenerator {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<String> generate() {
        List<String> imports = new ArrayList<>();
        
        imports.add("import React, { useEffect, useState } from 'react'");
        imports.add("import styled from 'styled-components/native'");

        return imports;
    }
}
