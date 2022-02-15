import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _bf82e14c146353002b55cac57bd3894d = styled.View`background-color: #222;margin: 0;`;

const _dcee0872fb24a3d8fd265386e61681fa = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _8c23993a9738882f648390f1e654f9dd = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _ac499023582117106a079bfce64b4174 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _e103c3e4c2f7fef701afa5c02891c778 = styled.Image``;

const _7324c9fe5589ebf4752ec4a2b64f4bbe = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _7d9d3e1bc3486e28fd43d6c64380fc = styled.Image``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou g�nglios aumentados, tamb�m conhecidos como �nguas.",id: "1",type: "Condi��o",content: "Adenomegalia � o aumento dos linfonodos (�nguas). Pode estar presente em crian�as e, na maior parte dos casos, � causada por infec��es virais. Mais raramente podem ser causadas por doen�as oncol�gicas tais como leucemias ou linfomas."},{name: "Alop�cia",description: "Queda de cabelos",id: "2",type: "Condi��o",content: "A alop�cia � a perda de cabelos do couro cabeludo ou de qualquer outra regi�o do corpo. Em crian�as em tratamento oncol�gico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[``];
_glossary.push(`<><View id="glossary"><View class="header"><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>`);
setGlossary(_glossary);
}, []);

return (
<_bf82e14c146353002b55cac57bd3894d>
<_8c23993a9738882f648390f1e654f9dd id="status-bar">
<_7324c9fe5589ebf4752ec4a2b64f4bbe onclick={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_7d9d3e1bc3486e28fd43d6c64380fc source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_7d9d3e1bc3486e28fd43d6c64380fc>
</_7324c9fe5589ebf4752ec4a2b64f4bbe>
<_ac499023582117106a079bfce64b4174 onclick={() => props.route.params.query="mobilang:screen:glossary"} id="back-btn">
<_e103c3e4c2f7fef701afa5c02891c778 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_e103c3e4c2f7fef701afa5c02891c778>
</_ac499023582117106a079bfce64b4174>
</_8c23993a9738882f648390f1e654f9dd>
<_dcee0872fb24a3d8fd265386e61681fa id="glossary">
</_dcee0872fb24a3d8fd265386e61681fa>
</_bf82e14c146353002b55cac57bd3894d>
);
}

export default GlossarydescScreen;

