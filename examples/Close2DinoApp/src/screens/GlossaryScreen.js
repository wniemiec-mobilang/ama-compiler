import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _951bd8d72d2304af345f8b1b8dfc80f6 = styled.View`background-color: #444;margin: 0;`;

const _11bbb2bcb186e3ba39d13a5c457a9fd5 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _27c51228711da480c7ba9324434e1cb4 = styled.View`background-color: #222;`;

const _9af6ca607e7fe2ac5f3442c9dd7340fc = styled.Text``;

const _5cd1dbae6b0ca8947e5b39781e379231 = styled.View`margin: 10px;`;

const _9e6415269ad2f524400eeb9eb8606739 = styled.TextInput``;

const _962fb354e15e02deb418f2f8d9c89021 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _a94bd367f5fada0d71f5f73c05ed39c8 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _b6ccec9f32a8f9a25c589113dd510bc5 = styled.Image``;

const _45f4c0cb7a2da633369befbf01a35fad = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _e5175d5a90401274566c36397abddb53 = styled.Image``;


function GlossaryScreen(props) {

[glossary_content,setGlossary_content] = useState([]);
[glossaryContent,setGlossarycontent] = useState([]);

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
let _glossaryContent=[];
_glossaryContent=[``];
for (let item of data) {;
if (item.name.toLowerCase().startsWith(name.toLowerCase())) {;
_glossaryContent.push(`<><View class='item'><Text>${item.name}</Text><View class='item-content'><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress='() => redirectTo('mobilang:screen:glossary-desc.html?id=${item.id}')'></TouchableOpacity></View></View></>`);
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
setGlossarycontent(_glossaryContent);
}, []);

return (
<_951bd8d72d2304af345f8b1b8dfc80f6>
<_962fb354e15e02deb418f2f8d9c89021 id='status-bar'>
<_45f4c0cb7a2da633369befbf01a35fad onclick={() => props.route.params.query="mobilang:screen:home"} id='menu-btn'>
<_e5175d5a90401274566c36397abddb53 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_e5175d5a90401274566c36397abddb53>
</_45f4c0cb7a2da633369befbf01a35fad>
<_a94bd367f5fada0d71f5f73c05ed39c8 onclick={() => props.route.params.query="mobilang:screen:home"} id='back-btn'>
<_b6ccec9f32a8f9a25c589113dd510bc5 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_b6ccec9f32a8f9a25c589113dd510bc5>
</_a94bd367f5fada0d71f5f73c05ed39c8>
</_962fb354e15e02deb418f2f8d9c89021>
<_5cd1dbae6b0ca8947e5b39781e379231 id='search'>
<_9e6415269ad2f524400eeb9eb8606739>
</_9e6415269ad2f524400eeb9eb8606739>
</_5cd1dbae6b0ca8947e5b39781e379231>
<_11bbb2bcb186e3ba39d13a5c457a9fd5 id='glossary'>
<_9af6ca607e7fe2ac5f3442c9dd7340fc>
Glossário
</_9af6ca607e7fe2ac5f3442c9dd7340fc>
<_27c51228711da480c7ba9324434e1cb4 id='glossary-content'>
</_27c51228711da480c7ba9324434e1cb4>
</_11bbb2bcb186e3ba39d13a5c457a9fd5>
</_951bd8d72d2304af345f8b1b8dfc80f6>
);
}

export default GlossaryScreen;

