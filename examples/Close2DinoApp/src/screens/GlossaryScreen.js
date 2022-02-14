import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _94508b84420558c620768ba0e022fcdc = styled.View``;

const _6f11f4ad8627bcd73e8a4d4ef36bf9bd = styled.View``;

const _e3159b355ce198da81d806770432908f = styled.View``;

const _3908b27c0fa434fbdac8ea3765eea105 = styled.Text``;

const _34ee9666ef2e35f313b04819cd7010a4 = styled.View``;

const _80cf32d21ad018fdf485f48d34ef8e47 = styled.TextInput``;

const _cb1fc127f03c1ae9e239bffe953081ee = styled.View``;

const _28325361996eca039b2337ebc59ef139 = styled.TouchableOpacity``;

const _9c007c0494ffc36e7b0f8563763432bc = styled.Image``;

const _3f13d29de00679c7c56b8ba534ae56c3 = styled.TouchableOpacity``;

const _b2da65d059886365ce5e3b85b18a8e15 = styled.Image``;


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
<_94508b84420558c620768ba0e022fcdc>
<_cb1fc127f03c1ae9e239bffe953081ee id='status-bar'>
<_3f13d29de00679c7c56b8ba534ae56c3 onclick={() => props.route.params.query="mobilang:screen:home"} id='menu-btn'>
<_b2da65d059886365ce5e3b85b18a8e15 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_b2da65d059886365ce5e3b85b18a8e15>
</_3f13d29de00679c7c56b8ba534ae56c3>
<_28325361996eca039b2337ebc59ef139 onclick={() => props.route.params.query="mobilang:screen:home"} id='back-btn'>
<_9c007c0494ffc36e7b0f8563763432bc source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_9c007c0494ffc36e7b0f8563763432bc>
</_28325361996eca039b2337ebc59ef139>
</_cb1fc127f03c1ae9e239bffe953081ee>
<_34ee9666ef2e35f313b04819cd7010a4 id='search'>
<_80cf32d21ad018fdf485f48d34ef8e47>
</_80cf32d21ad018fdf485f48d34ef8e47>
</_34ee9666ef2e35f313b04819cd7010a4>
<_6f11f4ad8627bcd73e8a4d4ef36bf9bd id='glossary'>
<_3908b27c0fa434fbdac8ea3765eea105>
Glossário
</_3908b27c0fa434fbdac8ea3765eea105>
<_e3159b355ce198da81d806770432908f id='glossary-content'>
</_e3159b355ce198da81d806770432908f>
</_6f11f4ad8627bcd73e8a4d4ef36bf9bd>
</_94508b84420558c620768ba0e022fcdc>
);
}

export default GlossaryScreen;

