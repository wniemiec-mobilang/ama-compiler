import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _322c5a94d2c0b488332200cf452f6f7b = styled.SafeAreaView`background-color: #222;margin: 0;flex: 1;`;

const _595d0200ede0372e15f609e10729c72 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _95dbcc14d9cf22dc1830ccddc40a09c2 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _7a52f5c7a0e154e15cc6ae82733e6cd7 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _2de1e0ea4ce28c7f4bbf93e4d3624af5 = styled.Image`height: 25;`;

const _5b0f0dbbc7c2843c3f6147cf9b2c93ec = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _dc4c0b147c97be5fab267dc4e5415f18 = styled.Image`height: 25;`;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id="glossary"><View class="header"><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary(_glossary);
}, []);

return (
<_322c5a94d2c0b488332200cf452f6f7b>
<_95dbcc14d9cf22dc1830ccddc40a09c2 id="status-bar">
<_5b0f0dbbc7c2843c3f6147cf9b2c93ec OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_dc4c0b147c97be5fab267dc4e5415f18 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_dc4c0b147c97be5fab267dc4e5415f18>
</_5b0f0dbbc7c2843c3f6147cf9b2c93ec>
<_7a52f5c7a0e154e15cc6ae82733e6cd7 OnPress={() => props.route.params.query="mobilang:screen:glossary"} id="back-btn">
<_2de1e0ea4ce28c7f4bbf93e4d3624af5 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_2de1e0ea4ce28c7f4bbf93e4d3624af5>
</_7a52f5c7a0e154e15cc6ae82733e6cd7>
</_95dbcc14d9cf22dc1830ccddc40a09c2>
<_595d0200ede0372e15f609e10729c72 id="glossary">
</_595d0200ede0372e15f609e10729c72>
</_322c5a94d2c0b488332200cf452f6f7b>
);
}

export default GlossarydescScreen;

