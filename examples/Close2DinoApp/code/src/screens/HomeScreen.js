import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _de3351c5157fd7fcea1fd391c8e4e059 = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _c5ec79fdb03d8befdcaee17caeb2f399 = styled.View`
`;

const _f53bd4f298b23052d78cecd10b470df1 = styled.TouchableOpacity`
background-color: #18bfa0;
padding: 20px;
flex-direction: row;
display: flex;
`;

const _96d7923bf2e43ecd9b32af75cccd7bd2 = styled.Text`
color: purple;
font-weight: bold;
text-decoration: none;
font-size: 20px;
text-align: center;
`;

const _49b7c1d8c9492325915572b47f6bd584 = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _4895f14339f459521d7cfeb7a16547e0 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _1a29bd912d3c32bfb801f3b540cc5663 = styled.Image`
width: 25px;
height: 25px;
`;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_de3351c5157fd7fcea1fd391c8e4e059>
<_49b7c1d8c9492325915572b47f6bd584 id="status-bar">
<_4895f14339f459521d7cfeb7a16547e0 onPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_1a29bd912d3c32bfb801f3b540cc5663 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_1a29bd912d3c32bfb801f3b540cc5663>
</_4895f14339f459521d7cfeb7a16547e0>
</_49b7c1d8c9492325915572b47f6bd584>
<_c5ec79fdb03d8befdcaee17caeb2f399 id="items">
<_f53bd4f298b23052d78cecd10b470df1 onPress={() => props.navigation.navigate('GlossaryScreen')}>
<_96d7923bf2e43ecd9b32af75cccd7bd2>
Glossário
</_96d7923bf2e43ecd9b32af75cccd7bd2>
</_f53bd4f298b23052d78cecd10b470df1>
</_c5ec79fdb03d8befdcaee17caeb2f399>
</_de3351c5157fd7fcea1fd391c8e4e059>
);
}

export default HomeScreen;

