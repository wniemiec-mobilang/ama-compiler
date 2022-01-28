import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const e9e06a246d8e47df0d107fe923720b48 = styled.View``;

const dca12781b88487847428ef334428a90e = styled.View``;

const e7ea67a3c735e4e593f8b220344da721 = styled.View``;

const c6bdb22596430bc35065717beb646c5a = styled.Text``;

const f52986d9f094ad64fcc5c7e71f516455 = styled.View``;

const ecb029b70b97bd96e4d28bd2819fdcff = styled.TextInput``;

const 2e3245140c4bfe1244d2c89631bbca43 = styled.View``;

const 6760314e16d008ea9e069ad77dddce6a = styled.TouchableOpacity``;

const d6594540f62500b9283f117c32afcd68 = styled.Image``;

const 86720023bfc33019cfcd5026e8fc7a8f = styled.TouchableOpacity``;

const 83f6f24cf72542d06860bc9bbae4aec8 = styled.Image``;


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
_glossaryContent.push(<><View class=item><Text>${item.name}</Text><View class=item-content><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress=() => redirectTo('mobilang:screen:glossary-desc.html?id=${item.id}')><Text>Leia mais</Text></TouchableOpacity></View></View></>);
};
};
makeGlossaryItemsClickable();
};
loadGlossaryWithName();
const searchInput=document.getElementById("search").children[0];
searchInput.addEventListener("keyup",(event) => {;
loadGlossaryWithName(searchInput.value);
});
setglossary_content(_glossary_content);
setglossaryContent(_glossaryContent);
}, []);

return (
<e9e06a246d8e47df0d107fe923720b48>
<2e3245140c4bfe1244d2c89631bbca43 id=status-bar>
<86720023bfc33019cfcd5026e8fc7a8f id=menu-btn .onclick=() >
<83f6f24cf72542d06860bc9bbae4aec8 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</83f6f24cf72542d06860bc9bbae4aec8>
</86720023bfc33019cfcd5026e8fc7a8f>
<6760314e16d008ea9e069ad77dddce6a id=back-btn .onclick=() >
<d6594540f62500b9283f117c32afcd68 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</d6594540f62500b9283f117c32afcd68>
</6760314e16d008ea9e069ad77dddce6a>
</2e3245140c4bfe1244d2c89631bbca43>
<f52986d9f094ad64fcc5c7e71f516455 id=search>
<ecb029b70b97bd96e4d28bd2819fdcff>
</ecb029b70b97bd96e4d28bd2819fdcff>
</f52986d9f094ad64fcc5c7e71f516455>
<dca12781b88487847428ef334428a90e id=glossary>
<c6bdb22596430bc35065717beb646c5a>
Glossário
</c6bdb22596430bc35065717beb646c5a>
<e7ea67a3c735e4e593f8b220344da721 id=glossary-content>
</e7ea67a3c735e4e593f8b220344da721>
</dca12781b88487847428ef334428a90e>
</e9e06a246d8e47df0d107fe923720b48>
);
}

export default GlossaryScreen;

