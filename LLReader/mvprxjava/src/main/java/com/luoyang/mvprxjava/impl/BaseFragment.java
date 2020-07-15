package com.luoyang.mvprxjava.impl;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luoyang.mvprxjava.IPresenter;
import com.luoyang.mvprxjava.IView;
import com.trello.rxlifecycle3.components.RxFragment;

/**
 * package: com.luoyang.mvprxjava.impl
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/3
 */
public  abstract class BaseFragment< P extends IPresenter> extends RxFragment implements IView {

    protected View view;
    protected Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        initSDK();
        view = createView(inflater, container);
        initData();
        bindView();
        bindEvent();
        firstRequest();
        return view;
    }

    /**
     * 事件触发绑定
     */
    protected void bindEvent() {

    }

    /**
     * 控件绑定
     */
    protected void bindView() {

    }

    /**
     * 数据初始化
     */
    protected void initData() {

    }

    /**
     * 首次逻辑操作
     */
    protected void firstRequest() {

    }

    /**
     * 加载布局
     */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container);

    /**
     * 第三方SDK初始化
     */
    protected void initSDK() {

    }


}
