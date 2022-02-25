import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _eff42be9b2f98204768e5556a3ddb82c = styled.View`background-color: #444;margin: 0;`;

const _f1aef7166a3930388f6e75da74f0b63d = styled.View``;

const _5f0c91f0c69bf21e9ac5f624f473d13f = styled.TouchableOpacity``;

const _1b989e6d642924c0f71b700f903e826a = styled.Text``;

const _80b3f358f940530ce57f82eb58fb8d00 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _6236aecfed4dbfdb7d6f9485a08d7a90 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _f82466640122018dc45593a189a45cf7 = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_eff42be9b2f98204768e5556a3ddb82c>
<_80b3f358f940530ce57f82eb58fb8d00 id="status-bar">
<_6236aecfed4dbfdb7d6f9485a08d7a90 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_f82466640122018dc45593a189a45cf7 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_f82466640122018dc45593a189a45cf7>
</_6236aecfed4dbfdb7d6f9485a08d7a90>
</_80b3f358f940530ce57f82eb58fb8d00>
<_f1aef7166a3930388f6e75da74f0b63d id="items">
<_5f0c91f0c69bf21e9ac5f624f473d13f onPress="() => props.route.params.query = 'mobilang:screen:glossary')">
<_1b989e6d642924c0f71b700f903e826a>
Glossário
</_1b989e6d642924c0f71b700f903e826a>
</_5f0c91f0c69bf21e9ac5f624f473d13f>
</_f1aef7166a3930388f6e75da74f0b63d>
</_eff42be9b2f98204768e5556a3ddb82c>
);
}

export default HomeScreen;

