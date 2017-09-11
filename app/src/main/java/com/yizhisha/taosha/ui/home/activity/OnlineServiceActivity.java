package com.yizhisha.taosha.ui.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;

public class OnlineServiceActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.webview)
    WebView webView;
    @Bind(R.id.pb_progress)
    ProgressBar pbProgress;
    private int count;
    private String path = "http://cs.ecqun.com/mobile/rand?id=127888&scheme=3";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_service;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(OnlineServiceActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        WebSettings webSettings=webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(path);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    pbProgress.setVisibility(View.GONE);
                } else {
                    // 加载中
                    pbProgress.setVisibility(View.VISIBLE);
                    pbProgress.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               /* Log.d("TTT","资质"+url);

                    if(url.equals("http://cs.ecqun.com/mobile/?id=626376&handle=talk&scheme=3")&&count>0){

                        finish_Activity(OnlineServiceActivity.this);
                        return true;
                    }
                count++;*/
                return false;
            }
        });

    }
}
