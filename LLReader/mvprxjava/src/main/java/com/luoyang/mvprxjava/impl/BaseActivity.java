package com.luoyang.mvprxjava.impl;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;

import com.luoyang.mvprxjava.AppActivityManager;
import com.luoyang.mvprxjava.IPresenter;
import com.luoyang.mvprxjava.IView;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;


/**
 * package: com.luoyang.mvprxjava.impl
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/3
 */
public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IView {
    public final static String start_share_ele= "start_with_share_ele";
    protected Bundle savedInstanceState;
    protected P mPresenter;
    private Boolean startShareAnim = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        if(getIntent()!=null){
            startShareAnim = getIntent().getBooleanExtra(start_share_ele,false);
        }
        AppActivityManager.getInstance().add(this);
        initSDK();
        onCreateActivity();
        mPresenter = initInjector();
        attachView();
        initData();
        bindView();
        bindEvent();
        firstRequest();
    }

    /**
     * 首次逻辑操作
     */
    protected void firstRequest() {

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
     * P层绑定V层
     */
    private void attachView() {
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    /**
     * P层解绑V层
     */
    private void detachView() {
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    /**
     * SDK初始化
     */
    protected void initSDK() {

    }

    /**
     * P层绑定   若无则返回null;
     *
     * @return
     */
    protected abstract P initInjector();

    /**
     * 布局载入  setContentView()
     */
    protected abstract void onCreateActivity();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
        AppActivityManager.getInstance().remove(this);
    }


    ////////////////////////////////启动Activity转场动画/////////////////////////////////////////////

    protected void startActivityForResultByAnim(Intent intent, int requestCode, int animIn, int animExit) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(animIn, animExit);
    }

    protected void startActivityByAnim(Intent intent, int animIn, int animExit) {
        startActivity(intent);
        overridePendingTransition(animIn, animExit);
    }

    protected void startActivityForResultByAnim(Intent intent, int requestCode, View view,  String transitionName, int animIn, int animExit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivityForResult(intent, requestCode, ActivityOptions.makeSceneTransitionAnimation(this, view, transitionName).toBundle());
        } else {
            startActivityForResultByAnim(intent, requestCode, animIn, animExit);
        }
    }

    protected void startActivityByAnim(Intent intent, View view,  String transitionName, int animIn, int animExit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.putExtra(start_share_ele,true);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, view, transitionName).toBundle());
        } else {
            startActivityByAnim(intent, animIn, animExit);
        }
    }

    public Context getContext(){
        return this;
    }

    public Boolean getStart_share_ele() {
        return startShareAnim;
    }

}
