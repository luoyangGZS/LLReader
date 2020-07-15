package com.luoyang.llreader.base.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * package: com.luoyang.llreader.base.observer
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public abstract class SimpleObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
