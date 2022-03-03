import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _a845fd8cb9e6495764ce06cb9cdf382 = styled.SafeAreaView`background-color: #444;margin: 0;flex: 1;`;

const _61fe0c6e785bcb7e959bd42200b230d8 = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _a0d0f3ac6a3994f209c24db1aae1f8de = styled.View`background-color: #222;`;

const _40e230665d12ee946f1d79980ccb2a1e = styled.Text`margin: 20 0;`;

const _5223ff1a9222b67d810ac1075bddbeca = styled.View`margin: 10px;`;

const _381113e4019ece5d7c2632e98301bafd = styled.TextInput`border: 1px solid #ccc;border-radius: 10px;padding-left: 20px;width: 100%;font-size: 20px;height: 50px;`;

const _62af66a7b36084bdb5067756e302381f = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _d527085ee39a23964fe93de937abf89e = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _1c2bc69fece0ebfda46a23dd21dda040 = styled.Image`height: 25;`;

const _bb4ce0659b604652db87b5d62d411c60 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _f85896be0f9feb93e3b4a15df7b387ad = styled.Image`height: 25;`;


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
_glossaryContent.push(`<><View class="item"><Text>${item.name}</Text><View class="item-content"><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress={() => props.route.params.query = 'mobilang:screen:glossary-desc.html?id=${item.id}'}><Text>Leia mais</Text></TouchableOpacity></View></View></>`);
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
<_a845fd8cb9e6495764ce06cb9cdf382>
<_62af66a7b36084bdb5067756e302381f id="status-bar">
<_bb4ce0659b604652db87b5d62d411c60 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_f85896be0f9feb93e3b4a15df7b387ad source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_f85896be0f9feb93e3b4a15df7b387ad>
</_bb4ce0659b604652db87b5d62d411c60>
<_d527085ee39a23964fe93de937abf89e OnPress={() => props.route.params.query="mobilang:screen:home"} id="back-btn">
<_1c2bc69fece0ebfda46a23dd21dda040 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_1c2bc69fece0ebfda46a23dd21dda040>
</_d527085ee39a23964fe93de937abf89e>
</_62af66a7b36084bdb5067756e302381f>
<_5223ff1a9222b67d810ac1075bddbeca id="search">
<_381113e4019ece5d7c2632e98301bafd>
</_381113e4019ece5d7c2632e98301bafd>
</_5223ff1a9222b67d810ac1075bddbeca>
<_61fe0c6e785bcb7e959bd42200b230d8 id="glossary">
<_40e230665d12ee946f1d79980ccb2a1e>
Glossário
</_40e230665d12ee946f1d79980ccb2a1e>
<_a0d0f3ac6a3994f209c24db1aae1f8de id="glossary-content">
</_a0d0f3ac6a3994f209c24db1aae1f8de>
</_61fe0c6e785bcb7e959bd42200b230d8>
</_a845fd8cb9e6495764ce06cb9cdf382>
);
}

export default GlossaryScreen;

