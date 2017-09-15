package com.zenny.webviewjsbridge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//todo 利用window.onPrompt(): https://github.com/pedant/safe-java-js-webview-bridge
//todo 利用注入对象(同本Demo): https://github.com/lzyzsd/JsBridge
public class WebViewActivity extends Activity {

    private static final String URL = "file:///android_asset/index.html";
    private WebView mWebView;
    private ProgressDialog progress;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jswebview);
        showLoadingDialog();
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                dismissLoadingDialog();
            }
        });

        //Inject WebAppInterface methods into Web page by having Interface 'Android'
        //注入一个JSBridgePlugin对象用于引用写好的js方法函数
        mWebView.addJavascriptInterface(new JSBridge(this, mWebView), "JSBridgePlugin");
        refreshWebView();

    }

    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setTitle("JS Bridge");
            progress.setMessage("Loading Webview Content....");
        }
        progress.show();
    }

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }


    private void refreshWebView() {
        mWebView.loadUrl(URL);
    }


}

