package com.enja.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.enja.qrscanner.Helper.MyWebClient;
import com.victor.loading.book.BookLoading;

import java.net.URL;

public class QRCodeFoundActivity extends AppCompatActivity {

    TextView tvQR;
    WebView webView;
    BookLoading progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_found);

        String qrResult = getIntent().getStringExtra("qr");

        initViews();
        configureWebView();
        checkQRResult(qrResult);
    }

    private void initViews(){
        tvQR=findViewById(R.id.tvQR);
        webView=findViewById(R.id.webView);
        progressView=findViewById(R.id.bookLoading);
        progressView.setVisibility(View.GONE);
    }

    private void configureWebView(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebClient(progressView));
    }

    private void checkQRResult(String qrResult){
        if(isValidURL(qrResult)){
            tvQR.setVisibility(View.GONE);
            webView.loadUrl(qrResult);
        }
        else{
            progressView.setVisibility(View.GONE);
            webView.setVisibility(View.GONE);
            tvQR.setText(""+qrResult);
        }
    }

    private boolean isValidURL(String s){
        //If we do not get exception while creating the java.net.URL object,
        // we return true,otherwise return false.
        try{
            new URL(s).toURI();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}