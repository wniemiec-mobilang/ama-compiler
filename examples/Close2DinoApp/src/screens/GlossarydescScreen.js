import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _cc7f47aced5a27641d3d4d6e99c201a1 = styled.View`background-color: #222;margin: 0;`;

const _ebdf9bd14d470d1a47fcf97cc8304006 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _3a042d5a1373fa1ac4ebcfe33b30ed9d = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _d2fd907097f6edfc17165b0c6d6338af = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _7b97eca2246e67c98a96b782c46266df = styled.Image``;

const _f8cfbdceff719ca6d89d33901ef22c96 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _3cf889764b2abfb70a1bde01b9615e4 = styled.Image``;


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
<_cc7f47aced5a27641d3d4d6e99c201a1>
<_3a042d5a1373fa1ac4ebcfe33b30ed9d id="status-bar">
<_f8cfbdceff719ca6d89d33901ef22c96 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_3cf889764b2abfb70a1bde01b9615e4 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_3cf889764b2abfb70a1bde01b9615e4>
</_f8cfbdceff719ca6d89d33901ef22c96>
<_d2fd907097f6edfc17165b0c6d6338af OnPress={() => props.route.params.query="mobilang:screen:glossary"} id="back-btn">
<_7b97eca2246e67c98a96b782c46266df source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_7b97eca2246e67c98a96b782c46266df>
</_d2fd907097f6edfc17165b0c6d6338af>
</_3a042d5a1373fa1ac4ebcfe33b30ed9d>
<_ebdf9bd14d470d1a47fcf97cc8304006 id="glossary">
</_ebdf9bd14d470d1a47fcf97cc8304006>
</_cc7f47aced5a27641d3d4d6e99c201a1>
);
}

export default GlossarydescScreen;

