package com.enja.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.net.URL;

public class QRCodeFoundActivity extends AppCompatActivity {

    TextView tvQR;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_found);

        String qrResult = getIntent().getStringExtra("qr");

        tvQR=findViewById(R.id.tvQR);

        webView=findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

//        webView.setWebViewClient(new WebViewClient(){       //for redirecting purposes
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////                view.loadUrl(request.toString());
//                return false;
//            }
//        });

        if(isValidURL(qrResult)){
            tvQR.setVisibility(View.GONE);
            webView.loadUrl(qrResult);
        }
        else{
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
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}