import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _553ae536080827db8d092d839bfbb5c6 = styled.SafeAreaView`
background-color: #222;
margin: 0;
flex: 1;
`;

const _6375ed38771404df598d85f2ffd0c4d8 = styled.View`
`;

const _b8f13001fd4c32faaad3304f6f2ebc3a = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _3aa9f7463d920b11995d04d5c18dec9f = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _5c8566449b24ea7331dba17ac92bd895 = styled.Image`
width: 25px;
height: 25px;
`;

const _7d6c3c91f96e8d68a010046c76eb8f13 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _95cad088bffb5f1f09ce762607824dbb = styled.Image`
width: 25px;
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
<_553ae536080827db8d092d839bfbb5c6>
<_b8f13001fd4c32faaad3304f6f2ebc3a id="status-bar">
<_7d6c3c91f96e8d68a010046c76eb8f13 OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_95cad088bffb5f1f09ce762607824dbb source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_95cad088bffb5f1f09ce762607824dbb>
</_7d6c3c91f96e8d68a010046c76eb8f13>
<_3aa9f7463d920b11995d04d5c18dec9f OnPress={() => props.navigation.navigate("GlossaryScreen")} id="back-btn">
<_5c8566449b24ea7331dba17ac92bd895 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_5c8566449b24ea7331dba17ac92bd895>
</_3aa9f7463d920b11995d04d5c18dec9f>
</_b8f13001fd4c32faaad3304f6f2ebc3a>
<_6375ed38771404df598d85f2ffd0c4d8 id="glossary-area">
</_6375ed38771404df598d85f2ffd0c4d8>
</_553ae536080827db8d092d839bfbb5c6>
);
}

export default GlossarydescScreen;

