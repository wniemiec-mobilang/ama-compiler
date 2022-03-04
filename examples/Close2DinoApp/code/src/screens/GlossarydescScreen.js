import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _c8dff3703ecb11834c458337d2fbe171 = styled.SafeAreaView`background-color: #222;margin: 0;flex: 1;`;

const _9402a4ffd8c6f09a72b7a856e7625b1b = styled.View`margin: 10px;color: white;font-size: 15px;font-family: Arial, Helvetica, sans-serif;`;

const _8901a6dc67e2953d97040bf17607b749 = styled.View`background-color: #ccc;flex-direction: row;display: flex;width: 100%;align-items: center;height: 50px;`;

const _eef76a0903ab53e9f78b23d0dcdf26b7 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _446600af3b3250bab4212a2fbdeba492 = styled.Image`height: 25px;`;

const _99776cdd2552c468b6b29ee726a47471 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _edcc2bcc631d676750143c61ac88f3a9 = styled.Image`height: 25px;`;


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
<_c8dff3703ecb11834c458337d2fbe171>
<_8901a6dc67e2953d97040bf17607b749 id="status-bar">
<_99776cdd2552c468b6b29ee726a47471 OnPress={() => props.route.params.query="HomeScreen"} id="menu-btn">
<_edcc2bcc631d676750143c61ac88f3a9 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_edcc2bcc631d676750143c61ac88f3a9>
</_99776cdd2552c468b6b29ee726a47471>
<_eef76a0903ab53e9f78b23d0dcdf26b7 OnPress={() => props.route.params.query="GlossaryScreen"} id="back-btn">
<_446600af3b3250bab4212a2fbdeba492 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_446600af3b3250bab4212a2fbdeba492>
</_eef76a0903ab53e9f78b23d0dcdf26b7>
</_8901a6dc67e2953d97040bf17607b749>
<_9402a4ffd8c6f09a72b7a856e7625b1b id="glossary">
</_9402a4ffd8c6f09a72b7a856e7625b1b>
</_c8dff3703ecb11834c458337d2fbe171>
);
}

export default GlossarydescScreen;

