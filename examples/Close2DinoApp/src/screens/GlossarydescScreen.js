import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const 29b44272f6091cd2473443e4924639f0 = styled.View``;

const 25c825f01c8bf352435c44fa5245a2ec = styled.div`font-size: 15px;margin: 10px;font-family: Arial, Helvetica, sans-serif;color: white;`;

const a5c06c10a621816c824f2914a486dac4 = styled.div`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const 1af336723050ec46539c7c4360211edc = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const 87774edc329979096351934dbae77def = styled.img``;

const e32e9853855ed04d06676c38209c908c = styled.button`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const 73d54409064be73ca97c336e4c9cd45e = styled.img``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou g�nglios aumentados, tamb�m conhecidos como �nguas.",id: "1",type: "Condi��o",content: "Adenomegalia � o aumento dos linfonodos (�nguas). Pode estar presente em crian�as e, na maior parte dos casos, � causada por infec��es virais. Mais raramente podem ser causadas por doen�as oncol�gicas tais como leucemias ou linfomas."},{name: "Alop�cia",description: "Queda de cabelos",id: "2",type: "Condi��o",content: "A alop�cia � a perda de cabelos do couro cabeludo ou de qualquer outra regi�o do corpo. Em crian�as em tratamento oncol�gico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[];
_glossary.push(<><div id=glossary><div class=header><h1>${glossaryItem.name}</h1><h2>${glossaryItem.type}</h2></div><p>${glossaryItem.description}</p></div></>);
setglossary(_glossary);
}, []);

return (
<29b44272f6091cd2473443e4924639f0>
<a5c06c10a621816c824f2914a486dac4 id=status-bar>
<e32e9853855ed04d06676c38209c908c id=menu-btn .onclick=() >
<73d54409064be73ca97c336e4c9cd45e src=https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png height=25>
</73d54409064be73ca97c336e4c9cd45e>
</e32e9853855ed04d06676c38209c908c>
<1af336723050ec46539c7c4360211edc id=back-btn .onclick=() >
<87774edc329979096351934dbae77def src=http://cdn.onlinewebfonts.com/svg/img_259786.png height=25>
</87774edc329979096351934dbae77def>
</1af336723050ec46539c7c4360211edc>
</a5c06c10a621816c824f2914a486dac4>
<25c825f01c8bf352435c44fa5245a2ec id=glossary>
</25c825f01c8bf352435c44fa5245a2ec>
</29b44272f6091cd2473443e4924639f0>
);
}

export default GlossarydescScreen;

