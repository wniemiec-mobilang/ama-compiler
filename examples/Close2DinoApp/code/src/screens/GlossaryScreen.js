import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _c848bc643f3b812ffc87eafb1ea840fe = styled.View`background-color: #444;margin: 0;`;

const _5c2fa3c9f3f788fcd7aa9e5d4bce4598 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _13e1fa80dcc583e2db5c2e6ed492e791 = styled.View`background-color: #222;`;

const _5389eb085fbc2650a92eab08b0fbc342 = styled.Text``;

const _e41239ded976cdb662e28a84be2dcd34 = styled.View`margin: 10px;`;

const _ae9c1318bf612a48a89b26ec3789dfd = styled.TextInput``;

const _e1472c247dcc63e864623281bb23364a = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _2216b66d1f2f4ddc27441517ec899977 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _e2b20d8d466043cced7ce0b868ee096 = styled.Image``;

const _d12f37b4025635a310e20bb972b2c84 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _e8a44aee67bb00a98f9d7491ba0d74a4 = styled.Image``;


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
<_c848bc643f3b812ffc87eafb1ea840fe>
<_e1472c247dcc63e864623281bb23364a id="status-bar">
<_d12f37b4025635a310e20bb972b2c84 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_e8a44aee67bb00a98f9d7491ba0d74a4 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_e8a44aee67bb00a98f9d7491ba0d74a4>
</_d12f37b4025635a310e20bb972b2c84>
<_2216b66d1f2f4ddc27441517ec899977 OnPress={() => props.route.params.query="mobilang:screen:home"} id="back-btn">
<_e2b20d8d466043cced7ce0b868ee096 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_e2b20d8d466043cced7ce0b868ee096>
</_2216b66d1f2f4ddc27441517ec899977>
</_e1472c247dcc63e864623281bb23364a>
<_e41239ded976cdb662e28a84be2dcd34 id="search">
<_ae9c1318bf612a48a89b26ec3789dfd>
</_ae9c1318bf612a48a89b26ec3789dfd>
</_e41239ded976cdb662e28a84be2dcd34>
<_5c2fa3c9f3f788fcd7aa9e5d4bce4598 id="glossary">
<_5389eb085fbc2650a92eab08b0fbc342>
Glossário
</_5389eb085fbc2650a92eab08b0fbc342>
<_13e1fa80dcc583e2db5c2e6ed492e791 id="glossary-content">
</_13e1fa80dcc583e2db5c2e6ed492e791>
</_5c2fa3c9f3f788fcd7aa9e5d4bce4598>
</_c848bc643f3b812ffc87eafb1ea840fe>
);
}

export default GlossaryScreen;

