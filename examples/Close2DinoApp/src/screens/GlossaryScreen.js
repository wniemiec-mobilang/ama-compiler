import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const 8eeb0eee90aa9367f6b91cdc23e8c2c6 = styled.View``;

const d0fefd73934ba8d076d35cc65d775ec2 = styled.View``;

const 9ca937ad3fa61c9ee28cf22606feae5e = styled.View``;

const ecceda90e82c584495bb13fa5827a45e = styled.Text``;

const 8059ad19aeac28e1d91de032dc5c496f = styled.View``;

const ab27ecf17cc6b7c890d731bc3b43e21 = styled.TextInput``;

const a41634685391afdbee82b5e7579b52ae = styled.View``;

const 25ba0af98a100a3e3c673ff11ce082ed = styled.TouchableOpacity``;

const 2124653bab23c6cee4b541e7661b6549 = styled.Image``;

const 478a86f87e71c405f84e8a39a6765a88 = styled.TouchableOpacity``;

const 92c3e2bf682d91c73fac74169526a52b = styled.Image``;


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
<8eeb0eee90aa9367f6b91cdc23e8c2c6>
<a41634685391afdbee82b5e7579b52ae id=status-bar>
<478a86f87e71c405f84e8a39a6765a88 id=menu-btn .onclick=() >
<92c3e2bf682d91c73fac74169526a52b source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</92c3e2bf682d91c73fac74169526a52b>
</478a86f87e71c405f84e8a39a6765a88>
<25ba0af98a100a3e3c673ff11ce082ed id=back-btn .onclick=() >
<2124653bab23c6cee4b541e7661b6549 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</2124653bab23c6cee4b541e7661b6549>
</25ba0af98a100a3e3c673ff11ce082ed>
</a41634685391afdbee82b5e7579b52ae>
<8059ad19aeac28e1d91de032dc5c496f id=search>
<ab27ecf17cc6b7c890d731bc3b43e21>
</ab27ecf17cc6b7c890d731bc3b43e21>
</8059ad19aeac28e1d91de032dc5c496f>
<d0fefd73934ba8d076d35cc65d775ec2 id=glossary>
<ecceda90e82c584495bb13fa5827a45e>
Glossário
</ecceda90e82c584495bb13fa5827a45e>
<9ca937ad3fa61c9ee28cf22606feae5e id=glossary-content>
</9ca937ad3fa61c9ee28cf22606feae5e>
</d0fefd73934ba8d076d35cc65d775ec2>
</8eeb0eee90aa9367f6b91cdc23e8c2c6>
);
}

export default GlossaryScreen;

