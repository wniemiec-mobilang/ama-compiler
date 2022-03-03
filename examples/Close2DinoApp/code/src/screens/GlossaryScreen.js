import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _722530e8123e24b8c4f83f5c5e126f86 = styled.View`background-color: #444;margin: 0;`;

const _53616033e9da22e3e4c3cc414475707 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _3152df84bd3d6fb28eb579599b6aed0b = styled.View`background-color: #222;`;

const _37c97564e576061b12287d6f60796622 = styled.Text``;

const _d8b62cb9695fa99bf6dcb10aac378d08 = styled.View`margin: 10px;`;

const _d9c5576a8418cb5f6defe410c8a0e0a7 = styled.TextInput``;

const _691eb6e765489587e130fe79fbde05e6 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _33a0f582f2007747e67659e3fce0b7b4 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _410c167f9fcb2cd22b1a0c30193a4048 = styled.Image``;

const _500e38c77728e70459935feee182f7d = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _2d91e49d884475cd962826fc828292e0 = styled.Image``;


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
<_722530e8123e24b8c4f83f5c5e126f86>
<_691eb6e765489587e130fe79fbde05e6 id="status-bar">
<_500e38c77728e70459935feee182f7d OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_2d91e49d884475cd962826fc828292e0 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_2d91e49d884475cd962826fc828292e0>
</_500e38c77728e70459935feee182f7d>
<_33a0f582f2007747e67659e3fce0b7b4 OnPress={() => props.route.params.query="mobilang:screen:home"} id="back-btn">
<_410c167f9fcb2cd22b1a0c30193a4048 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_410c167f9fcb2cd22b1a0c30193a4048>
</_33a0f582f2007747e67659e3fce0b7b4>
</_691eb6e765489587e130fe79fbde05e6>
<_d8b62cb9695fa99bf6dcb10aac378d08 id="search">
<_d9c5576a8418cb5f6defe410c8a0e0a7>
</_d9c5576a8418cb5f6defe410c8a0e0a7>
</_d8b62cb9695fa99bf6dcb10aac378d08>
<_53616033e9da22e3e4c3cc414475707 id="glossary">
<_37c97564e576061b12287d6f60796622>
Glossário
</_37c97564e576061b12287d6f60796622>
<_3152df84bd3d6fb28eb579599b6aed0b id="glossary-content">
</_3152df84bd3d6fb28eb579599b6aed0b>
</_53616033e9da22e3e4c3cc414475707>
</_722530e8123e24b8c4f83f5c5e126f86>
);
}

export default GlossaryScreen;

