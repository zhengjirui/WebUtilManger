package com.zheng.webmanger.manger;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.zheng.webmanger.view.AbsWebView;
import com.zheng.webmanger.view.LoadView;

/**
 * Created by 18513 on 2017/12/28.
 * http://blog.csdn.net/barryhappy/article/details/52733406
 */

public class CusWebViewClient extends BridgeWebViewClient {

    private BridgeWebView cusWebView;
    private AbsWebView absWebView;
    private LoadView loadView;
    private boolean errFilter = true;//当网页加载错误时，不走finish方法
    private boolean requestState = true; //标志一次请求完成才能有下次请求，true标志一次请求完成

    public CusWebViewClient(BridgeWebView cusWebView) {
        this(cusWebView,null);
    }

    public CusWebViewClient(BridgeWebView cusWebView, AbsWebView absWebView) {
        super(cusWebView);
        this.cusWebView = cusWebView;
        this.absWebView = absWebView;
        initWebChromeClient();
    }

    private void initWebChromeClient() {
        this.cusWebView.setWebChromeClient(new WebChromeClient() {

            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if(absWebView != null)
                absWebView.onReceivedTitle(title);
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    Log.e("----","进度" + newProgress);
                    if (absWebView != null)
                        absWebView.onProgressChanged(newProgress);
                } else if (newProgress == 100) {
                    if (absWebView != null)
                        absWebView.onProgressChanged(newProgress);
                }
            }
        });
    }

    public void setLoadLayout(LoadView loadView) {
        this.loadView = loadView;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.e("----","开始");
        if(requestState){
            requestState = false;
            errFilter = true;
            if (absWebView != null)
                absWebView.onPageStarted();
            if (loadView != null)
                loadView.onStart();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.e("----","结束");
        if(!requestState){
            requestState = true;
            if (absWebView != null)
                absWebView.onPageFinished();
            if (loadView != null && errFilter)
                loadView.onFinish();
        }

    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        Log.e("----","错误");
        if(!requestState){
            requestState = true;
            errFilter = false;
            if (absWebView != null){
                absWebView.onReceivedError();
                absWebView.onPageFinished();
            }

            if (loadView != null)
                loadView.onError();
            Log.e("----------", errorCode + "");
        }

    }
}
