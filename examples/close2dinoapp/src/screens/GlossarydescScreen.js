import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const 706d82759e3a3ead255ae8ad6d0ac413 = styled.View``;

const f7dbdc227b1c8583b9264a6eef2152d9 = styled.View``;

const d8619a64a25e5e3447bfc7953a8dbfe8 = styled.View``;

const 14d89e99e428e85cd06d612d92dbefa8 = styled.TouchableOpacity``;

const e12a75deb0de1c3e3996d8f95477d807 = styled.Image``;

const f536cb46dd52eb612ed30e57a4826055 = styled.TouchableOpacity``;

const 247ae5aff4f719b8ec1765be1b848adf = styled.Image``;


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
<706d82759e3a3ead255ae8ad6d0ac413>
<d8619a64a25e5e3447bfc7953a8dbfe8 id=status-bar>
<f536cb46dd52eb612ed30e57a4826055 id=menu-btn .onclick=() >
<247ae5aff4f719b8ec1765be1b848adf source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</247ae5aff4f719b8ec1765be1b848adf>
</f536cb46dd52eb612ed30e57a4826055>
<14d89e99e428e85cd06d612d92dbefa8 id=back-btn .onclick=() >
<e12a75deb0de1c3e3996d8f95477d807 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</e12a75deb0de1c3e3996d8f95477d807>
</14d89e99e428e85cd06d612d92dbefa8>
</d8619a64a25e5e3447bfc7953a8dbfe8>
<f7dbdc227b1c8583b9264a6eef2152d9 id=glossary>
</f7dbdc227b1c8583b9264a6eef2152d9>
</706d82759e3a3ead255ae8ad6d0ac413>
);
}

export default GlossarydescScreen;

