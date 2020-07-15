package com.luoyang.mvprxjava.impl;


import com.luoyang.mvprxjava.IPresenter;
import com.luoyang.mvprxjava.IView;

/**
 * package: com.luoyang.mvprxjava.impl
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/3
 */
public abstract class BasePresenter<  V extends IView>  implements IPresenter {

    /**
     * 使用弱引用来防止内存泄漏
     */
    protected V mView;

    @Override
    public void attachView( IView iView) {
        mView = (V) iView;
    }

}
