import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _a9840e100b3f0f9e4123b9907eda80f3 = styled.SafeAreaView`background-color: #444;margin: 0;flex: 1;`;

const _ed5414bae67d773baa191460be4bf428 = styled.View``;

const _e658dec788951d25e870c4053ff8ef52 = styled.TouchableOpacity`background-color: #18bfa0;padding: 20px;`;

const _c361761465ba66a39041ee994cbcd3bd = styled.Text`color: purple;font-weight: bold;text-decoration: none;font-size: 20px;font-family: Arial;text-align: center;`;

const _364bcefe4deb9f456180826ed0f41308 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _623e3c2ad7e4efe8fdcda3da599bd2ee = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _69f62a003cc340210084fdf48f5b515 = styled.Image`height: 25px;`;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_a9840e100b3f0f9e4123b9907eda80f3>
<_364bcefe4deb9f456180826ed0f41308 id="status-bar">
<_623e3c2ad7e4efe8fdcda3da599bd2ee OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_69f62a003cc340210084fdf48f5b515 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_69f62a003cc340210084fdf48f5b515>
</_623e3c2ad7e4efe8fdcda3da599bd2ee>
</_364bcefe4deb9f456180826ed0f41308>
<_ed5414bae67d773baa191460be4bf428 id="items">
<_e658dec788951d25e870c4053ff8ef52 onPress={() => props.route.params.query = 'mobilang:screen:glossary'}>
<_c361761465ba66a39041ee994cbcd3bd>
Gloss�rio
</_c361761465ba66a39041ee994cbcd3bd>
</_e658dec788951d25e870c4053ff8ef52>
</_ed5414bae67d773baa191460be4bf428>
</_a9840e100b3f0f9e4123b9907eda80f3>
);
}

export default HomeScreen;

