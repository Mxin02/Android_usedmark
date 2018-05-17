package com.example.myapplication.Acitivity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;

public class NewDetaiActivity extends AppCompatActivity {

    private ProgressBar mPb;
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detai);

        mPb = (ProgressBar) findViewById(R.id.activity_news_detail_pb);
        mWebview = (WebView) findViewById(R.id.activity_news_detail_webview);
        initWebView();
    }

    private void initWebView() {
        // TODO Auto-generated method stub
        String url = getIntent().getStringExtra("url");
        if (url != null) {
            // 加载url
            mWebview.loadUrl(url);
        } else {
            Toast.makeText(this, "加载失败", 0).show();
        }

        mPb.setVisibility(View.GONE);
        // 得到webview设置的对象
        WebSettings settings = mWebview.getSettings();
        // 设置webview可以点击放大和缩小
        settings.setUseWideViewPort(true);
        // 设置内置的按钮
        settings.setBuiltInZoomControls(true);
        // mWebview设置支持js
        settings.setJavaScriptEnabled(true);
        //
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                mPb.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                mPb.setVisibility(View.INVISIBLE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                mPb.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        });

    }

    public void back(View v) {
        NewDetaiActivity.this.finish();
    }
}
