import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _a1dc2d700b12a80c75037135809e5603 = styled.SafeAreaView`
background-color: #222;
margin: 0;
flex: 1;
`;

const _7135e052897e6073115ac0dc592c70ef = styled.View`
`;

const _763de7e24662732cd0f86dc196a983cc = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _893f61fbd7fd9bc6f6014ecf4978dba = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _4c2ace80f5aa30af4641ff838b86381d = styled.Image`
width: 25px;
height: 25px;
`;

const _a9f5db056c7ecfa870770d6895024b71 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _5ecc91526b858aa674004e8b2470863d = styled.Image`
width: 25px;
height: 25px;
`;


function GlossarydescScreen(props) {

[glossary_area,setGlossary_area] = useState([]);
[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id="props.route.params.id";
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id="glossary"><View class="header"><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary_area(_glossary_area);
setGlossary(_glossary);
}, []);

return (
<_a1dc2d700b12a80c75037135809e5603>
<_763de7e24662732cd0f86dc196a983cc id="status-bar">
<_a9f5db056c7ecfa870770d6895024b71 onPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_5ecc91526b858aa674004e8b2470863d source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_5ecc91526b858aa674004e8b2470863d>
</_a9f5db056c7ecfa870770d6895024b71>
<_893f61fbd7fd9bc6f6014ecf4978dba onPress={() => props.navigation.navigate("GlossaryScreen")} id="back-btn">
<_4c2ace80f5aa30af4641ff838b86381d source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_4c2ace80f5aa30af4641ff838b86381d>
</_893f61fbd7fd9bc6f6014ecf4978dba>
</_763de7e24662732cd0f86dc196a983cc>
<_7135e052897e6073115ac0dc592c70ef id="glossary-area">
</_7135e052897e6073115ac0dc592c70ef>
</_a1dc2d700b12a80c75037135809e5603>
);
}

export default GlossarydescScreen;

