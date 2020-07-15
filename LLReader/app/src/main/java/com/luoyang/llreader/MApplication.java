package com.luoyang.llreader;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;

import com.luoyang.llreader.service.DownloadService;
import com.umeng.analytics.MobclickAgent;

/**
 * package: com.luoyang.llreader
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public class MApplication extends Application {

    private static MApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
//        if (BuildConfig.IS_RELEASE) {
//            String channel = "debug";
//            try {
//                ApplicationInfo appInfo = getPackageManager()
//                        .getApplicationInfo(getPackageName(),
//                                PackageManager.GET_META_DATA);
//                channel = appInfo.metaData.getString("UMENG_CHANNEL_VALUE");
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, getString(R.string.umeng_key), channel, MobclickAgent.EScenarioType.E_UM_NORMAL, true));
//        }
        instance = this;
        ReadBookControl.createReadBookControl(this);
        startService(new Intent(this, DownloadService.class));
    }

    public static MApplication getInstance() {
        return instance;
    }
}
