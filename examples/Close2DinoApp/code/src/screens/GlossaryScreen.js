import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _5052f2d817d0714fd4d0bd22d4f87774 = styled.View`background-color: #444;margin: 0;`;

const _7c81414fec30b126db5f0927da635faf = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _b56710a7cfc7e30de17e22d5cfdfa5 = styled.View`background-color: #222;`;

const _72c9bbf444cce46dffb51fb22d6dd164 = styled.Text``;

const _b88f0b0b8bcae2cdf528927e91dac4a9 = styled.View`margin: 10px;`;

const _942aa9f6965f9a3d67fef589a3a04bbc = styled.TextInput``;

const _ec9d2e0444639820f640b0e0f950acb6 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _f8f772c2d59b514d95d715554fbd0a6d = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _2fc52c92bc002ce446973fd461023ecf = styled.Image``;

const _650ac34a96e1b14c804cdd645c10ca85 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _cc6cbb2be8eb113f6ea3482649dce518 = styled.Image``;


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
<_5052f2d817d0714fd4d0bd22d4f87774>
<_ec9d2e0444639820f640b0e0f950acb6 id="status-bar">
<_650ac34a96e1b14c804cdd645c10ca85 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_cc6cbb2be8eb113f6ea3482649dce518 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_cc6cbb2be8eb113f6ea3482649dce518>
</_650ac34a96e1b14c804cdd645c10ca85>
<_f8f772c2d59b514d95d715554fbd0a6d OnPress={() => props.route.params.query="mobilang:screen:home"} id="back-btn">
<_2fc52c92bc002ce446973fd461023ecf source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_2fc52c92bc002ce446973fd461023ecf>
</_f8f772c2d59b514d95d715554fbd0a6d>
</_ec9d2e0444639820f640b0e0f950acb6>
<_b88f0b0b8bcae2cdf528927e91dac4a9 id="search">
<_942aa9f6965f9a3d67fef589a3a04bbc>
</_942aa9f6965f9a3d67fef589a3a04bbc>
</_b88f0b0b8bcae2cdf528927e91dac4a9>
<_7c81414fec30b126db5f0927da635faf id="glossary">
<_72c9bbf444cce46dffb51fb22d6dd164>
Glossário
</_72c9bbf444cce46dffb51fb22d6dd164>
<_b56710a7cfc7e30de17e22d5cfdfa5 id="glossary-content">
</_b56710a7cfc7e30de17e22d5cfdfa5>
</_7c81414fec30b126db5f0927da635faf>
</_5052f2d817d0714fd4d0bd22d4f87774>
);
}

export default GlossaryScreen;

