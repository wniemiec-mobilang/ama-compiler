import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _8829be559981e7b2d3b0daa144f744fd = styled.View``;

const _a5c4f769ca0f0e5ce3430994774154f1 = styled.View``;

const _428a4f94981a7d6e46247f2de6569d7d = styled.View``;

const _94758d9c56ee4f014bf65d56079b3344 = styled.Text``;

const _685ea2af83e535aaedaae13ffe8012e = styled.View``;

const _4b782b64c1976b3cb494a4040dfaf39e = styled.TextInput``;

const _2d1dbfb44403d68fd7ad96eb311141f9 = styled.View``;

const _a1db5e435a4e79d039a10cf34bbf6f8e = styled.TouchableOpacity``;

const _ce9b6cc703ace8d28a9da936a8e02971 = styled.Image``;

const _c30c872bef18b16bf8ce7721e54327f2 = styled.TouchableOpacity``;

const _689e73315f80d952f16cb5524d9ff9ef = styled.Image``;


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
_glossaryContent=[];
for (let item of data) {;
if (item.name.toLowerCase().startsWith(name.toLowerCase())) {;
_glossaryContent.push(<><View class='item'><Text>${item.name}</Text><View class='item-content'><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress='() => redirectTo('mobilang:screen:glossary-desc.html?id=${item.id}')'></TouchableOpacity></View></View></>);
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
<_8829be559981e7b2d3b0daa144f744fd>
<_2d1dbfb44403d68fd7ad96eb311141f9 id='status-bar'>
<_c30c872bef18b16bf8ce7721e54327f2 onclick='() => props.route.params.query="mobilang:screen:home"' id='menu-btn'>
<_689e73315f80d952f16cb5524d9ff9ef source='{{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}'>
</_689e73315f80d952f16cb5524d9ff9ef>
</_c30c872bef18b16bf8ce7721e54327f2>
<_a1db5e435a4e79d039a10cf34bbf6f8e onclick='() => props.route.params.query="mobilang:screen:home"' id='back-btn'>
<_ce9b6cc703ace8d28a9da936a8e02971 source='{{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}'>
</_ce9b6cc703ace8d28a9da936a8e02971>
</_a1db5e435a4e79d039a10cf34bbf6f8e>
</_2d1dbfb44403d68fd7ad96eb311141f9>
<_685ea2af83e535aaedaae13ffe8012e id='search'>
<_4b782b64c1976b3cb494a4040dfaf39e>
</_4b782b64c1976b3cb494a4040dfaf39e>
</_685ea2af83e535aaedaae13ffe8012e>
<_a5c4f769ca0f0e5ce3430994774154f1 id='glossary'>
<_94758d9c56ee4f014bf65d56079b3344>
Glossário
</_94758d9c56ee4f014bf65d56079b3344>
<_428a4f94981a7d6e46247f2de6569d7d id='glossary-content'>
</_428a4f94981a7d6e46247f2de6569d7d>
</_a5c4f769ca0f0e5ce3430994774154f1>
</_8829be559981e7b2d3b0daa144f744fd>
);
}

export default GlossaryScreen;

