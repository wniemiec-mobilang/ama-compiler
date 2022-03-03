import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _cad3338ba193e158d3bf919fe4540c7c = styled.View`background-color: #444;margin: 0;`;

const _e29f394c0408ee8ecded74c220c55c16 = styled.View``;

const _74560ccb9c01ad813b9f758ccf356121 = styled.TouchableOpacity``;

const _6a5ea159cc82617463c496e4383b698e = styled.Text``;

const _54eef1b8b211308e609ef4ccbd54ecee = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _dcdc467b20e7689857364b2401bc19aa = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _ed238b4f6dcb5a4cefa48f8d4f1ee5db = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_cad3338ba193e158d3bf919fe4540c7c>
<_54eef1b8b211308e609ef4ccbd54ecee id="status-bar">
<_dcdc467b20e7689857364b2401bc19aa OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_ed238b4f6dcb5a4cefa48f8d4f1ee5db source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_ed238b4f6dcb5a4cefa48f8d4f1ee5db>
</_dcdc467b20e7689857364b2401bc19aa>
</_54eef1b8b211308e609ef4ccbd54ecee>
<_e29f394c0408ee8ecded74c220c55c16 id="items">
<_74560ccb9c01ad813b9f758ccf356121 onPress="() => props.route.params.query = 'mobilang:screen:glossary')">
<_6a5ea159cc82617463c496e4383b698e>
Glossário
</_6a5ea159cc82617463c496e4383b698e>
</_74560ccb9c01ad813b9f758ccf356121>
</_e29f394c0408ee8ecded74c220c55c16>
</_cad3338ba193e158d3bf919fe4540c7c>
);
}

export default HomeScreen;

