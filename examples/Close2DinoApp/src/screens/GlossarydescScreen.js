import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const f083ade6be8d76f4c68fd4768bd73b8f = styled.View``;

const 8ce9a6de6bdb21ae7bcfd2f2708c6acd = styled.View``;

const 4b45981c400e49fd27a45cde2ce8b3e6 = styled.View``;

const 84492baea874a397d582dea964bf622f = styled.TouchableOpacity``;

const 7d0dd94edb659fe68f22b30008738f49 = styled.Image``;

const e9403e3d25baa8d432cb4541bc1f53e5 = styled.TouchableOpacity``;

const 25085481b7ccaea2346514640e8768f = styled.Image``;


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
<f083ade6be8d76f4c68fd4768bd73b8f>
<4b45981c400e49fd27a45cde2ce8b3e6 id=status-bar>
<e9403e3d25baa8d432cb4541bc1f53e5 id=menu-btn .onclick=() >
<25085481b7ccaea2346514640e8768f source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</25085481b7ccaea2346514640e8768f>
</e9403e3d25baa8d432cb4541bc1f53e5>
<84492baea874a397d582dea964bf622f id=back-btn .onclick=() >
<7d0dd94edb659fe68f22b30008738f49 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</7d0dd94edb659fe68f22b30008738f49>
</84492baea874a397d582dea964bf622f>
</4b45981c400e49fd27a45cde2ce8b3e6>
<8ce9a6de6bdb21ae7bcfd2f2708c6acd id=glossary>
</8ce9a6de6bdb21ae7bcfd2f2708c6acd>
</f083ade6be8d76f4c68fd4768bd73b8f>
);
}

export default GlossarydescScreen;

