import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _90b89d32cdf0d6a468b91fd9d415780f = styled.View``;

const _8bb2955030688760f29fb1a1bcff41a1 = styled.div`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _a5a58f6e3b9bf82a8419e542fe076076 = styled.div`background-color: #222;`;

const _54f8d7b0fa884dd229f5d90d340c6b17 = styled.h1``;

const _a2f3f48e032559821b664c29273b3543 = styled.div`margin: 10px;`;

const _20eb2378f653a02ff5e4542d10c85262 = styled.input``;

const _d98320cb3564d369d178b712269203e = styled.div`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _6e684d3123dc87630508ceea1071878c = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _cc062dbca5a2e6a029e3a29c3b38704b = styled.img``;

const _af27a2defc9de0d61d11dd2bddc0492e = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _109c2e51cc797dc97132ad8a3807f937 = styled.img``;


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
_glossaryContent.push(<><div class='item'><h2>${item.name}</h2><div class='item-content'><h3>${item.type}</h3><p>${item.description}</p><a href='mobilang:screen:glossary-desc.html?id=${item.id}'>Leia mais</a></div></div></>);
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
<_90b89d32cdf0d6a468b91fd9d415780f>
<_d98320cb3564d369d178b712269203e id='status-bar'>
<_af27a2defc9de0d61d11dd2bddc0492e onclick='() ' id='menu-btn'>
<_109c2e51cc797dc97132ad8a3807f937 src='https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png' height='25'>
</_109c2e51cc797dc97132ad8a3807f937>
</_af27a2defc9de0d61d11dd2bddc0492e>
<_6e684d3123dc87630508ceea1071878c onclick='() ' id='back-btn'>
<_cc062dbca5a2e6a029e3a29c3b38704b src='http://cdn.onlinewebfonts.com/svg/img_259786.png' height='25'>
</_cc062dbca5a2e6a029e3a29c3b38704b>
</_6e684d3123dc87630508ceea1071878c>
</_d98320cb3564d369d178b712269203e>
<_a2f3f48e032559821b664c29273b3543 id='search'>
<_20eb2378f653a02ff5e4542d10c85262 placeholder='Buscar...' type='text'>
</_20eb2378f653a02ff5e4542d10c85262>
</_a2f3f48e032559821b664c29273b3543>
<_8bb2955030688760f29fb1a1bcff41a1 id='glossary'>
<_54f8d7b0fa884dd229f5d90d340c6b17>
Glossário
</_54f8d7b0fa884dd229f5d90d340c6b17>
<_a5a58f6e3b9bf82a8419e542fe076076 id='glossary-content'>
</_a5a58f6e3b9bf82a8419e542fe076076>
</_8bb2955030688760f29fb1a1bcff41a1>
</_90b89d32cdf0d6a468b91fd9d415780f>
);
}

export default GlossaryScreen;

