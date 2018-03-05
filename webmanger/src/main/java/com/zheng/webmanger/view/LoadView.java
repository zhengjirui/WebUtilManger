package com.zheng.webmanger.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.zheng.webmanger.R;

/**
 * Created by 18513 on 2018/1/4.
 */

public class LoadView extends RelativeLayout {

    private View loading_layout;
    private View error_layout;
    private View loading_progress;
    private IReloadLisenter iReloadLisenter;
    private View loading_bg;

    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        loading_layout = LayoutInflater.from(getContext()).inflate(R.layout.loading_layout, null);
        error_layout = loading_layout.findViewById(R.id.error_layout);
        loading_progress = loading_layout.findViewById(R.id.loading_progress);
        loading_bg = loading_layout.findViewById(R.id.loading_bg);
        addView(loading_layout);
        error_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iReloadLisenter.onReloadLisenter();
            }
        });
    }

    public void onStart() {
        setVisibility(VISIBLE);
        error_layout.setVisibility(GONE);
        loading_progress.setVisibility(VISIBLE);
        loading_bg.setVisibility(VISIBLE);
    }

    public void onFinish() {
        setVisibility(GONE);
        error_layout.setVisibility(GONE);
        loading_bg.setVisibility(GONE);
        loading_progress.setVisibility(GONE);

    }

    public void onError() {
        setVisibility(VISIBLE);
        error_layout.setVisibility(VISIBLE);
        loading_bg.setVisibility(GONE);
        loading_progress.setVisibility(GONE);
    }

    public void setOnReloadLisenter(IReloadLisenter iReloadLisenter) {
        this.iReloadLisenter = iReloadLisenter;
    }

    public interface IReloadLisenter {
        void onReloadLisenter();
    }
}
