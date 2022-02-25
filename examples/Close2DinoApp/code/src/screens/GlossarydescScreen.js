import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _4a1e07e544ff4e859eca7b8b8e2e0e1e = styled.View`background-color: #222;margin: 0;`;

const _548df99840c6fd2f231d09528fe8554c = styled.View`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const _b9c2053e9719673aa04cc00a1acfa4bc = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _662364144048a14cf5b5e54f26312395 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _3b3e02b3605abae7b6947a71ad38fe6b = styled.Image``;

const _4dc90655e9f6c749ab089cc54c31d6d1 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _6cc56527916e2e57d663404553ca7249 = styled.Image``;


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
<_4a1e07e544ff4e859eca7b8b8e2e0e1e>
<_b9c2053e9719673aa04cc00a1acfa4bc id="status-bar">
<_4dc90655e9f6c749ab089cc54c31d6d1 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_6cc56527916e2e57d663404553ca7249 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_6cc56527916e2e57d663404553ca7249>
</_4dc90655e9f6c749ab089cc54c31d6d1>
<_662364144048a14cf5b5e54f26312395 OnPress={() => props.route.params.query="mobilang:screen:glossary"} id="back-btn">
<_3b3e02b3605abae7b6947a71ad38fe6b source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_3b3e02b3605abae7b6947a71ad38fe6b>
</_662364144048a14cf5b5e54f26312395>
</_b9c2053e9719673aa04cc00a1acfa4bc>
<_548df99840c6fd2f231d09528fe8554c id="glossary">
</_548df99840c6fd2f231d09528fe8554c>
</_4a1e07e544ff4e859eca7b8b8e2e0e1e>
);
}

export default GlossarydescScreen;

