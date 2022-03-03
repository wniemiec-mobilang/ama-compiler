import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _d9c8e833a987c41167f9684d712355a5 = styled.View`background-color: #222;margin: 0;`;

const _869ded16487497d1cdd4b96ce8e97408 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _25d2ed8a999f8c8da22deafd15203c22 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _d1c8d8f75c3008c53f9f91ba572e61a4 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _db2c590f7c16e1f8dd60307ed6df463c = styled.Image``;

const _7a271f556c6a903c9db82486ee250070 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _adbb1a51269416919ad72f596823f947 = styled.Image``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou g�nglios aumentados, tamb�m conhecidos como �nguas.",id: "1",type: "Condi��o",content: "Adenomegalia � o aumento dos linfonodos (�nguas). Pode estar presente em crian�as e, na maior parte dos casos, � causada por infec��es virais. Mais raramente podem ser causadas por doen�as oncol�gicas tais como leucemias ou linfomas."},{name: "Alop�cia",description: "Queda de cabelos",id: "2",type: "Condi��o",content: "A alop�cia � a perda de cabelos do couro cabeludo ou de qualquer outra regi�o do corpo. Em crian�as em tratamento oncol�gico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id="glossary"><View class="header"><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary(_glossary);
}, []);

return (
<_d9c8e833a987c41167f9684d712355a5>
<_25d2ed8a999f8c8da22deafd15203c22 id="status-bar">
<_7a271f556c6a903c9db82486ee250070 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_adbb1a51269416919ad72f596823f947 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_adbb1a51269416919ad72f596823f947>
</_7a271f556c6a903c9db82486ee250070>
<_d1c8d8f75c3008c53f9f91ba572e61a4 OnPress={() => props.route.params.query="mobilang:screen:glossary"} id="back-btn">
<_db2c590f7c16e1f8dd60307ed6df463c source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_db2c590f7c16e1f8dd60307ed6df463c>
</_d1c8d8f75c3008c53f9f91ba572e61a4>
</_25d2ed8a999f8c8da22deafd15203c22>
<_869ded16487497d1cdd4b96ce8e97408 id="glossary">
</_869ded16487497d1cdd4b96ce8e97408>
</_d9c8e833a987c41167f9684d712355a5>
);
}

export default GlossarydescScreen;

