import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _6d878c02ca30463a14d763029549c2e = styled.SafeAreaView`background-color: #444;margin: 0;flex: 1;`;

const _b773cb5f3cddec7b1bd191a596cebf01 = styled.View`margin: 10px;color: white;font-size: 15px;font-family: Arial, Helvetica, sans-serif;`;

const _cbd3345c1dff5e42e7a6b5aa417be40 = styled.View`background-color: #222;`;

const _6f536b36cd873461133983a1bf1d9378 = styled.Text`margin: 20 0;`;

const _b8ed1f5b079dcee379a6bb5274e83fc4 = styled.View`margin: 10px;`;

const _4346c2139e12e57c8b74ee28e8f77a1e = styled.TextInput`border: 1px solid #ccc;border-radius: 10px;padding-left: 20px;width: 100%;font-size: 20px;height: 50px;`;

const _3d2667b9d4b353f0c57a77d6ba87afba = styled.View`background-color: #ccc;flex-direction: row;display: flex;width: 100%;align-items: center;height: 50px;`;

const _3060415d2ab14d1321962787bd46ce4d = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _e54dc1a7241eac4cf24a49b0efa1269b = styled.Image`height: 25px;`;

const _3bdc00a312376d86ddd3c6b33855807b = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _fa66cb54a54ec662f3180146af92022f = styled.Image`height: 25px;`;


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
<_6d878c02ca30463a14d763029549c2e>
<_3d2667b9d4b353f0c57a77d6ba87afba id="status-bar">
<_3bdc00a312376d86ddd3c6b33855807b OnPress={() => props.route.params.query="HomeScreen"} id="menu-btn">
<_fa66cb54a54ec662f3180146af92022f source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_fa66cb54a54ec662f3180146af92022f>
</_3bdc00a312376d86ddd3c6b33855807b>
<_3060415d2ab14d1321962787bd46ce4d OnPress={() => props.route.params.query="HomeScreen"} id="back-btn">
<_e54dc1a7241eac4cf24a49b0efa1269b source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_e54dc1a7241eac4cf24a49b0efa1269b>
</_3060415d2ab14d1321962787bd46ce4d>
</_3d2667b9d4b353f0c57a77d6ba87afba>
<_b8ed1f5b079dcee379a6bb5274e83fc4 id="search">
<_4346c2139e12e57c8b74ee28e8f77a1e>
</_4346c2139e12e57c8b74ee28e8f77a1e>
</_b8ed1f5b079dcee379a6bb5274e83fc4>
<_b773cb5f3cddec7b1bd191a596cebf01 id="glossary">
<_6f536b36cd873461133983a1bf1d9378>
Glossário
</_6f536b36cd873461133983a1bf1d9378>
<_cbd3345c1dff5e42e7a6b5aa417be40 id="glossary-content">
</_cbd3345c1dff5e42e7a6b5aa417be40>
</_b773cb5f3cddec7b1bd191a596cebf01>
</_6d878c02ca30463a14d763029549c2e>
);
}

export default GlossaryScreen;

