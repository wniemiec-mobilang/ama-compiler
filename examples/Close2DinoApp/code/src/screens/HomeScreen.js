import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _8df42af27152173daebc1b4867f90171 = styled.View`background-color: #444;margin: 0;`;

const _ba40559e079dd4ca545b7a837ab157d7 = styled.View``;

const _61231bd1887a7838aa8562a0f5649fbe = styled.TouchableOpacity``;

const _7dd71606cf3c175f14656e1c1a2769cb = styled.Text``;

const _1387ef8b2ddebda7b6acf24d4bb9cebf = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _73ee1028e32699b95805006a56b05d3e = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _94504774b407dafa1e9621f88e4eac66 = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_8df42af27152173daebc1b4867f90171>
<_1387ef8b2ddebda7b6acf24d4bb9cebf id="status-bar">
<_73ee1028e32699b95805006a56b05d3e OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_94504774b407dafa1e9621f88e4eac66 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_94504774b407dafa1e9621f88e4eac66>
</_73ee1028e32699b95805006a56b05d3e>
</_1387ef8b2ddebda7b6acf24d4bb9cebf>
<_ba40559e079dd4ca545b7a837ab157d7 id="items">
<_61231bd1887a7838aa8562a0f5649fbe onPress="() => props.route.params.query = 'mobilang:screen:glossary')">
<_7dd71606cf3c175f14656e1c1a2769cb>
Glossário
</_7dd71606cf3c175f14656e1c1a2769cb>
</_61231bd1887a7838aa8562a0f5649fbe>
</_ba40559e079dd4ca545b7a837ab157d7>
</_8df42af27152173daebc1b4867f90171>
);
}

export default HomeScreen;

