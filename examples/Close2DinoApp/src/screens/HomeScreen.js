import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _502f89c1db15e4debb613d8a0cd9c517 = styled.View`background-color: #444;margin: 0;`;

const _11ef2023fcaf967bbf7df7a966444415 = styled.View``;

const _deea487e7b71260c3b6cd6a883ff51 = styled.TouchableOpacity``;

const _d3f5d3da6b690c67e19db2a9c14a191e = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _4f409457acdde4542e43a8f4b1d3374e = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _55878cc494a7fe4d06377c52562c4b17 = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_502f89c1db15e4debb613d8a0cd9c517>
<_d3f5d3da6b690c67e19db2a9c14a191e id="status-bar">
<_4f409457acdde4542e43a8f4b1d3374e onclick={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_55878cc494a7fe4d06377c52562c4b17 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_55878cc494a7fe4d06377c52562c4b17>
</_4f409457acdde4542e43a8f4b1d3374e>
</_d3f5d3da6b690c67e19db2a9c14a191e>
<_11ef2023fcaf967bbf7df7a966444415 id="items">
<_deea487e7b71260c3b6cd6a883ff51 onPress="() => redirectTo('mobilang:screen:glossary')">
</_deea487e7b71260c3b6cd6a883ff51>
</_11ef2023fcaf967bbf7df7a966444415>
</_502f89c1db15e4debb613d8a0cd9c517>
);
}

export default HomeScreen;

