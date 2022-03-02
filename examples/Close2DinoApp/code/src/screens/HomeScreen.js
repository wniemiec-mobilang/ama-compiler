import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _4028ca357ba13e3cc29b26bb095d633e = styled.View`background-color: #444;margin: 0;`;

const _3013c288422a9c1f700140b25de59420 = styled.View``;

const _76363981c30d81b0f7b2d0d938ba0b88 = styled.TouchableOpacity``;

const _f125493ea1ec277decff2b6f197d9414 = styled.Text``;

const _ab98fc03319a01986065b91ec2c400f4 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _fd0f3e14b84a55879e7301606f415412 = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _29b3908d8865efd619f01cb50f027bb9 = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_4028ca357ba13e3cc29b26bb095d633e>
<_ab98fc03319a01986065b91ec2c400f4 id="status-bar">
<_fd0f3e14b84a55879e7301606f415412 OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_29b3908d8865efd619f01cb50f027bb9 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_29b3908d8865efd619f01cb50f027bb9>
</_fd0f3e14b84a55879e7301606f415412>
</_ab98fc03319a01986065b91ec2c400f4>
<_3013c288422a9c1f700140b25de59420 id="items">
<_76363981c30d81b0f7b2d0d938ba0b88 onPress="() => props.route.params.query = 'mobilang:screen:glossary')">
<_f125493ea1ec277decff2b6f197d9414>
Glossário
</_f125493ea1ec277decff2b6f197d9414>
</_76363981c30d81b0f7b2d0d938ba0b88>
</_3013c288422a9c1f700140b25de59420>
</_4028ca357ba13e3cc29b26bb095d633e>
);
}

export default HomeScreen;

