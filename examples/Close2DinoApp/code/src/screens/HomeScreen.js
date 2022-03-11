import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _182c784fa55c73437bb1dc1db001585e = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _773c3d7c18f66ef32ead3b8ef39051 = styled.View`
`;

const _28ddf78f27e0979e8a0a4f529ecb868 = styled.TouchableOpacity`
background-color: #18bfa0;
padding: 20px;
flex-direction: row;
display: flex;
`;

const _c96a1dd1906477abe176cf9fbc317bbb = styled.Text`
color: purple;
font-weight: bold;
text-decoration: none;
font-size: 20px;
text-align: center;
`;

const _46f55991a36a3ed77b99a0fc4f58a4f5 = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _4a4a107ee72acac1f2e69234938de05e = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _2239393c9a449abd8ed7c0a210e171d = styled.Image`
width: 25px;
height: 25px;
`;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_182c784fa55c73437bb1dc1db001585e>
<_46f55991a36a3ed77b99a0fc4f58a4f5 id="status-bar">
<_4a4a107ee72acac1f2e69234938de05e OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_2239393c9a449abd8ed7c0a210e171d source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_2239393c9a449abd8ed7c0a210e171d>
</_4a4a107ee72acac1f2e69234938de05e>
</_46f55991a36a3ed77b99a0fc4f58a4f5>
<_773c3d7c18f66ef32ead3b8ef39051 id="items">
<_28ddf78f27e0979e8a0a4f529ecb868 onPress={() => props.navigation.navigate('GlossaryScreen')}>
<_c96a1dd1906477abe176cf9fbc317bbb>
Glossário
</_c96a1dd1906477abe176cf9fbc317bbb>
</_28ddf78f27e0979e8a0a4f529ecb868>
</_773c3d7c18f66ef32ead3b8ef39051>
</_182c784fa55c73437bb1dc1db001585e>
);
}

export default HomeScreen;

