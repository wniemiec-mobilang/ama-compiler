import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const 9d95b2abdf2d4eaa6c0d7f431075cce9 = styled.View``;

const 3e0983b32bf5fc464f34533d2c556e53 = styled.View``;

const 37a72647fae79e2c61a8dd521345084e = styled.View``;

const 52f2db7ac834ec0ef71e10ae5a93e853 = styled.Text``;

const 273369066b7b8a9cb97a84c122a25d5a = styled.View``;

const b2dd693708eb036cd039dd231e2edeb7 = styled.TextInput``;

const d2164a44057d90255d92db4695390e1b = styled.View``;

const 5daa351002895400b42eae1ae3afa4ea = styled.TouchableOpacity``;

const 885074b71f6576b66e81d1ecd97ae1d3 = styled.Image``;

const 4a9774f0d2b6fa2915ccab8fb9970219 = styled.TouchableOpacity``;

const d629b737747519953c17cf749431a203 = styled.Image``;


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
<9d95b2abdf2d4eaa6c0d7f431075cce9>
<d2164a44057d90255d92db4695390e1b id=status-bar>
<4a9774f0d2b6fa2915ccab8fb9970219 id=menu-btn .onclick=() >
<d629b737747519953c17cf749431a203 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</d629b737747519953c17cf749431a203>
</4a9774f0d2b6fa2915ccab8fb9970219>
<5daa351002895400b42eae1ae3afa4ea id=back-btn .onclick=() >
<885074b71f6576b66e81d1ecd97ae1d3 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</885074b71f6576b66e81d1ecd97ae1d3>
</5daa351002895400b42eae1ae3afa4ea>
</d2164a44057d90255d92db4695390e1b>
<273369066b7b8a9cb97a84c122a25d5a id=search>
<b2dd693708eb036cd039dd231e2edeb7>
</b2dd693708eb036cd039dd231e2edeb7>
</273369066b7b8a9cb97a84c122a25d5a>
<3e0983b32bf5fc464f34533d2c556e53 id=glossary>
<52f2db7ac834ec0ef71e10ae5a93e853>
Glossário
</52f2db7ac834ec0ef71e10ae5a93e853>
<37a72647fae79e2c61a8dd521345084e id=glossary-content>
</37a72647fae79e2c61a8dd521345084e>
</3e0983b32bf5fc464f34533d2c556e53>
</9d95b2abdf2d4eaa6c0d7f431075cce9>
);
}

export default GlossaryScreen;

