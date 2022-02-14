import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _64dcf6de29750254027ecd0079452467 = styled.View`background-color: #222;margin: 0;`;

const _25983b9b5418d91b6e0f721ad66368c1 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _651ba7ebd4cb78af4f637c95457e95fa = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _e9666cee95c517b0d26901e5d365fbb3 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _3727dc5cbabfce8cdfc807ea673d1aca = styled.Image``;

const _79f424a286071a7a14ce7a0f129f23d5 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _e498e1551c34359c5ff7d2160ebb3b61 = styled.Image``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou g�nglios aumentados, tamb�m conhecidos como �nguas.",id: "1",type: "Condi��o",content: "Adenomegalia � o aumento dos linfonodos (�nguas). Pode estar presente em crian�as e, na maior parte dos casos, � causada por infec��es virais. Mais raramente podem ser causadas por doen�as oncol�gicas tais como leucemias ou linfomas."},{name: "Alop�cia",description: "Queda de cabelos",id: "2",type: "Condi��o",content: "A alop�cia � a perda de cabelos do couro cabeludo ou de qualquer outra regi�o do corpo. Em crian�as em tratamento oncol�gico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id='glossary'><View class='header'><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary(_glossary);
}, []);

return (
<_64dcf6de29750254027ecd0079452467>
<_651ba7ebd4cb78af4f637c95457e95fa id='status-bar'>
<_79f424a286071a7a14ce7a0f129f23d5 onclick={() => props.route.params.query="mobilang:screen:home"} id='menu-btn'>
<_e498e1551c34359c5ff7d2160ebb3b61 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_e498e1551c34359c5ff7d2160ebb3b61>
</_79f424a286071a7a14ce7a0f129f23d5>
<_e9666cee95c517b0d26901e5d365fbb3 onclick={() => props.route.params.query="mobilang:screen:glossary"} id='back-btn'>
<_3727dc5cbabfce8cdfc807ea673d1aca source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_3727dc5cbabfce8cdfc807ea673d1aca>
</_e9666cee95c517b0d26901e5d365fbb3>
</_651ba7ebd4cb78af4f637c95457e95fa>
<_25983b9b5418d91b6e0f721ad66368c1 id='glossary'>
</_25983b9b5418d91b6e0f721ad66368c1>
</_64dcf6de29750254027ecd0079452467>
);
}

export default GlossarydescScreen;

