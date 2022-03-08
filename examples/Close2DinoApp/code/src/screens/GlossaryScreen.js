import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _cc415bf8b323e36b612bae7b65e8ed78 = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _c601d1cd4c7731b8ee65b25b3b474281 = styled.View`
margin: 10px;
color: white;
font-size: 15px;
font-family: Arial, Helvetica, sans-serif;
`;

const _409fc80af16a91635c28d35d90a72fcd = styled.View`
background-color: #222;
`;

const _f8789ad467a29457b9996461f48ac6b = styled.Text`
margin: 20 0;
`;

const _356ba91d8bad14361b6fc310fe3f2bae = styled.View`
margin: 10px;
`;

const _d45c04d25438094ae430c147ed9cf289 = styled.TextInput`
border: 1px solid #ccc;
border-radius: 10px;
padding-left: 20px;
width: 100%;
font-size: 20px;
height: 50px;
`;

const _9698c203a590af237a11b01e697ca73f = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _6fc9fc96080a8b67db3db4818258a434 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _e5688c11edbd84772c55fad7c8932014 = styled.Image`
height: 25px;
`;

const _376a753b84b5c3f3bf40a59c375b1bf = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _907fbd544c3d842ac5bfe85f625be5b1 = styled.Image`
height: 25px;
`;


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
_glossaryContent.push(`<><View class="item"><Text>${item.name}</Text><View class="item-content"><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress={() => props.navigation.navigate('mobilang:screen:glossary-desc.html,{id:${item.id}}')}><Text>Leia mais</Text></TouchableOpacity></View></View></>`);
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
<_cc415bf8b323e36b612bae7b65e8ed78>
<_9698c203a590af237a11b01e697ca73f id="status-bar">
<_376a753b84b5c3f3bf40a59c375b1bf OnPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_907fbd544c3d842ac5bfe85f625be5b1 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_907fbd544c3d842ac5bfe85f625be5b1>
</_376a753b84b5c3f3bf40a59c375b1bf>
<_6fc9fc96080a8b67db3db4818258a434 OnPress={() => props.navigation.navigate("HomeScreen")} id="back-btn">
<_e5688c11edbd84772c55fad7c8932014 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_e5688c11edbd84772c55fad7c8932014>
</_6fc9fc96080a8b67db3db4818258a434>
</_9698c203a590af237a11b01e697ca73f>
<_356ba91d8bad14361b6fc310fe3f2bae id="search">
<_d45c04d25438094ae430c147ed9cf289>
</_d45c04d25438094ae430c147ed9cf289>
</_356ba91d8bad14361b6fc310fe3f2bae>
<_c601d1cd4c7731b8ee65b25b3b474281 id="glossary">
<_f8789ad467a29457b9996461f48ac6b>
Glossário
</_f8789ad467a29457b9996461f48ac6b>
<_409fc80af16a91635c28d35d90a72fcd id="glossary-content">
</_409fc80af16a91635c28d35d90a72fcd>
</_c601d1cd4c7731b8ee65b25b3b474281>
</_cc415bf8b323e36b612bae7b65e8ed78>
);
}

export default GlossaryScreen;

