import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _49867643a085ed9fadbb49458e04a3a = styled.View`background-color: #444;margin: 0;`;

const _e7a1653637b053b8da5673d2a6255edb = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _7b1186465f8b36804cbca3f454559385 = styled.View`background-color: #222;`;

const _401cfc27c9b291ac2d2f4938f8a0ebbc = styled.Text``;

const _33ae15cdb244effad88b779e6894848f = styled.View`margin: 10px;`;

const _2cded755eaae60e2badbd6fd0c5e40a4 = styled.TextInput``;

const _741b88a9d6ff1fd13dbd6c45c07429da = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _a3d7b3a7d915964c2fa1ed5d025bc644 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _cd43f2463356925116013fb1806dae67 = styled.Image``;

const _996fed8e7e7c59e176abc5d698701b4a = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _d56b58ea09c6b6c83ffde111ece54543 = styled.Image``;


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
_glossaryContent.push(`<><View class="item"><Text>${item.name}</Text><View class="item-content"><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress="() => props.route.params.query = 'mobilang:screen:glossary-desc.html?id=${item.id}')"><Text>Leia mais</Text></TouchableOpacity></View></View></>`);
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
<_49867643a085ed9fadbb49458e04a3a>
<_741b88a9d6ff1fd13dbd6c45c07429da id="status-bar">
<_996fed8e7e7c59e176abc5d698701b4a OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_d56b58ea09c6b6c83ffde111ece54543 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_d56b58ea09c6b6c83ffde111ece54543>
</_996fed8e7e7c59e176abc5d698701b4a>
<_a3d7b3a7d915964c2fa1ed5d025bc644 OnPress={() => props.route.params.query="mobilang:screen:home"} id="back-btn">
<_cd43f2463356925116013fb1806dae67 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_cd43f2463356925116013fb1806dae67>
</_a3d7b3a7d915964c2fa1ed5d025bc644>
</_741b88a9d6ff1fd13dbd6c45c07429da>
<_33ae15cdb244effad88b779e6894848f id="search">
<_2cded755eaae60e2badbd6fd0c5e40a4>
</_2cded755eaae60e2badbd6fd0c5e40a4>
</_33ae15cdb244effad88b779e6894848f>
<_e7a1653637b053b8da5673d2a6255edb id="glossary">
<_401cfc27c9b291ac2d2f4938f8a0ebbc>
Glossário
</_401cfc27c9b291ac2d2f4938f8a0ebbc>
<_7b1186465f8b36804cbca3f454559385 id="glossary-content">
</_7b1186465f8b36804cbca3f454559385>
</_e7a1653637b053b8da5673d2a6255edb>
</_49867643a085ed9fadbb49458e04a3a>
);
}

export default GlossaryScreen;

