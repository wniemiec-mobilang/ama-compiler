import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const 2a31d8b4cf6750a90d9f7ffc42f380a1 = styled.View``;

const 68e1b51762d5919d4d2bedf9f15ae830 = styled.div`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const f6b7f29e1e22fbdde7433b7ecc9065eb = styled.div`background-color: #222;`;

const 107d94feb7219d61be5793f81a1ef618 = styled.h1``;

const ba7b4bacc61b1a186f60f00814b49b74 = styled.div`margin: 10px;`;

const cea083ab0ac32f9b63c7090df372424b = styled.input``;

const 2499c141d119abdaca9df63f8efeff82 = styled.div`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const ee42366e457642a9fedb2e820a998db3 = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const 74e99de109453e8b4b1fadb71fa96873 = styled.img``;

const fa9b068515b2eed6ad67ab31e4d25992 = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const 4391c8a795f18f3603e5f71c670cad3f = styled.img``;


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
_glossaryContent.push(<><div class=item><h2>${item.name}</h2><div class=item-content><h3>${item.type}</h3><p>${item.description}</p><a href=mobilang:screen:glossary-desc.html?id=${item.id}>Leia mais</a></div></div></>);
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
<2a31d8b4cf6750a90d9f7ffc42f380a1>
<2499c141d119abdaca9df63f8efeff82 id=status-bar>
<fa9b068515b2eed6ad67ab31e4d25992 id=menu-btn .onclick=() >
<4391c8a795f18f3603e5f71c670cad3f src=https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png height=25>
</4391c8a795f18f3603e5f71c670cad3f>
</fa9b068515b2eed6ad67ab31e4d25992>
<ee42366e457642a9fedb2e820a998db3 id=back-btn .onclick=() >
<74e99de109453e8b4b1fadb71fa96873 src=http://cdn.onlinewebfonts.com/svg/img_259786.png height=25>
</74e99de109453e8b4b1fadb71fa96873>
</ee42366e457642a9fedb2e820a998db3>
</2499c141d119abdaca9df63f8efeff82>
<ba7b4bacc61b1a186f60f00814b49b74 id=search>
<cea083ab0ac32f9b63c7090df372424b placeholder=Buscar... type=text>
</cea083ab0ac32f9b63c7090df372424b>
</ba7b4bacc61b1a186f60f00814b49b74>
<68e1b51762d5919d4d2bedf9f15ae830 id=glossary>
<107d94feb7219d61be5793f81a1ef618>
Glossário
</107d94feb7219d61be5793f81a1ef618>
<f6b7f29e1e22fbdde7433b7ecc9065eb id=glossary-content>
</f6b7f29e1e22fbdde7433b7ecc9065eb>
</68e1b51762d5919d4d2bedf9f15ae830>
</2a31d8b4cf6750a90d9f7ffc42f380a1>
);
}

export default GlossaryScreen;

