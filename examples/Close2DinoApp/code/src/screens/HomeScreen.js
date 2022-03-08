import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _157b01a0dfc04ba1ed238b0cf8d3646f = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _c07fcfee6a075cbb9b67ed34d4455b36 = styled.View`
`;

const _5f81d7bc09917122b1c4f6111cf9c743 = styled.TouchableOpacity`
background-color: #18bfa0;
padding: 20px;
flex-direction: row;
display: flex;
`;

const _33170ada0276a3a713c860534e014a82 = styled.Text`
color: purple;
font-weight: bold;
text-decoration: none;
font-size: 20px;
text-align: center;
`;

const _4ead901a365052c434e2779c79371499 = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _ee334f7d3a1faaec34562c85e3b320e0 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _1bdff3f41b6d94a805afa3d1c8083c25 = styled.Image`
height: 25px;
`;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_157b01a0dfc04ba1ed238b0cf8d3646f>
<_4ead901a365052c434e2779c79371499 id="status-bar">
<_ee334f7d3a1faaec34562c85e3b320e0 OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_1bdff3f41b6d94a805afa3d1c8083c25 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_1bdff3f41b6d94a805afa3d1c8083c25>
</_ee334f7d3a1faaec34562c85e3b320e0>
</_4ead901a365052c434e2779c79371499>
<_c07fcfee6a075cbb9b67ed34d4455b36 id="items">
<_5f81d7bc09917122b1c4f6111cf9c743 onPress={() => props.navigation.navigate('GlossaryScreen')}>
<_33170ada0276a3a713c860534e014a82>
Glossário
</_33170ada0276a3a713c860534e014a82>
</_5f81d7bc09917122b1c4f6111cf9c743>
</_c07fcfee6a075cbb9b67ed34d4455b36>
</_157b01a0dfc04ba1ed238b0cf8d3646f>
);
}

export default HomeScreen;

