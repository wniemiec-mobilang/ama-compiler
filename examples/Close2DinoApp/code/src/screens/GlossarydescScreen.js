import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _700d7d7dbfd11a105cdc477d76670bb = styled.SafeAreaView`
background-color: #222;
margin: 0;
flex: 1;
`;

const _12c8194ffb346ccc2df43c710f496b89 = styled.View`
`;

const _d7c6f80c92df0cd6882c0c161a687e2a = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _38e93d9d8f4dc72db52ae33d24f6a2ce = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _87100ef5a0e3f3d66458c0c6c32d3710 = styled.Image`
height: 25px;
`;

const _fdf195f6f908b0f875e87f142cf46d14 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _fe49df8f75f4bfd8f8027f364d269cb6 = styled.Image`
height: 25px;
`;


function GlossarydescScreen(props) {

[glossary_area,setGlossary_area] = useState([]);
[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id="props.route.params.id";
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id="glossary"><View class="header"><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary_area(_glossary_area);
setGlossary(_glossary);
}, []);

return (
<_700d7d7dbfd11a105cdc477d76670bb>
<_d7c6f80c92df0cd6882c0c161a687e2a id="status-bar">
<_fdf195f6f908b0f875e87f142cf46d14 OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_fe49df8f75f4bfd8f8027f364d269cb6 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_fe49df8f75f4bfd8f8027f364d269cb6>
</_fdf195f6f908b0f875e87f142cf46d14>
<_38e93d9d8f4dc72db52ae33d24f6a2ce OnPress={() => props.navigation.navigate("GlossaryScreen")} id="back-btn">
<_87100ef5a0e3f3d66458c0c6c32d3710 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_87100ef5a0e3f3d66458c0c6c32d3710>
</_38e93d9d8f4dc72db52ae33d24f6a2ce>
</_d7c6f80c92df0cd6882c0c161a687e2a>
<_12c8194ffb346ccc2df43c710f496b89 id="glossary-area">
</_12c8194ffb346ccc2df43c710f496b89>
</_700d7d7dbfd11a105cdc477d76670bb>
);
}

export default GlossarydescScreen;

