import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _13d820982df20daabb209d5f4dcaffa9 = styled.SafeAreaView`background-color: #444;margin: 0;flex: 1;`;

const _6f0e6c4e9043fd6234b6a5842bae64 = styled.View``;

const _1419ba86891bcd23c58898f8a5b47140 = styled.TouchableOpacity`background-color: #18bfa0;padding: 20px;flex-direction: row;display: flex;`;

const _cffa4af74526bb5966df061f735e1a03 = styled.Text`color: purple;font-weight: bold;text-decoration: none;font-size: 20px;font-family: Arial;text-align: center;`;

const _5e62bab8cfc665953c3f7b6d84199695 = styled.View`background-color: #ccc;flex-direction: row;display: flex;width: 100%;align-items: center;height: 50px;`;

const _d7eb71205a71e2763b2f518ccaf744bc = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _70a4544ebf1b78fcb203a3cf13ed2b89 = styled.Image`height: 25px;`;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_13d820982df20daabb209d5f4dcaffa9>
<_5e62bab8cfc665953c3f7b6d84199695 id="status-bar">
<_d7eb71205a71e2763b2f518ccaf744bc OnPress={() => props.route.params.query="HomeScreen"} id="menu-btn">
<_70a4544ebf1b78fcb203a3cf13ed2b89 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_70a4544ebf1b78fcb203a3cf13ed2b89>
</_d7eb71205a71e2763b2f518ccaf744bc>
</_5e62bab8cfc665953c3f7b6d84199695>
<_6f0e6c4e9043fd6234b6a5842bae64 id="items">
<_1419ba86891bcd23c58898f8a5b47140 onPress={() => props.route.params.query = 'GlossaryScreen'}>
<_cffa4af74526bb5966df061f735e1a03>
Glossário
</_cffa4af74526bb5966df061f735e1a03>
</_1419ba86891bcd23c58898f8a5b47140>
</_6f0e6c4e9043fd6234b6a5842bae64>
</_13d820982df20daabb209d5f4dcaffa9>
);
}

export default HomeScreen;

