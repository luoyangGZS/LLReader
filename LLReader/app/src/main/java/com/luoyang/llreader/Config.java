package com.luoyang.llreader;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * package: com.luoyang.llreader
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/8
 */
public class Config {

    private final static String SP_NAME = "config";

    private final static String FONT_TYPE_KEY = "fonttype";

    public final static String FONTTYPE_DEFAULT = "";
    public final static String FONTTYPE_QIHEI = "font/qihei.ttf";
    public final static String FONTTYPE_FZKATONG = "font/fzkatong.ttf";
    public final static String FONTTYPE_BYSONG = "font/bysong.ttf";

    public final static String FONTTYPE_HKSHAONV ="font/hkshaonv.ttf";
    public final static String FONTTYPE_HWZHONGSONG ="font/hwzhongsong.ttf";
    public final static String FONTTYPE_KAITI ="font/kaiti.ttf";
    public final static String FONTTYPE_YOUYUAN ="font/youyuan.ttf";

    private Context mContext;
    private static Config config;
    private SharedPreferences sp;
    //字体
   private Typeface typeface;

    private Config(Context mContext){
        this.mContext = mContext.getApplicationContext();
        sp = this.mContext.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }
    public static synchronized Config getInstance(){
        return config;
    }
//
    public static synchronized Config createConfig(Context context){
        if (config == null){
            config = new Config(context);
        }

        return config;
    }


    public Typeface getTypeface(){
        if (typeface == null) {
            String typePath = sp.getString(FONT_TYPE_KEY,FONTTYPE_QIHEI);
            typeface = getTypeface(typePath);
        }
        return typeface;
    }

    public String getTypefacePath(){
        String path = sp.getString(FONT_TYPE_KEY,FONTTYPE_QIHEI);
        return path;
    }

    public  Typeface getTypeface(String typeFacePath){
        Typeface mTypeface;
        if (typeFacePath.equals(FONTTYPE_DEFAULT)){
            mTypeface = Typeface.DEFAULT;
        }else{
            mTypeface = Typeface.createFromAsset(mContext.getAssets(),typeFacePath);
        }
        return mTypeface;
    }

    public void setTypeface(String typefacePath){
        typeface = getTypeface(typefacePath);
        sp.edit().putString(FONT_TYPE_KEY,typefacePath).commit();
    }
}
