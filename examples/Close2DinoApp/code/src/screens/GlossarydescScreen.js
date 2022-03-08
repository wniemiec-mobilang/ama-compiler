import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _b38f749896f3197124c141ac9f7a3217 = styled.SafeAreaView`
background-color: #222;
margin: 0;
flex: 1;
`;

const _1586a3471928542f35357702adcb0287 = styled.View`
margin: 10px;
color: white;
font-size: 15px;
font-family: Arial, Helvetica, sans-serif;
`;

const _b604858d3971db70da6a4f97f40fb613 = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _5fbc9167a92f34c5c3dce48e66f74d21 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _1ccf5f808f315caf61fd61d2e2c0c623 = styled.Image`
height: 25px;
`;

const _b066a8409394fbea47a22fe5895179a7 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _b9b89bd06f18039abb2da2f11c886c24 = styled.Image`
height: 25px;
`;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=window.location.href.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id="glossary"><View class="header"><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary(_glossary);
}, []);

return (
<_b38f749896f3197124c141ac9f7a3217>
<_b604858d3971db70da6a4f97f40fb613 id="status-bar">
<_b066a8409394fbea47a22fe5895179a7 OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_b9b89bd06f18039abb2da2f11c886c24 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_b9b89bd06f18039abb2da2f11c886c24>
</_b066a8409394fbea47a22fe5895179a7>
<_5fbc9167a92f34c5c3dce48e66f74d21 OnPress={() => props.navigation.navigate("GlossaryScreen")} id="back-btn">
<_1ccf5f808f315caf61fd61d2e2c0c623 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_1ccf5f808f315caf61fd61d2e2c0c623>
</_5fbc9167a92f34c5c3dce48e66f74d21>
</_b604858d3971db70da6a4f97f40fb613>
<_1586a3471928542f35357702adcb0287 id="glossary">
</_1586a3471928542f35357702adcb0287>
</_b38f749896f3197124c141ac9f7a3217>
);
}

export default GlossarydescScreen;

