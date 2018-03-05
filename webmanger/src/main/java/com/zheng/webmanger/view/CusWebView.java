package com.zheng.webmanger.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;

import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 * Created by 18513 on 2017/12/28.
 * http://blog.csdn.net/KevinsCSDN/article/details/52241334  配置详解
 */

public class CusWebView extends BridgeWebView {

    private WebSettings webSettings;

    public CusWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CusWebView(Context context) {
        super(context);
        init();
    }

    /**
     * 初始化操作
     */
    private void init(){
        webSettings = this.getSettings();
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。


        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
//        webSettings.setAllowFileAccessFromFileURLs(true);
//        webSettings.setPluginState(WebSettings.PluginState.ON);

//        webSettings.setSupportMultipleWindows (true);//设置WebView是否支持多窗口。如果设置为true，主程序要实现onCreateWindow(WebView, boolean, boolean, Message)，默认false。
//        webSettings.supportMultipleWindows() ;
//        webSettings.setLoadWithOverviewMode(true) ;//是否允许WebView度超出以概览的方式载入页面，默认false。即缩小内容以适应屏幕宽度。该项设置在内容宽度超出WebView控件的宽度时生效，例如当getUseWideViewPort() 返回true时。

//        webSettings.setJavaScriptEnabled(true);//设置WebView是否允许执行JavaScript脚本，默认false，不允许。
//        webSettings.setBuiltInZoomControls (true);//是否使用内置的缩放机制。内置的缩放机制包括屏幕上的缩放控件（浮于WebView内容之上）和缩放手势的运用。通过setDisplayZoomControls(boolean)可以控制是否显示这些控件，默认值为false。
//        webSettings.setJavaScriptCanOpenWindowsAutomatically (true);//让JavaScript自动打开窗口，默认false。适用于JavaScript方法window.open()。

//        webSettings.setLayoutAlgorithm (WebSettings.LayoutAlgorithm.NARROW_COLUMNS) ;//




    }

}
