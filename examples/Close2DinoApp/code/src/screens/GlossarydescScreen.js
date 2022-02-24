import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _19f3a4f261c6089aa89b38f6b15bacd = styled.View`background-color: #222;margin: 0;`;

const _ae944b27975e80be7d0cbcd6badefa4e = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _f45da902661b20a1fa900cd6518b3ef5 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _b97aa000121085122c22c90df9733837 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _1eca27f207dc3404c9418f118577434b = styled.Image``;

const _fc6e8df14c2abe2252f2e6c19cd917ed = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _1b2ae08263af5147c36f8a42b5ddbab = styled.Image``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id="glossary"><View class="header"><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary(_glossary);
}, []);

return (
<_19f3a4f261c6089aa89b38f6b15bacd>
<_f45da902661b20a1fa900cd6518b3ef5 id="status-bar">
<_fc6e8df14c2abe2252f2e6c19cd917ed OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_1b2ae08263af5147c36f8a42b5ddbab source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_1b2ae08263af5147c36f8a42b5ddbab>
</_fc6e8df14c2abe2252f2e6c19cd917ed>
<_b97aa000121085122c22c90df9733837 OnPress={() => props.route.params.query="mobilang:screen:glossary"} id="back-btn">
<_1eca27f207dc3404c9418f118577434b source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_1eca27f207dc3404c9418f118577434b>
</_b97aa000121085122c22c90df9733837>
</_f45da902661b20a1fa900cd6518b3ef5>
<_ae944b27975e80be7d0cbcd6badefa4e id="glossary">
</_ae944b27975e80be7d0cbcd6badefa4e>
</_19f3a4f261c6089aa89b38f6b15bacd>
);
}

export default GlossarydescScreen;

