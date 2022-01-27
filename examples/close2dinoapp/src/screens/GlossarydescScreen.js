import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const 35e2b2ba92b8c2423817e3bebd2f90b1 = styled.View``;

const d91b0c4fa91e5c6ebf03c37972c1a587 = styled.View``;

const a6d2a4a4d7dc773f470a6e355c34c77e = styled.View``;

const 6f1456c7fadc2b098541dc5740f76bca = styled.TouchableOpacity``;

const 3da122f7d595baba642535debc175b11 = styled.Image``;

const e58efb42fd03941e714206f657bb4d13 = styled.TouchableOpacity``;

const 999effad0e96e862770d90c7c7a2a57c = styled.Image``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[];
_glossary.push(<><View id=glossary><View class=header><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>);
setglossary(_glossary);
}, []);

return (
<35e2b2ba92b8c2423817e3bebd2f90b1>
<a6d2a4a4d7dc773f470a6e355c34c77e id=status-bar>
<e58efb42fd03941e714206f657bb4d13 id=menu-btn .onclick=() >
<999effad0e96e862770d90c7c7a2a57c source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</999effad0e96e862770d90c7c7a2a57c>
</e58efb42fd03941e714206f657bb4d13>
<6f1456c7fadc2b098541dc5740f76bca id=back-btn .onclick=() >
<3da122f7d595baba642535debc175b11 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</3da122f7d595baba642535debc175b11>
</6f1456c7fadc2b098541dc5740f76bca>
</a6d2a4a4d7dc773f470a6e355c34c77e>
<d91b0c4fa91e5c6ebf03c37972c1a587 id=glossary>
</d91b0c4fa91e5c6ebf03c37972c1a587>
</35e2b2ba92b8c2423817e3bebd2f90b1>
);
}

export default GlossarydescScreen;

