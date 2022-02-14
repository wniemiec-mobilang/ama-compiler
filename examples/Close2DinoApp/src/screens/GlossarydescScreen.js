import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _f568283ecc79f0c616dcd914bbbbace8 = styled.View``;

const _edd1895072ce70a3d423472d1f8788ac = styled.View``;

const _1af70016a9bb6a08229f9fe7db7f1dc5 = styled.View``;

const _aeae18ee6eba5410ce9762be07bb3ed = styled.TouchableOpacity``;

const _302249350ae61fcdeb1fbc7798765fda = styled.Image``;

const _c78303ff7ef3cc7fc6608fcdd82eb832 = styled.TouchableOpacity``;

const _61220f71ec80ccc6816292059ca03a31 = styled.Image``;


function GlossarydescScreen(props) {

[glossary,setGlossary] = useState([]);

useEffect(() => {
const data=[{name: "Adenomegalia",description: "Linfonodos ou gânglios aumentados, também conhecidos como ínguas.",id: "1",type: "Condição",content: "Adenomegalia é o aumento dos linfonodos (ínguas). Pode estar presente em crianças e, na maior parte dos casos, é causada por infecções virais. Mais raramente podem ser causadas por doenças oncológicas tais como leucemias ou linfomas."},{name: "Alopécia",description: "Queda de cabelos",id: "2",type: "Condição",content: "A alopécia é a perda de cabelos do couro cabeludo ou de qualquer outra região do corpo. Em crianças em tratamento oncológico (quimioterapia ou radioterapia) a queda do cabelo pode acontecer. Nestes casos, uma vez terminado o tratamento o cabelo volta a crescer."}];
const id=props.route.params.query.split("?")[1].split("=")[1];
const glossaryItem=data.find((item) => item.id==id);
let _glossary=[];
_glossary=[];
_glossary.push(<><View id='glossary'><View class='header'><Text>${glossaryItem.name}</Text><Text>${glossaryItem.type}</Text></View><Text>${glossaryItem.description}</Text></View></>);
setGlossary(_glossary);
}, []);

return (
<_f568283ecc79f0c616dcd914bbbbace8>
<_1af70016a9bb6a08229f9fe7db7f1dc5 id='status-bar'>
<_c78303ff7ef3cc7fc6608fcdd82eb832 onclick='() => props.route.params.query="mobilang:screen:home"' id='menu-btn'>
<_61220f71ec80ccc6816292059ca03a31 source='{{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}'>
</_61220f71ec80ccc6816292059ca03a31>
</_c78303ff7ef3cc7fc6608fcdd82eb832>
<_aeae18ee6eba5410ce9762be07bb3ed onclick='() => props.route.params.query="mobilang:screen:glossary"' id='back-btn'>
<_302249350ae61fcdeb1fbc7798765fda source='{{uri: 'http://cdn.onlinewebfonts.com/svg/img_259786.png'}}'>
</_302249350ae61fcdeb1fbc7798765fda>
</_aeae18ee6eba5410ce9762be07bb3ed>
</_1af70016a9bb6a08229f9fe7db7f1dc5>
<_edd1895072ce70a3d423472d1f8788ac id='glossary'>
</_edd1895072ce70a3d423472d1f8788ac>
</_f568283ecc79f0c616dcd914bbbbace8>
);
}

export default GlossarydescScreen;

