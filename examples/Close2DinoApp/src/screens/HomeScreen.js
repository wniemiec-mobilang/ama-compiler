import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _5ac36bfee420c6e251722049d69448b0 = styled.View`background-color: #444;margin: 0;`;

const _f3e4a2ee30ee81e9a49a83bbbf243ca9 = styled.View``;

const _82e4db17e9625e2f60aef2c6ca8b137d = styled.TouchableOpacity``;

const _f22d3f135c48ebec985ac1ee04474ec6 = styled.Text``;

const _e9e7e9981f78257a315701469f08d167 = styled.View`background-color: #ccc;width: 100%;align-items: center;display: flex;height: 50px;`;

const _4da54aceb9faa06491e89d9e0ac496ba = styled.TouchableOpacity`border: 0;background-color: transparent;cursor: pointer;margin: 0 15px;`;

const _5a4ffde35bb44e21160e5d2f6221d611 = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_5ac36bfee420c6e251722049d69448b0>
<_e9e7e9981f78257a315701469f08d167 id="status-bar">
<_4da54aceb9faa06491e89d9e0ac496ba OnPress={() => props.route.params.query="mobilang:screen:home"} id="menu-btn">
<_5a4ffde35bb44e21160e5d2f6221d611 source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_5a4ffde35bb44e21160e5d2f6221d611>
</_4da54aceb9faa06491e89d9e0ac496ba>
</_e9e7e9981f78257a315701469f08d167>
<_f3e4a2ee30ee81e9a49a83bbbf243ca9 id="items">
<_82e4db17e9625e2f60aef2c6ca8b137d onPress="() => props.route.params.query = 'mobilang:screen:glossary')">
<_f22d3f135c48ebec985ac1ee04474ec6>
Glossário
</_f22d3f135c48ebec985ac1ee04474ec6>
</_82e4db17e9625e2f60aef2c6ca8b137d>
</_f3e4a2ee30ee81e9a49a83bbbf243ca9>
</_5ac36bfee420c6e251722049d69448b0>
);
}

export default HomeScreen;

