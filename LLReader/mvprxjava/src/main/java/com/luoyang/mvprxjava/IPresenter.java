package com.luoyang.mvprxjava;



/**
 * package: com.luoyang.mvprxjava
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/3
 */
public interface IPresenter {
    /**
     * 注入View，使之能够与View相互响应
     */
    void attachView( IView iView);

    /**
     * 释放资源，如果使用了网络请求 可以在此执行IModel.cancelRequest()
     */
    void detachView();
}

