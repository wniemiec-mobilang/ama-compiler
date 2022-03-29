import React, { useState } from 'react';
import { Platform, ScrollView, useWindowDimensions, ActivityIndicator } from 'react-native';
import { WebView } from 'react-native-webview';
import IframeRenderer, {iframeModel} from '@native-html/iframe-plugin';
import RenderHTML from 'react-native-render-html';

const App = () => {
  const [content, setContent] = useState(Platform.OS === 'ios' ? './assets/home.html' : 'file:///android_asset/home.html');

  const renderers = {
    iframe: IframeRenderer,
  };

  const customHTMLElementModels = {
    iframe: iframeModel,
  };

  const {width, height} = useWindowDimensions();

  var html = `
    <iframe allowfullscreen style="width:${width}px; height: ${height}px" src='${content}'></iframe>
  `;

  const renderProps = {
    a: {
      onPress: (_, href) => {
        setContent('');
        setContent(href);
      }
    },
    iframe: {
      scalesPageToFit: true,
      webViewProps: webViewProps,
    }
  };

  const webViewProps = {
    originWhitelist: '*',
    javaScriptCanOpenWindowsAutomatically: true,
    allowFileAccessFromFileURLs: true,
    allowFileAccess: true,
    allowUniversalAccessFromFileURLs: true,
    allowingReadAccessToURL: true,
  };

  return (
    <ScrollView>
      <RenderHTML
        contentWidth={height * 2}
        renderers={renderers}
        customHTMLElementModels={customHTMLElementModels}
        source={{html: html}}
        WebView={WebView}
        defaultWebViewProps={webViewProps}
        renderersProps={renderProps}
      />
      </ScrollView>
  );
}

export default App;

