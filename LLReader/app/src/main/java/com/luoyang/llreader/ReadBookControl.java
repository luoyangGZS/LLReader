package com.luoyang.llreader;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;

import com.luoyang.llreader.utils.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * package: com.luoyang.llreader
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/8
 */
public class ReadBookControl {
    public static final int DEFAULT_TEXT = 4;
    public static final int DEFAULT_BG = 0;
    public static final int DEFAULT_FONT_INDEX = 6;

    private static List<Map<String,Integer>> textKind;
    private static List<Map<String,Integer>> textDrawable;

    private int textSize;
    private int textExtra;
    private int textColor;
    private int textBackground;

    private int textKindIndex = DEFAULT_TEXT;
    private int textDrawableIndex = DEFAULT_BG;
    private int textFontsIndex = DEFAULT_FONT_INDEX;

    private Boolean canClickTurn = true;
    private Boolean canKeyTurn = true;

    private Context mContext;
    private SharedPreferences preference;
    //字体
    private Typeface typeface;

    private static ReadBookControl readBookControl;

    //    public static ReadBookControl getInstance(){
    //        if(readBookControl == null){
    //            synchronized (ReadBookControl.class){
    //                if(readBookControl == null){
    //                    readBookControl = new ReadBookControl();
    //                }
    //            }
    //        }
    //        return readBookControl;
    //    }

    //synchronized程序中不是有多个对象同时调用getInstance，在同一时刻只能有一个线程可以进入同步代码块内运行
     public static synchronized ReadBookControl getInstance(){
            return readBookControl;
        }

        public static synchronized ReadBookControl createReadBookControl(Context context){
            if(readBookControl == null){
                readBookControl = new ReadBookControl(context);
              }
            return readBookControl;
        }

    private ReadBookControl(Context context){
        this.mContext = context.getApplicationContext();
        if(null == textKind){
            textKind = new ArrayList<>();
            Map<String,Integer> temp1 = new HashMap<>();
            temp1.put("textSize", 14);
            temp1.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),5));
            textKind.add(temp1);

            Map<String,Integer> temp2 = new HashMap<>();
            temp2.put("textSize", 15);
            temp2.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),5));
            textKind.add(temp2);

            Map<String,Integer> temp3 = new HashMap<>();
            temp3.put("textSize", 16);
            temp3.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),5));
            textKind.add(temp3);

            Map<String,Integer> temp4 = new HashMap<>();
            temp4.put("textSize", 17);
            temp4.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),5));
            textKind.add(temp4);

            Map<String,Integer> temp5 = new HashMap<>();
            temp5.put("textSize", 18);
            temp5.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),5));
            textKind.add(temp5);

            Map<String,Integer> temp6 = new HashMap<>();
            temp6.put("textSize", 19);
            temp6.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),5));
            textKind.add(temp6);

            Map<String,Integer> temp7 = new HashMap<>();
            temp7.put("textSize", 20);
            temp7.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),5));
            textKind.add(temp7);

            Map<String,Integer> temp8 = new HashMap<>();
            temp8.put("textSize", 21);
            temp8.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),6));
            textKind.add(temp8);

            Map<String,Integer> temp9 = new HashMap<>();
            temp9.put("textSize", 23);
            temp9.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),6));
            textKind.add(temp9);

            Map<String,Integer> temp10 = new HashMap<>();
            temp10.put("textSize", 25);
            temp10.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),7));
            textKind.add(temp10);

            Map<String,Integer> temp11 = new HashMap<>();
            temp11.put("textSize", 27);
            temp11.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),7));
            textKind.add(temp11);

            Map<String,Integer> temp12 = new HashMap<>();
            temp12.put("textSize", 30);
            temp12.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),8));
            textKind.add(temp12);

            Map<String,Integer> temp13 = new HashMap<>();
            temp13.put("textSize", 33);
            temp13.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),9));
            textKind.add(temp13);

            Map<String,Integer> temp14 = new HashMap<>();
            temp14.put("textSize", 36);
            temp14.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),10));
            textKind.add(temp14);

            Map<String,Integer> temp15 = new HashMap<>();
            temp15.put("textSize", 45);
            temp15.put("textExtra", DensityUtil.dp2px(MApplication.getInstance(),10));
            textKind.add(temp15);
        }
        if(null == textDrawable){
            textDrawable = new ArrayList<>();
            Map<String,Integer> temp1 = new HashMap<>();
            temp1.put("textColor",Color.parseColor("#3E3D3B"));
            temp1.put("textBackground",R.drawable.read_bg_7);
            textDrawable.add(temp1);

            Map<String,Integer> temp2 = new HashMap<>();
            temp2.put("textColor",Color.parseColor("#5E432E"));
            temp2.put("textBackground",R.drawable.bg_readbook_yellow);
            textDrawable.add(temp2);

            Map<String,Integer> temp3 = new HashMap<>();
            temp3.put("textColor",Color.parseColor("#22482C"));
            temp3.put("textBackground",R.drawable.bg_readbook_green);
            textDrawable.add(temp3);

            Map<String,Integer> temp4 = new HashMap<>();
            temp4.put("textColor",Color.parseColor("#892643"));
            temp4.put("textBackground",R.drawable.read_bg_2);
            textDrawable.add(temp4);

            Map<String,Integer> temp5 = new HashMap<>();
            temp5.put("textColor",Color.parseColor("#989e8d"));
            temp5.put("textBackground",R.drawable.shape_bg_readbook_bule1);
            textDrawable.add(temp5);

            Map<String,Integer> temp6 = new HashMap<>();
            temp6.put("textColor",Color.parseColor("#1f4556"));
            temp6.put("textBackground",R.drawable.shape_bg_readbook_bule2);
            textDrawable.add(temp6);


            Map<String,Integer> temp7 = new HashMap<>();
            temp7.put("textColor",Color.parseColor("#808080"));
            temp7.put("textBackground",R.drawable.bg_readbook_black);
            textDrawable.add(temp7);


        }
        preference = MApplication.getInstance().getSharedPreferences("CONFIG", 0);
        this.textKindIndex = preference.getInt("textKindIndex",DEFAULT_TEXT);
        this.textSize = textKind.get(textKindIndex).get("textSize");
        this.textExtra = textKind.get(textKindIndex).get("textExtra");
        this.textDrawableIndex = preference.getInt("textDrawableIndex",DEFAULT_BG);
        this.textColor = textDrawable.get(textDrawableIndex).get("textColor");
        this.textBackground = textDrawable.get(textDrawableIndex).get("textBackground");

        this.canClickTurn = preference.getBoolean("canClickTurn",true);
        this.canKeyTurn = preference.getBoolean("canClickTurn",true);
        String typePath = preference.getString("fonttype",Config.FONTTYPE_KAITI);
        this.typeface = getTypeface(typePath);
        this.textFontsIndex = preference.getInt("textFontsIndex",DEFAULT_FONT_INDEX);
    }


    public Typeface getTypeface(String typeFacePath){
        Typeface mTypeface;
        if (typeFacePath.equals(Config.FONTTYPE_DEFAULT)){
            mTypeface = Typeface.DEFAULT;
        }else{
            mTypeface = Typeface.createFromAsset(mContext.getAssets(),typeFacePath);
        }
        return mTypeface;
    }


    public int getTextSize() {
        return textSize;
    }

    public int getTextExtra() {
        return textExtra;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextBackground() {
        return textBackground;
    }

    public int getTextKindIndex() {
        return textKindIndex;
    }

    public void setTypeface(String typefacePath){
        this.typeface = getTypeface(typefacePath);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("fonttype",typefacePath);
        editor.commit();
    }

    public Typeface getTypeface(){
        if (typeface == null) {
            String typePath = preference.getString("fonttype",Config.FONTTYPE_KAITI);
            typeface = getTypeface(typePath);
        }
        return typeface;
    }

    public String getTypefacePath(){
        String path = preference.getString("fonttype",Config.FONTTYPE_KAITI);
        return path;
    }

    public void setTextKindIndex(int textKindIndex) {
        this.textKindIndex = textKindIndex;
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt("textKindIndex",textKindIndex);
        editor.commit();
        this.textSize = textKind.get(textKindIndex).get("textSize");
        this.textExtra = textKind.get(textKindIndex).get("textExtra");
    }

    public int getTextDrawableIndex() {
        return textDrawableIndex;
    }

    public void setTextDrawableIndex(int textDrawableIndex) {
        this.textDrawableIndex = textDrawableIndex;
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt("textDrawableIndex",textDrawableIndex);
        editor.commit();
        this.textColor = textDrawable.get(textDrawableIndex).get("textColor");
        this.textBackground = textDrawable.get(textDrawableIndex).get("textBackground");
    }

    public static List<Map<String, Integer>> getTextKind() {
        return textKind;
    }

    public static List<Map<String, Integer>> getTextDrawable() {
        return textDrawable;
    }

    public Boolean getCanKeyTurn() {
        return canKeyTurn;
    }

    public void setCanKeyTurn(Boolean canKeyTurn) {
        this.canKeyTurn = canKeyTurn;
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("canKeyTurn",canKeyTurn);
        editor.commit();
    }

    public Boolean getCanClickTurn() {
        return canClickTurn;
    }

    public void setCanClickTurn(Boolean canClickTurn) {
        this.canClickTurn = canClickTurn;
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("canClickTurn",canClickTurn);
        editor.commit();
    }

    public void setTextFontsIndex(int textFontsIndex){
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt("textFontsIndex",textFontsIndex);
        editor.commit();
         this.textFontsIndex = textFontsIndex;
    }

    public int getTextFontsIndex(){
         return textFontsIndex;
    }
}
