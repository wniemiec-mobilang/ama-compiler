package wniemiec.mobilang.asc.framework.parser.reactnative;

import java.util.ArrayList;
import java.util.List;

public class ReactNativeImportGenerator {

    public List<String> generate() {
        List<String> imports = new ArrayList<>();
        imports.add("import React, { useEffect, useState } from 'react'");
        imports.add("import styled from 'styled-components/native'");

        return imports;
    }
}
