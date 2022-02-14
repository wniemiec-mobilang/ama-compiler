import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _37f7bed3ed1e83701b6ecf0fb46038f3 = styled.View``;

const _8395d77a5fcbf81092f85a00d1b4e56a = styled.div`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _43150ade3a91b151d72ed18ae19ec210 = styled.div`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _416be5b8e52a64482c6ff1be63bdfd21 = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _c2c15af4b1b60ddf785725609e0eb448 = styled.img``;

const _1fea13475d550f16df10caf84abf7356 = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _4e1d371b142872d5d7f11c1c881ab998 = styled.img``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);
[Glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _Glossary=[];
_Glossary=[];
_Glossary.push(<><div id='glossary'><div class='header'><h1>${glossaryItem.name}</h1><h2>${glossaryItem.type}</h2></div><p>${glossaryItem.description}</p></div></>);
setGlossary(_glossary);
setGlossary(_Glossary);
}, []);

return (
<_37f7bed3ed1e83701b6ecf0fb46038f3>
<_43150ade3a91b151d72ed18ae19ec210 id='status-bar'>
<_1fea13475d550f16df10caf84abf7356 onclick='() => props.route.params.query="mobilang:screen:home"' id='menu-btn'>
<_4e1d371b142872d5d7f11c1c881ab998 src='https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png' height='25'>
</_4e1d371b142872d5d7f11c1c881ab998>
</_1fea13475d550f16df10caf84abf7356>
<_416be5b8e52a64482c6ff1be63bdfd21 onclick='() => props.route.params.query="mobilang:screen:glossary"' id='back-btn'>
<_c2c15af4b1b60ddf785725609e0eb448 src='http://cdn.onlinewebfonts.com/svg/img_259786.png' height='25'>
</_c2c15af4b1b60ddf785725609e0eb448>
</_416be5b8e52a64482c6ff1be63bdfd21>
</_43150ade3a91b151d72ed18ae19ec210>
<_8395d77a5fcbf81092f85a00d1b4e56a id='glossary'>
</_8395d77a5fcbf81092f85a00d1b4e56a>
</_37f7bed3ed1e83701b6ecf0fb46038f3>
);
}

export default GlossarydescScreen;

