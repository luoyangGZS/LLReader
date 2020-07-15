package com.luoyang.llreader.dao;

import android.database.sqlite.SQLiteDatabase;

import com.luoyang.llreader.MApplication;

/**
 * package: com.luoyang.llreader.dao
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 * 创建一个Mapplication类，继承application中，这边获取Mapplication的实例
 * 完成DaoSession的初始化，避免以后重复初始化，便于使用
 */
public class DbHelper {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    //Application中进行初始化，创建表
    private DbHelper(){

        /** 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
         * 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
         * 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。*/

        mHelper = new DaoMaster.DevOpenHelper(MApplication.getInstance(), "llreader_db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    private static DbHelper instance;

    public static DbHelper getInstance(){
        if(null == instance){
            synchronized (DbHelper.class){
                if(null == instance){
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}

