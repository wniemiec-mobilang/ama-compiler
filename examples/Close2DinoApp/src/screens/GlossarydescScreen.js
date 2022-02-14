import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _5bacbf462f1f818e50976ecdf0cd1ccb = styled.View``;

const _4c49b02b12279b81e47d6145b0e358c4 = styled.View``;

const _2dc66414cf0449b861cee95f7828ecae = styled.View``;

const _9eea2dcf23709199a1616d5b43d0d279 = styled.TouchableOpacity``;

const _72713f8c3e6efecab9fd69b719921884 = styled.Image``;

const _6e60a83ab449ef17c6ae68667d952977 = styled.TouchableOpacity``;

const _5ac0762e0f681c83a692fca7d7d28d3f = styled.Image``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[];
_glossary.push(<><View id='glossary'><View class='header'><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>);
setGlossary(_glossary);
}, []);

return (
<_5bacbf462f1f818e50976ecdf0cd1ccb>
<_2dc66414cf0449b861cee95f7828ecae id='status-bar'>
<_6e60a83ab449ef17c6ae68667d952977 onclick={() => props.route.params.query="mobilang:screen:home"} id='menu-btn'>
<_5ac0762e0f681c83a692fca7d7d28d3f source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_5ac0762e0f681c83a692fca7d7d28d3f>
</_6e60a83ab449ef17c6ae68667d952977>
<_9eea2dcf23709199a1616d5b43d0d279 onclick={() => props.route.params.query="mobilang:screen:glossary"} id='back-btn'>
<_72713f8c3e6efecab9fd69b719921884 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_72713f8c3e6efecab9fd69b719921884>
</_9eea2dcf23709199a1616d5b43d0d279>
</_2dc66414cf0449b861cee95f7828ecae>
<_4c49b02b12279b81e47d6145b0e358c4 id='glossary'>
</_4c49b02b12279b81e47d6145b0e358c4>
</_5bacbf462f1f818e50976ecdf0cd1ccb>
);
}

export default GlossarydescScreen;

