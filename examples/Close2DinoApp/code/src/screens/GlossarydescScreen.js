import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _a346d6fffe3be196d9b46e47429027f8 = styled.View`background-color: #222;margin: 0;`;

const _aec6c4ba122379439495690855d1d0f7 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _8889bf59347cbd936373f5c7b4734647 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _9a59da179150e24b08f2b295c014233e = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _f042a6114bf94674579a2ed525274120 = styled.Image``;

const _562c07a7d3df35f10548a2ed548be896 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _93af7af7e96a52225e9d83823a081c3b = styled.Image``;


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
<_a346d6fffe3be196d9b46e47429027f8>
<_8889bf59347cbd936373f5c7b4734647 id="status-bar">
<_562c07a7d3df35f10548a2ed548be896 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_93af7af7e96a52225e9d83823a081c3b source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_93af7af7e96a52225e9d83823a081c3b>
</_562c07a7d3df35f10548a2ed548be896>
<_9a59da179150e24b08f2b295c014233e OnPress={() => props.route.params.query="mobilang:screen:glossary"} id="back-btn">
<_f042a6114bf94674579a2ed525274120 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_f042a6114bf94674579a2ed525274120>
</_9a59da179150e24b08f2b295c014233e>
</_8889bf59347cbd936373f5c7b4734647>
<_aec6c4ba122379439495690855d1d0f7 id="glossary">
</_aec6c4ba122379439495690855d1d0f7>
</_a346d6fffe3be196d9b46e47429027f8>
);
}

export default GlossarydescScreen;

