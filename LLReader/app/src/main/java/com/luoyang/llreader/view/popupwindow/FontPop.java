package com.luoyang.llreader.view.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.luoyang.llreader.Config;
import com.luoyang.llreader.R;
import com.luoyang.llreader.ReadBookControl;

import androidx.annotation.NonNull;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * package: com.luoyang.llreader.view.popupwindow
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/8
 */
public class FontPop extends PopupWindow {



    //private Unbinder unbinder
    private Context mContext;
    private View view;
    private FrameLayout flSmaller;
    private FrameLayout flBigger;
    private TextView tvTextSizedDefault;
    private TextView tvTextSize;
    private CircleImageView civBgWhite;
    private CircleImageView civBgYellow;
    private CircleImageView civBgGreen;
    private CircleImageView civBgPink;
    private CircleImageView civBule1;
    private CircleImageView civBule2;
    private CircleImageView civBgBlack;

    //font
    private TextView tv_Default;
    private TextView tv_Qihei;
    private TextView tv_Fzkatong;
    private TextView tv_Bysong;

    private TextView tv_Hkshaonv;
    private TextView tv_Hwzhongsong;
    private TextView tv_Kaiti;
    private TextView tv_Youyuan;

    private ReadBookControl readBookControl;

    public interface OnChangeProListener {
        void textChange(int index);

        void bgChange(int index);

        void typefaceChange(Typeface typeface);

    }

    private OnChangeProListener changeProListener;

    public FontPop(Context context, @NonNull OnChangeProListener changeProListener) {
        super(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.mContext = context;
        this.changeProListener = changeProListener;

        view = LayoutInflater.from(mContext).inflate(R.layout.view_pop_font, null);
        //unbinder = ButterKnife.bind(this, view);
        this.setContentView(view);
        initData();
        bindView();
        bindEvent();

        setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_pop_checkaddshelf_bg));
        setFocusable(true);
        setTouchable(true);
        setAnimationStyle(R.style.anim_pop_windowlight);
    }

    private void bindEvent() {
        flSmaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText(readBookControl.getTextKindIndex() - 1);
                changeProListener.textChange(readBookControl.getTextKindIndex());
            }
        });
        flBigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText(readBookControl.getTextKindIndex() + 1);
                changeProListener.textChange(readBookControl.getTextKindIndex());
            }
        });
        tvTextSizedDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText(ReadBookControl.DEFAULT_TEXT);
                changeProListener.textChange(readBookControl.getTextKindIndex());
            }
        });

        civBgWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg(0);
                changeProListener.bgChange(readBookControl.getTextDrawableIndex());
            }
        });
        civBgYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg(1);
                changeProListener.bgChange(readBookControl.getTextDrawableIndex());
            }
        });
        civBgGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg(2);
                changeProListener.bgChange(readBookControl.getTextDrawableIndex());
            }
        });

        civBgPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg(3);
                changeProListener.bgChange(readBookControl.getTextDrawableIndex());
            }
        });

        civBule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg(4);
                changeProListener.bgChange(readBookControl.getTextDrawableIndex());
            }
        });
        civBule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg(5);
                changeProListener.bgChange(readBookControl.getTextDrawableIndex());
            }
        });
        civBgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg(6);
                changeProListener.bgChange(readBookControl.getTextDrawableIndex());
            }
        });

        tv_Default.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_DEFAULT,0);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });

        tv_Qihei.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_QIHEI,1);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });

        tv_Fzkatong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_FZKATONG,2);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });

        tv_Bysong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_BYSONG,3);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });

        tv_Hkshaonv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_HKSHAONV,4);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });

        tv_Hwzhongsong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_HWZHONGSONG,5);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });

        tv_Kaiti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_KAITI,6);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });

        tv_Youyuan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTypeface(Config.FONTTYPE_YOUYUAN,7);
                changeProListener.typefaceChange(readBookControl.getTypeface());
            }
        });
    }

    private void bindView() {
        flSmaller = (FrameLayout) view.findViewById(R.id.fl_smaller);
        flBigger = (FrameLayout) view.findViewById(R.id.fl_bigger);
        tvTextSizedDefault = (TextView) view.findViewById(R.id.tv_textsize_default);
        tvTextSize = (TextView) view.findViewById(R.id.tv_dur_textsize);
        updateText(readBookControl.getTextKindIndex());

        civBgWhite = (CircleImageView) view.findViewById(R.id.civ_bg_white);
        civBgYellow = (CircleImageView) view.findViewById(R.id.civ_bg_yellow);
        civBgGreen = (CircleImageView) view.findViewById(R.id.civ_bg_green);
        civBgPink = view.findViewById(R.id.civ_bg_pink);
        civBule1 =  view.findViewById(R.id.civ_bg_bule1);
        civBule2=  view.findViewById(R.id.civ_bg_bule2);
        civBgBlack =  view.findViewById(R.id.civ_bg_black);
        updateBg(readBookControl.getTextDrawableIndex());


         tv_Default = view.findViewById(R.id.tv_default);
         tv_Qihei = view.findViewById(R.id.tv_qihei);
         tv_Fzkatong = view.findViewById(R.id.tv_fzkatong);
         tv_Bysong = view.findViewById(R.id.tv_bysong);
         tv_Hkshaonv = view.findViewById(R.id.tv_hkshaonv);
         tv_Hwzhongsong = view.findViewById(R.id.tv_hwzhongsong);
         tv_Kaiti = view.findViewById(R.id.tv_kaiti);
         tv_Youyuan = view.findViewById(R.id.tv_youyuan);

         //初始化字体
        tv_Default.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_DEFAULT));
        tv_Qihei.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_QIHEI));
        tv_Fzkatong.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_FZKATONG));
        tv_Bysong.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_BYSONG));

        tv_Hkshaonv.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_HKSHAONV));
        tv_Hwzhongsong.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_HWZHONGSONG));
        tv_Kaiti.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_KAITI));
        tv_Youyuan.setTypeface(readBookControl.getTypeface(Config.FONTTYPE_YOUYUAN));

        updateTypeface(readBookControl.getTypefacePath(),readBookControl.getTextFontsIndex());
        changeProListener.typefaceChange(readBookControl.getTypeface());
    }

    //设置字体
    private void updateTypeface(String typefacePath,int textFontsIndex) {
        if (textFontsIndex == 0) {
            tv_Default.setEnabled(false);
            tv_Qihei.setEnabled(true);
            tv_Fzkatong.setEnabled(true);
            tv_Bysong.setEnabled(true);

            tv_Hkshaonv.setEnabled(true);
            tv_Hwzhongsong.setEnabled(true);
            tv_Kaiti.setEnabled(true);
            tv_Youyuan.setEnabled(true);
        } else if(textFontsIndex ==1 ){
            tv_Default.setEnabled(true);
            tv_Qihei.setEnabled(false);
            tv_Fzkatong.setEnabled(true);
            tv_Bysong.setEnabled(true);

            tv_Hkshaonv.setEnabled(true);
            tv_Hwzhongsong.setEnabled(true);
            tv_Kaiti.setEnabled(true);
            tv_Youyuan.setEnabled(true);
        }else if(textFontsIndex ==2 ){
            tv_Default.setEnabled(true);
            tv_Qihei.setEnabled(true);
            tv_Fzkatong.setEnabled(false);
            tv_Bysong.setEnabled(true);

            tv_Hkshaonv.setEnabled(true);
            tv_Hwzhongsong.setEnabled(true);
            tv_Kaiti.setEnabled(true);
            tv_Youyuan.setEnabled(true);
        }else if(textFontsIndex == 3){
            tv_Default.setEnabled(true);
            tv_Qihei.setEnabled(true);
            tv_Fzkatong.setEnabled(true);
            tv_Bysong.setEnabled(false);

            tv_Hkshaonv.setEnabled(true);
            tv_Hwzhongsong.setEnabled(true);
            tv_Kaiti.setEnabled(true);
            tv_Youyuan.setEnabled(true);
        }else if(textFontsIndex == 4){
            tv_Default.setEnabled(true);
            tv_Qihei.setEnabled(true);
            tv_Fzkatong.setEnabled(true);
            tv_Bysong.setEnabled(true);

            tv_Hkshaonv.setEnabled(false);
            tv_Hwzhongsong.setEnabled(true);
            tv_Kaiti.setEnabled(true);
            tv_Youyuan.setEnabled(true);
        }else if(textFontsIndex == 5){
            tv_Default.setEnabled(true);
            tv_Qihei.setEnabled(true);
            tv_Fzkatong.setEnabled(true);
            tv_Bysong.setEnabled(true);

            tv_Hkshaonv.setEnabled(true);
            tv_Hwzhongsong.setEnabled(false);
            tv_Kaiti.setEnabled(true);
            tv_Youyuan.setEnabled(true);
        }else if(textFontsIndex == 6 ){
            tv_Default.setEnabled(true);
            tv_Qihei.setEnabled(true);
            tv_Fzkatong.setEnabled(true);
            tv_Bysong.setEnabled(true);

            tv_Hkshaonv.setEnabled(true);
            tv_Hwzhongsong.setEnabled(true);
            tv_Kaiti.setEnabled(false);
            tv_Youyuan.setEnabled(true);
        }else if(textFontsIndex == 7){
            tv_Default.setEnabled(true);
            tv_Qihei.setEnabled(true);
            tv_Fzkatong.setEnabled(true);
            tv_Bysong.setEnabled(true);

            tv_Hkshaonv.setEnabled(true);
            tv_Hwzhongsong.setEnabled(true);
            tv_Kaiti.setEnabled(true);
            tv_Youyuan.setEnabled(false);
        }
        readBookControl.setTypeface(typefacePath);
        readBookControl.setTextFontsIndex(textFontsIndex);
    }

    private void updateText(int textKindIndex) {
        if (textKindIndex == 0) {
            flSmaller.setEnabled(false);
            flBigger.setEnabled(true);
        } else if (textKindIndex == readBookControl.getTextKind().size() - 1) {
            flSmaller.setEnabled(true);
            flBigger.setEnabled(false);
        } else {
            flSmaller.setEnabled(true);
            flBigger.setEnabled(true);

        }
        if (textKindIndex == ReadBookControl.DEFAULT_TEXT) {
            tvTextSizedDefault.setEnabled(false);
        } else {
            tvTextSizedDefault.setEnabled(true);
        }
        tvTextSize.setText(String.valueOf(readBookControl.getTextKind().get(textKindIndex).get("textSize")));
        readBookControl.setTextKindIndex(textKindIndex);
    }

    private void updateBg(int index) {
        civBgWhite.setBorderColor(Color.parseColor("#00000000"));
        civBgYellow.setBorderColor(Color.parseColor("#00000000"));
        civBgGreen.setBorderColor(Color.parseColor("#00000000"));
        civBgPink.setBorderColor(Color.parseColor("#00000000"));

        civBule1.setBorderColor(Color.parseColor("#00000000"));
        civBule2.setBorderColor(Color.parseColor("#00000000"));
        civBgBlack.setBorderColor(Color.parseColor("#00000000"));
        switch (index) {
            case 0:
                civBgWhite.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 1:
                civBgYellow.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 2:
                civBgGreen.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 3:
                civBgPink.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 4:
                civBule1.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 5:
                civBule2.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            default:
                civBgBlack.setBorderColor(Color.parseColor("#F3B63F"));
                break;
        }
        readBookControl.setTextDrawableIndex(index);
    }

    private void initData() {
        readBookControl = ReadBookControl.getInstance();
    }
}
