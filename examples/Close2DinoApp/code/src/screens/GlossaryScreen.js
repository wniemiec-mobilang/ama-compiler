import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _50df66b419d5eefa8f934926c06cd59d = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _ecc295dfb3f199dacbc4c11a473dae05 = styled.View`
margin: 10px;
color: white;
font-size: 15px;
`;

const _8b4262ea75fbfa356b54ede3ec995615 = styled.View`
background-color: #222;
`;

const _99d7dc72dc3ec28f8fcdf8f49f11105a = styled.Text`
margin: 20px 0;
color: white;
`;

const _8faed859fa3db477e7af71a43a7135 = styled.View`
margin: 10px;
`;

const _6de566fdf51e5c944554da1d2754a848 = styled.TextInput`
border: 1px solid #ccc;
border-radius: 10px;
padding-left: 20px;
width: 100%;
font-size: 20px;
height: 50px;
background-color: white;
`;

const _aea536131855323bce7f78cea85c53cd = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _aa55951f8aba3bade8f3d6e0ed4bcea7 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _c35b9acd677c784946a13a018a649090 = styled.Image`
width: 25px;
height: 25px;
`;

const _7115430171959aa06c4080f3228e0879 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _c5c3958075dfd378ec3add770197803 = styled.Image`
width: 25px;
height: 25px;
`;


function GlossaryScreen(props) {


useEffect(() => {
function openDescription(item) {;
item.children[1].classList.toggle("item-content-open");
};
}, []);

return (
<_50df66b419d5eefa8f934926c06cd59d>
<_aea536131855323bce7f78cea85c53cd id="status-bar">
<_7115430171959aa06c4080f3228e0879 OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_c5c3958075dfd378ec3add770197803 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_c5c3958075dfd378ec3add770197803>
</_7115430171959aa06c4080f3228e0879>
<_aa55951f8aba3bade8f3d6e0ed4bcea7 OnPress={() => props.navigation.navigate("HomeScreen")} id="back-btn">
<_c35b9acd677c784946a13a018a649090 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_c35b9acd677c784946a13a018a649090>
</_aa55951f8aba3bade8f3d6e0ed4bcea7>
</_aea536131855323bce7f78cea85c53cd>
<_8faed859fa3db477e7af71a43a7135 id="search">
<_6de566fdf51e5c944554da1d2754a848 placeholder="Buscar...">
</_6de566fdf51e5c944554da1d2754a848>
</_8faed859fa3db477e7af71a43a7135>
<_ecc295dfb3f199dacbc4c11a473dae05 id="glossary">
<_99d7dc72dc3ec28f8fcdf8f49f11105a>
Glossário
</_99d7dc72dc3ec28f8fcdf8f49f11105a>
<_8b4262ea75fbfa356b54ede3ec995615 id="glossary-content">
</_8b4262ea75fbfa356b54ede3ec995615>
</_ecc295dfb3f199dacbc4c11a473dae05>
</_50df66b419d5eefa8f934926c06cd59d>
);
}

export default GlossaryScreen;

