package com.zhjirui.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.zheng.webmanger.view.CusWebView;
import com.zheng.webmanger.manger.CusWebViewClient;
import com.zheng.webmanger.view.LoadView;
import com.zheng.webmanger.manger.WebViewManger;
import com.zhjirui.okhttp.base.BaseActivity;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
//http://blog.csdn.net/KevinsCSDN/article/details/52241334

public class MainActivity extends BaseActivity implements LoadView.IReloadLisenter{

    private WebViewManger webViewManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHeadLeftButtonVisibility(INVISIBLE);
        setTitle("喃呱客服");
        setColorBarAlpha(this, getResources().getColor(R.color.de_title_bg), 10);
        CusWebView cus_webview = (CusWebView) findViewById(R.id.cus_webview);
        LoadView loading_layout = (LoadView) findViewById(R.id.loading_layout);
        webViewManger = new WebViewManger(cus_webview);
//        webViewManger.setLoadUrl("https://www.baidu.com/");
//        webViewManger.setLoadUrl("http://192.168.1.14:5003/app/main/mobile.html");
//        http://www.layui.com/layim/demo/mobile.html
//        webViewManger.setLoadUrl("http://192.168.1.14:5003/examples/components/structure/chat.html");
//        webViewManger.setLoadUrl("file:///android_asset/index.html");
        webViewManger.setLoadUrl("http://192.168.3.236:5003/app/main/mobile.html");
        webViewManger.setWebViewClient(new CusWebViewClient(cus_webview));
        loading_layout.setOnReloadLisenter(this);
        webViewManger.setLoadingLayout(loading_layout);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(webViewManger.onKeyDown(keyCode)){
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onReloadLisenter() {
        webViewManger.setReloadUrl();
        Toast.makeText(this,"asdjf",Toast.LENGTH_SHORT).show();
    }
}
