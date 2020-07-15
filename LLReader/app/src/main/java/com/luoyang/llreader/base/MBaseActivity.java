package com.luoyang.llreader.base;


import android.graphics.Typeface;
import android.os.Bundle;

import com.luoyang.llreader.ReadBookControl;
import com.luoyang.mvprxjava.IPresenter;
import com.luoyang.mvprxjava.impl.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * package: com.luoyang.llreader.base
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/3
 */

public abstract class MBaseActivity<P extends IPresenter> extends BaseActivity<P> {

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }


    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

