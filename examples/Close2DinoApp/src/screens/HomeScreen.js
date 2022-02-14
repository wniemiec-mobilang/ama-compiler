import React, { useEffect, useState } from 'react';
import styled from 'styled-components/native';


const _7cdb91f603cd89c8e94522c77a689ea0 = styled.View``;

const _c6ce0687c49f7d019ea23863fc860f6f = styled.View``;

const _1972d69aff9d81bd897cb2b28874b3 = styled.TouchableOpacity``;

const _54fc263ca9743c3141973637a6006ecb = styled.View``;

const _9131ac285f6de819bbd3c0ced98c33c4 = styled.TouchableOpacity``;

const _9c8534cf05f9dfb8421c1edaa68a6d92 = styled.Image``;


function HomeScreen(props) {


useEffect(() => {
}, []);

return (
<_7cdb91f603cd89c8e94522c77a689ea0>
<_54fc263ca9743c3141973637a6006ecb id='status-bar'>
<_9131ac285f6de819bbd3c0ced98c33c4 onclick='() => props.route.params.query="mobilang:screen:home"' id='menu-btn'>
<_9c8534cf05f9dfb8421c1edaa68a6d92 source='{{uri: 'https://cdn0.iconfinder.com/data/icons/heroicons-ui/24/icon-menu-512.png'}}'>
</_9c8534cf05f9dfb8421c1edaa68a6d92>
</_9131ac285f6de819bbd3c0ced98c33c4>
</_54fc263ca9743c3141973637a6006ecb>
<_c6ce0687c49f7d019ea23863fc860f6f id='items'>
<_1972d69aff9d81bd897cb2b28874b3 onPress='() => redirectTo('mobilang:screen:glossary')'>
</_1972d69aff9d81bd897cb2b28874b3>
</_c6ce0687c49f7d019ea23863fc860f6f>
</_7cdb91f603cd89c8e94522c77a689ea0>
);
}

export default HomeScreen;

