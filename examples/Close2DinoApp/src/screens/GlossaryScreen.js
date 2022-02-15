import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _a9f2f3c64aa16af1178c450e395fe779 = styled.View`background-color: #444;margin: 0;`;

const _388b070616ee1a3af085711a7cf5c10c = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _86958666b774332e9711934c5a7aae92 = styled.View`background-color: #222;`;

const _5ede350ed9d91285362b3202100c1cdf = styled.Text``;

const _1fdaa51f801ebb6aac504912a0aa7210 = styled.View`margin: 10px;`;

const _4bcae6ae851b1b51142f93c5c61b8713 = styled.TextInput``;

const _debfe85e3545aa61746da093eed8ce15 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _9f11a67f1cd9f4036cf24712bf53abad = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _55e776a52ab9adf4622c3163514258f9 = styled.Image``;

const _2b9e1ec7c99f23b07a9743658161666f = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _b4badaec7dd1ad7cabaed40cd7c3d67d = styled.Image``;


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
<_a9f2f3c64aa16af1178c450e395fe779>
<_debfe85e3545aa61746da093eed8ce15 id="status-bar">
<_2b9e1ec7c99f23b07a9743658161666f OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_b4badaec7dd1ad7cabaed40cd7c3d67d source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_b4badaec7dd1ad7cabaed40cd7c3d67d>
</_2b9e1ec7c99f23b07a9743658161666f>
<_9f11a67f1cd9f4036cf24712bf53abad OnPress={() => props.route.params.query="mobilang:screen:home"} id="back-btn">
<_55e776a52ab9adf4622c3163514258f9 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_55e776a52ab9adf4622c3163514258f9>
</_9f11a67f1cd9f4036cf24712bf53abad>
</_debfe85e3545aa61746da093eed8ce15>
<_1fdaa51f801ebb6aac504912a0aa7210 id="search">
<_4bcae6ae851b1b51142f93c5c61b8713>
</_4bcae6ae851b1b51142f93c5c61b8713>
</_1fdaa51f801ebb6aac504912a0aa7210>
<_388b070616ee1a3af085711a7cf5c10c id="glossary">
<_5ede350ed9d91285362b3202100c1cdf>
Glossário
</_5ede350ed9d91285362b3202100c1cdf>
<_86958666b774332e9711934c5a7aae92 id="glossary-content">
</_86958666b774332e9711934c5a7aae92>
</_388b070616ee1a3af085711a7cf5c10c>
</_a9f2f3c64aa16af1178c450e395fe779>
);
}

export default GlossaryScreen;

