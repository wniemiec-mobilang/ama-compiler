import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _ec502e9b0184ee61d51bef556e8d150f = styled.View``;

const _dba107313da28e61f26bf90775d0396f = styled.View``;

const _34e64f8c95bf04cffea029738675115d = styled.TouchableOpacity``;

const _e7c911db53df20b271a84b1d764b7eb6 = styled.View``;

const _a83ab2b91b00d3953a5250b9ae3499a0 = styled.TouchableOpacity``;

const _39f737c02dce81dd528ca4642aff756b = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_ec502e9b0184ee61d51bef556e8d150f>
<_e7c911db53df20b271a84b1d764b7eb6 id='status-bar'>
<_a83ab2b91b00d3953a5250b9ae3499a0 onclick={() => props.route.params.query="mobilang:screen:home"} id='menu-btn'>
<_39f737c02dce81dd528ca4642aff756b source={{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}>
</_39f737c02dce81dd528ca4642aff756b>
</_a83ab2b91b00d3953a5250b9ae3499a0>
</_e7c911db53df20b271a84b1d764b7eb6>
<_dba107313da28e61f26bf90775d0396f id='items'>
<_34e64f8c95bf04cffea029738675115d onPress='() => redirectTo('mobilang:screen:glossary')'>
</_34e64f8c95bf04cffea029738675115d>
</_dba107313da28e61f26bf90775d0396f>
</_ec502e9b0184ee61d51bef556e8d150f>
);
}

export default HomeScreen;

