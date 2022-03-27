import React from 'react';
import { Platform, ScrollView, useWindowDimensions } from 'react-native';
import { WebView } from 'react-native-webview';
import IframeRenderer, {iframeModel} from '@native-html/iframe-plugin';
import RenderHTML from 'react-native-render-html';

const App = () => {

  const renderers = {
    iframe: IframeRenderer,
  };

  const customHTMLElementModels = {
    iframe: iframeModel,
  };

const {width, height} = useWindowDimensions();

  const homeUrl = Platform.OS === 'ios'
    ? './assets/HomeScreen.html'
    : 'file:///android_asset/HomeScreen.html';

  const html = `
    <iframe allowfullscreen style="width:${width}px; height: ${height}px" src='${homeUrl}'></iframe>
  `

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
        />
      </ScrollView>
  );
}

export default App;

