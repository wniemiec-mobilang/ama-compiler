import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _ec399ad05d474653bc43ed91eb461745 = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _8c03cb1e69f087cbc48316bd151fec69 = styled.View`
`;

const _55940666ccdd1d8e4df7122fab5ac4e7 = styled.TouchableOpacity`
background-color: #18bfa0;
padding: 20px;
flex-direction: row;
display: flex;
font-family: Arial;
`;

const _647a60ed8fdcae42cfc292612e433fb9 = styled.Text`
color: purple;
font-weight: bold;
text-decoration: none;
font-size: 20px;
text-align: center;
`;

const _3b666042fd26a53bd4404fd3cd52fcb0 = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _eb439d7a1cc2e8a69f256144cebe9a07 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _b28d4483eca8f5f66a22c88ea6461268 = styled.Image`
height: 25px;
`;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_ec399ad05d474653bc43ed91eb461745>
<_3b666042fd26a53bd4404fd3cd52fcb0 id="status-bar">
<_eb439d7a1cc2e8a69f256144cebe9a07 OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_b28d4483eca8f5f66a22c88ea6461268 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_b28d4483eca8f5f66a22c88ea6461268>
</_eb439d7a1cc2e8a69f256144cebe9a07>
</_3b666042fd26a53bd4404fd3cd52fcb0>
<_8c03cb1e69f087cbc48316bd151fec69 id="items">
<_55940666ccdd1d8e4df7122fab5ac4e7 onPress={() => props.navigation.navigate('GlossaryScreen')}>
<_647a60ed8fdcae42cfc292612e433fb9>
Glossário
</_647a60ed8fdcae42cfc292612e433fb9>
</_55940666ccdd1d8e4df7122fab5ac4e7>
</_8c03cb1e69f087cbc48316bd151fec69>
</_ec399ad05d474653bc43ed91eb461745>
);
}

export default HomeScreen;

