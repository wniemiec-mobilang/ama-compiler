import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _6fb06ad404f84381ac26047fb8c11e74 = styled.View`background-color: #444;margin: 0;`;

const _2f5be7233953ef5b42100ef90c9af46e = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _abd9dc7f957d132e24784b855d11fa89 = styled.View`background-color: #222;`;

const _fa476f5e7384197f6a469357f4df86f4 = styled.Text``;

const _15342dec5a2160a74657ae3d372c8c07 = styled.View`margin: 10px;`;

const _80bd784926d506de926d6f782ff08e81 = styled.TextInput``;

const _a85879f8a75e003ecb5a6e6508f73123 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _46fdc13971e3b2fcb3e88c157d537187 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _42411a11dc66bcf67f8910b98cad1fe9 = styled.Image``;

const _4c438d0d6bf181dbb8f99de3e1896ed2 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _55183ee88bc3b5d7c8a586af6bf3ba62 = styled.Image``;


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
_glossaryContent.push(`<><View class="item"><Text>${item.name}</Text><View class="item-content"><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress="() => redirectTo('mobilang:screen:glossary-desc.html?id=${item.id}')"></TouchableOpacity></View></View></>`);
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
<_6fb06ad404f84381ac26047fb8c11e74>
<_a85879f8a75e003ecb5a6e6508f73123 id="status-bar">
<_4c438d0d6bf181dbb8f99de3e1896ed2 onclick={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_55183ee88bc3b5d7c8a586af6bf3ba62 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_55183ee88bc3b5d7c8a586af6bf3ba62>
</_4c438d0d6bf181dbb8f99de3e1896ed2>
<_46fdc13971e3b2fcb3e88c157d537187 onclick={() => props.route.params.query="mobilang:screen:home"} id="back-btn">
<_42411a11dc66bcf67f8910b98cad1fe9 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_42411a11dc66bcf67f8910b98cad1fe9>
</_46fdc13971e3b2fcb3e88c157d537187>
</_a85879f8a75e003ecb5a6e6508f73123>
<_15342dec5a2160a74657ae3d372c8c07 id="search">
<_80bd784926d506de926d6f782ff08e81>
</_80bd784926d506de926d6f782ff08e81>
</_15342dec5a2160a74657ae3d372c8c07>
<_2f5be7233953ef5b42100ef90c9af46e id="glossary">
<_fa476f5e7384197f6a469357f4df86f4>
Glossário
</_fa476f5e7384197f6a469357f4df86f4>
<_abd9dc7f957d132e24784b855d11fa89 id="glossary-content">
</_abd9dc7f957d132e24784b855d11fa89>
</_2f5be7233953ef5b42100ef90c9af46e>
</_6fb06ad404f84381ac26047fb8c11e74>
);
}

export default GlossaryScreen;

