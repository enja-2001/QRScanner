package com.enja.qrscanner.Helper;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.victor.loading.book.BookLoading;

public class MyWebClient extends WebViewClient {
    private BookLoading progressView;

    public MyWebClient(BookLoading progressView) {
        this.progressView=progressView;
        progressView.setVisibility(View.VISIBLE);
        progressView.start();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progressView.stop();
        progressView.setVisibility(View.GONE);
    }
}