import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _985c2f6f14c462704a5e3d8399e84749 = styled.View`background-color: #444;margin: 0;`;

const _85b14bd7dfd4d58ac6080e9e263e36dc = styled.View``;

const _ba64d03caafacf512faa88e697e591f = styled.TouchableOpacity``;

const _a927da888294dc493179c723123abdbb = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _842044f0cd8620d446f1dce0a3b0e0b0 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _a97c7d939278ae8d9c92e8b5aec69dbf = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_985c2f6f14c462704a5e3d8399e84749>
<_a927da888294dc493179c723123abdbb id='status-bar'>
<_842044f0cd8620d446f1dce0a3b0e0b0 onclick={() => props.route.params.query="mobilang:screen:home"} id='menu-btn'>
<_a97c7d939278ae8d9c92e8b5aec69dbf source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_a97c7d939278ae8d9c92e8b5aec69dbf>
</_842044f0cd8620d446f1dce0a3b0e0b0>
</_a927da888294dc493179c723123abdbb>
<_85b14bd7dfd4d58ac6080e9e263e36dc id='items'>
<_ba64d03caafacf512faa88e697e591f onPress='() => redirectTo('mobilang:screen:glossary')'>
</_ba64d03caafacf512faa88e697e591f>
</_85b14bd7dfd4d58ac6080e9e263e36dc>
</_985c2f6f14c462704a5e3d8399e84749>
);
}

export default HomeScreen;

