import React, { useEffect, useState } from 'react';
import {View, Text, TouchableOpacity} from 'react-native'
import styled from 'styled-components/native';


const _7c2dcb2e4a6a3d4938abb689469ba5c = styled.SafeAreaView`
background-color: #444;
margin: 0;
flex: 1;
`;

const _6701f09671b1612ff8e0fce3d8e511ef = styled.View`
margin: 10px;
color: white;
font-size: 15px;
`;

const _cc29668eb0ec35c5519672537468b529 = styled.View`
background-color: #222;
`;

const _210f3cf01d2de2152eb25aab6c68777f = styled.Text`
margin: 20px 0;
color: white;
`;

const _971933311f5dc8e09c41dd945d4fbd5a = styled.View`
margin: 10px;
`;

const _fbb07580939d24e653bbca5d493c0c11 = styled.TextInput`
border: 1px solid #ccc;
border-radius: 10px;
padding-left: 20px;
width: 100%;
font-size: 20px;
height: 50px;
`;

const _962077c886230bb647085f3e8bdfaf16 = styled.View`
background-color: #ccc;
flex-direction: row;
display: flex;
width: 100%;
align-items: center;
height: 50px;
`;

const _1a7794ac16128c5567632beb306b7b81 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _3f5777c3a704a9e2470ba2bf957e7359 = styled.Image`
width: 25px;
height: 25px;
`;

const _c72eb046653f64698176a3a2624601b9 = styled.TouchableOpacity`
border: 0;
background-color: transparent;
cursor: pointer;
margin: 0 15px;
`;

const _39a520e76be3d8ff7a7477dc3efc387f = styled.Image`
width: 25px;
height: 25px;
`;


function GlossaryScreen(props) {

[glossary_content,setGlossary_content] = useState([]);
[glossaryContent,setGlossarycontent] = useState([]);

useEffect(() => {
function openDescription(item) {;
item.children[1].classList.toggle("item-content-open");
};
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
let _glossaryContent=[];
function loadGlossaryWithName(name="") {;
_glossaryContent=[];
for (let item of data) {;
if (item.name.toLowerCase().startsWith(name.toLowerCase())) {;
_glossaryContent.push(<><View class="item"><Text>${item.name}</Text><View class="item-content"><Text>${item.type}</Text><Text>${item.description}</Text><TouchableOpacity onPress={() => props.navigation.navigate('mobilang:screen:glossary-desc,{id:${item.id}}')}><Text>Leia mais</Text></TouchableOpacity></View></View></>);
};
};
//makeGlossaryItemsClickable();
};
loadGlossaryWithName();
//const searchInput=document.getElementById("search").children[0];
//searchInput.addEventListener("keyup",(event) => {;
//loadGlossaryWithName(searchInput.value);
//});
//setGlossary_content(_glossary_content);
setGlossarycontent(_glossaryContent);
}, []);

return (
<_7c2dcb2e4a6a3d4938abb689469ba5c>
<_962077c886230bb647085f3e8bdfaf16 id="status-bar">
<_c72eb046653f64698176a3a2624601b9 onPress={() => props.navigation.navigate("HomeScreen")} id="menu-btn">
<_39a520e76be3d8ff7a7477dc3efc387f source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_39a520e76be3d8ff7a7477dc3efc387f>
</_c72eb046653f64698176a3a2624601b9>
<_1a7794ac16128c5567632beb306b7b81 onPress={() => props.navigation.navigate("HomeScreen")} id="back-btn">
<_3f5777c3a704a9e2470ba2bf957e7359 source={{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}>
</_3f5777c3a704a9e2470ba2bf957e7359>
</_1a7794ac16128c5567632beb306b7b81>
</_962077c886230bb647085f3e8bdfaf16>
<_971933311f5dc8e09c41dd945d4fbd5a id="search">
<_fbb07580939d24e653bbca5d493c0c11 background-color="white" placeholder="Buscar..." type="text">
</_fbb07580939d24e653bbca5d493c0c11>
</_971933311f5dc8e09c41dd945d4fbd5a>
<_6701f09671b1612ff8e0fce3d8e511ef id="glossary">
<_210f3cf01d2de2152eb25aab6c68777f>
Glossário
</_210f3cf01d2de2152eb25aab6c68777f>
<_cc29668eb0ec35c5519672537468b529 id="glossary-content">
    {glossaryContent}
</_cc29668eb0ec35c5519672537468b529>
</_6701f09671b1612ff8e0fce3d8e511ef>
</_7c2dcb2e4a6a3d4938abb689469ba5c>
);
}

export default GlossaryScreen;

