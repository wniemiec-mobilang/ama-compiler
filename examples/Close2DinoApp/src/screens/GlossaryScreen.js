import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _924a26b919d9764431c69591858f4ef3 = styled.View``;

const _97228e24dbaaf66db0c9387d996dcd4e = styled.div`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _49c35524584302410f5bea6f9c68d608 = styled.div`background-color: #222;`;

const _ac25496bb98b01e0124ef51c57e3adb5 = styled.h1``;

const _ec3a96c5f42ca67bea45958fa6300dc0 = styled.div`margin: 10px;`;

const _305f725bb1de36562db6a841fbaae75a = styled.input``;

const _762f7d9fcef908275f51dc188c9bc158 = styled.div`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _25e629721592c51d9d03786dba9bc078 = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _8c4fdc255a1ba4d84292e0997a1957cf = styled.img``;

const _d8987da9f2a35ae834a5018555de793a = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _94430748423f7b8cbb5835e651c88a4f = styled.img``;


function GlossaryScreen(props) {

[glossary_content,setGlossary_content] = useState([]);
[Glossarycontent,setGlossarycontent] = useState([]);

useEffect(() => {
function openDescription(item) {;
item.children[1].classList.toggle("item-content-open");
};
function makeGlossaryItemsClickable() {;
const items=document.querySelectorAll(".item");
for (let i="0";i<items.length;i++) {;
items.i.onclick=() => openDescription(items.i);
};
};
makeGlossaryItemsClickable();
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
function loadGlossaryWithName(name="") {;
let _Glossarycontent=[];
_Glossarycontent=[];
for (let item of data) {;
if (item.name.toLowerCase().startsWith(name.toLowerCase())) {;
_Glossarycontent.push(<><div class='item'><h2>${item.name}</h2><div class='item-content'><h3>${item.type}</h3><p>${item.description}</p><a href='mobilang:screen:glossary-desc.html?id=${item.id}'>Leia mais</a></div></div></>);
};
};
makeGlossaryItemsClickable();
};
loadGlossaryWithName();
const searchInput=document.getElementById("search").children[0];
searchInput.addEventListener("keyup",(event) => {;
loadGlossaryWithName(searchInput.value);
});
setGlossary_content(_glossary_content);
setGlossarycontent(_Glossarycontent);
}, []);

return (
<_924a26b919d9764431c69591858f4ef3>
<_762f7d9fcef908275f51dc188c9bc158 id='status-bar'>
<_d8987da9f2a35ae834a5018555de793a onclick='() => props.route.params.query="mobilang:screen:home"' id='menu-btn'>
<_94430748423f7b8cbb5835e651c88a4f src='https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png' height='25'>
</_94430748423f7b8cbb5835e651c88a4f>
</_d8987da9f2a35ae834a5018555de793a>
<_25e629721592c51d9d03786dba9bc078 onclick='() => props.route.params.query="mobilang:screen:home"' id='back-btn'>
<_8c4fdc255a1ba4d84292e0997a1957cf src='http://cdn.onlinewebfonts.com/svg/img_259786.png' height='25'>
</_8c4fdc255a1ba4d84292e0997a1957cf>
</_25e629721592c51d9d03786dba9bc078>
</_762f7d9fcef908275f51dc188c9bc158>
<_ec3a96c5f42ca67bea45958fa6300dc0 id='search'>
<_305f725bb1de36562db6a841fbaae75a placeholder='Buscar...' type='text'>
</_305f725bb1de36562db6a841fbaae75a>
</_ec3a96c5f42ca67bea45958fa6300dc0>
<_97228e24dbaaf66db0c9387d996dcd4e id='glossary'>
<_ac25496bb98b01e0124ef51c57e3adb5>
Glossário
</_ac25496bb98b01e0124ef51c57e3adb5>
<_49c35524584302410f5bea6f9c68d608 id='glossary-content'>
</_49c35524584302410f5bea6f9c68d608>
</_97228e24dbaaf66db0c9387d996dcd4e>
</_924a26b919d9764431c69591858f4ef3>
);
}

export default GlossaryScreen;

