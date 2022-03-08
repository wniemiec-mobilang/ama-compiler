import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _143c4295ac8807babf4686a078d36df1 = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _a5b8cf6a83a6086551c33f9c5251b9e = styled.View`
margin: 10px;
color: white;
font-size: 15px;
`;

const _9dd9b7a6fd1df0738dd772c20c1211e9 = styled.View`
background-color: #222;
`;

const _60894bad9eabb4bff11d6fdcef7b07c3 = styled.Text`
margin: 20 0;
`;

const _82b2ca9a381b255bdfd2313d37fc76de = styled.View`
margin: 10px;
`;

const _4d01fc50f3a2587a2881cc4fd4a4b3b4 = styled.TextInput`
border: 1px solid #ccc;
border-radius: 10px;
padding-left: 20px;
width: 100%;
font-size: 20px;
height: 50px;
`;

const _d3c6f2c897ef67e0a007e7bffaef9781 = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _154d7070afe4b122f9d3326b10eb1b21 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _efaee681ac3907ae07d0fc82b907b1f1 = styled.Image`
height: 25px;
`;

const _ac3a73e7ef1bd6a58c2febf014694916 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _5cc2b72bc812ea79f68671785b27aa76 = styled.Image`
height: 25px;
`;


function GlossaryScreen(props) {


useEffect(() => {
function openDescription(item) {;
item.children[1].classList.toggle("item-content-open");
};
}, []);

return (
<_143c4295ac8807babf4686a078d36df1>
<_d3c6f2c897ef67e0a007e7bffaef9781 id="status-bar">
<_ac3a73e7ef1bd6a58c2febf014694916 OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_5cc2b72bc812ea79f68671785b27aa76 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_5cc2b72bc812ea79f68671785b27aa76>
</_ac3a73e7ef1bd6a58c2febf014694916>
<_154d7070afe4b122f9d3326b10eb1b21 OnPress={() => props.navigation.navigate("HomeScreen")} id="back-btn">
<_efaee681ac3907ae07d0fc82b907b1f1 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_efaee681ac3907ae07d0fc82b907b1f1>
</_154d7070afe4b122f9d3326b10eb1b21>
</_d3c6f2c897ef67e0a007e7bffaef9781>
<_82b2ca9a381b255bdfd2313d37fc76de id="search">
<_4d01fc50f3a2587a2881cc4fd4a4b3b4>
</_4d01fc50f3a2587a2881cc4fd4a4b3b4>
</_82b2ca9a381b255bdfd2313d37fc76de>
<_a5b8cf6a83a6086551c33f9c5251b9e id="glossary">
<_60894bad9eabb4bff11d6fdcef7b07c3>
Glossário
</_60894bad9eabb4bff11d6fdcef7b07c3>
<_9dd9b7a6fd1df0738dd772c20c1211e9 id="glossary-content">
</_9dd9b7a6fd1df0738dd772c20c1211e9>
</_a5b8cf6a83a6086551c33f9c5251b9e>
</_143c4295ac8807babf4686a078d36df1>
);
}

export default GlossaryScreen;

