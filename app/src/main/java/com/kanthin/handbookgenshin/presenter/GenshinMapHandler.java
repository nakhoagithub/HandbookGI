package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class GenshinMapHandler {
    private Context context;

    public GenshinMapHandler(Context context) {
        this.context = context;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void handlerGenshinMap(WebView genshinMap, LinearProgressIndicator progressIndicator){
        String url = "https://webstatic-sea.mihoyo.com/";

        WebSettings webSettings = genshinMap.getSettings();

        genshinMap.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                progressIndicator.setVisibility(View.VISIBLE);
                progressIndicator.setProgressCompat(progress*100, true); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    progressIndicator.setVisibility(View.GONE);
            }
        });

        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        genshinMap.setInitialScale(1);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true);

        genshinMap.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        genshinMap.loadUrl(url);

        genshinMap.setWebViewClient(new WebViewClient());
    }
}
