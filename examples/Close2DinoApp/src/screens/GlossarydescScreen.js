import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _50a6edee23e6efe9f1ef83386700e5f0 = styled.View``;

const _cae4e59f87c04e6c22e7ea098f872a9a = styled.div`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _27f55491159712258f0cee766a26dd91 = styled.div`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _8e022fa14a97a8d1069b7cdb7430da67 = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _227eb523f040fcee649b02dd63cf445b = styled.img``;

const _408387c38d1a68423a487b879210dbe = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _968d0fd9f1f354df9e3813808221da22 = styled.img``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[];
_glossary.push(<><div id='glossary'><div class='header'><h1>${glossaryItem.name}</h1><h2>${glossaryItem.type}</h2></div><p>${glossaryItem.description}</p></div></>);
setglossary(_glossary);
}, []);

return (
<_50a6edee23e6efe9f1ef83386700e5f0>
<_27f55491159712258f0cee766a26dd91 id='status-bar'>
<_408387c38d1a68423a487b879210dbe onclick='() ' id='menu-btn'>
<_968d0fd9f1f354df9e3813808221da22 src='https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png' height='25'>
</_968d0fd9f1f354df9e3813808221da22>
</_408387c38d1a68423a487b879210dbe>
<_8e022fa14a97a8d1069b7cdb7430da67 onclick='() ' id='back-btn'>
<_227eb523f040fcee649b02dd63cf445b src='http://cdn.onlinewebfonts.com/svg/img_259786.png' height='25'>
</_227eb523f040fcee649b02dd63cf445b>
</_8e022fa14a97a8d1069b7cdb7430da67>
</_27f55491159712258f0cee766a26dd91>
<_cae4e59f87c04e6c22e7ea098f872a9a id='glossary'>
</_cae4e59f87c04e6c22e7ea098f872a9a>
</_50a6edee23e6efe9f1ef83386700e5f0>
);
}

export default GlossarydescScreen;

