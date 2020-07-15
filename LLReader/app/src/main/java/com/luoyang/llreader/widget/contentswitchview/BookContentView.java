package com.luoyang.llreader.widget.contentswitchview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luoyang.llreader.R;
import com.luoyang.llreader.ReadBookControl;
import com.luoyang.llreader.widget.MTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;


/**
 * package: com.luoyang.llreader.widget.contentswitchview
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public class BookContentView extends FrameLayout {
    public long qTag = System.currentTimeMillis();

    public static final int DURPAGEINDEXBEGIN = -1;
    public static final int DURPAGEINDEXEND = -2;

    private View view;
    private ImageView ivBg;
    private TextView tvTitle;
    private LinearLayout llContent;
    private MTextView tvContent;
    private View vBottom;
    private TextView tvPage;

    private TextView tvLoading;
    private LinearLayout llError;
    private TextView tvErrorInfo;
    private TextView tvLoadAgain;

    private String title;
    private String content;
    private int durChapterIndex;
    private int chapterAll;
    private int durPageIndex;      //如果durPageIndex = -1 则是从头开始  -2则是从尾开始
    private int pageAll;

    private Context mContext;
    private ContentSwitchView.LoadDataListener loadDataListener;

    private SetDataListener setDataListener;

    public interface SetDataListener {
        void setDataFinish(BookContentView bookContentView, int durChapterIndex, int chapterAll, int durPageIndex, int pageAll, int fromPageIndex);
    }

    public BookContentView(Context context) {
        this(context, null);
    }

    public BookContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BookContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_content_switch_item, this, false);
        addView(view);
        ivBg = view.findViewById(R.id.iv_bg);
        tvTitle = view.findViewById(R.id.tv_title);
        llContent = view.findViewById(R.id.ll_content);
        tvContent = view.findViewById(R.id.tv_content);
        vBottom = view.findViewById(R.id.v_bottom);
        tvPage = view.findViewById(R.id.tv_page);

        tvLoading = view.findViewById(R.id.tv_loading);
        llError = view.findViewById(R.id.ll_error);
        tvErrorInfo = view.findViewById(R.id.tv_error_info);
        tvLoadAgain = view.findViewById(R.id.tv_load_again);

        tvLoadAgain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadDataListener != null)
                    loading();
            }
        });
    }

    public void loading() {
        llError.setVisibility(GONE);
        tvLoading.setVisibility(VISIBLE);
        llContent.setVisibility(INVISIBLE);
        qTag = System.currentTimeMillis();
        //执行请求操作
        if (loadDataListener != null) {
            loadDataListener.loaddata(this, qTag, durChapterIndex, durPageIndex);
        }
    }

    public void finishLoading() {
        llError.setVisibility(GONE);
        llContent.setVisibility(VISIBLE);
        tvLoading.setVisibility(GONE);
    }

    public void setNoData(String contentLines) {
        this.content = contentLines;

        tvPage.setText((this.durPageIndex + 1) + "/" + this.pageAll);

        finishLoading();
    }

    public void updateData(long tag, String title, List<String> contentLines, int durChapterIndex, int chapterAll, int durPageIndex, int durPageAll) {
        if (tag == qTag) {
            if (setDataListener != null) {
                setDataListener.setDataFinish(this, durChapterIndex, chapterAll, durPageIndex, durPageAll, this.durPageIndex);
            }
            if (contentLines == null) {
                this.content = "";
            } else {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < contentLines.size(); i++) {
                    s.append(contentLines.get(i));
                }
                this.content = s.toString();
            }
            this.title = title;
            this.durChapterIndex = durChapterIndex;
            this.chapterAll = chapterAll;
            this.durPageIndex = durPageIndex;
            this.pageAll = durPageAll;

            tvTitle.setText(this.title);
            //设置段距为10density
            //setParagraphSpacing(mContext, tvContent, this.content, 10, 0);
             tvContent.setText(this.content);
            tvPage.setText((this.durPageIndex + 1) + "/" + this.pageAll);

            finishLoading();
        }
    }

    public void loadData(String title, int durChapterIndex, int chapterAll, int durPageIndex) {
        this.title = title;
        this.durChapterIndex = durChapterIndex;
        this.chapterAll = chapterAll;
        this.durPageIndex = durPageIndex;
        tvTitle.setText(title);
        tvPage.setText("");

        loading();
    }

    public ContentSwitchView.LoadDataListener getLoadDataListener() {
        return loadDataListener;
    }

    public void setLoadDataListener(ContentSwitchView.LoadDataListener loadDataListener, SetDataListener setDataListener) {
        this.loadDataListener = loadDataListener;
        this.setDataListener = setDataListener;
    }

    public void setLoadDataListener(ContentSwitchView.LoadDataListener loadDataListener) {
        this.loadDataListener = loadDataListener;
    }

    public void loadError() {
        llError.setVisibility(VISIBLE);
        tvLoading.setVisibility(GONE);
        llContent.setVisibility(INVISIBLE);
    }

    public int getPageAll() {
        return pageAll;
    }

    public void setPageAll(int pageAll) {
        this.pageAll = pageAll;
    }

    public int getDurPageIndex() {
        return durPageIndex;
    }

    public void setDurPageIndex(int durPageIndex) {
        this.durPageIndex = durPageIndex;
    }

    public int getDurChapterIndex() {
        return durChapterIndex;
    }

    public void setDurChapterIndex(int durChapterIndex) {
        this.durChapterIndex = durChapterIndex;
    }

    public int getChapterAll() {
        return chapterAll;
    }

    public void setChapterAll(int chapterAll) {
        this.chapterAll = chapterAll;
    }

    public SetDataListener getSetDataListener() {
        return setDataListener;
    }

    public void setSetDataListener(SetDataListener setDataListener) {
        this.setDataListener = setDataListener;
    }

    public long getqTag() {
        return qTag;
    }

    public void setqTag(long qTag) {
        this.qTag = qTag;
    }

    public TextView getTvContent() {
        return tvContent;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public int getLineCount(int height) {
        float ascent = tvContent.getPaint().ascent();
        float descent = tvContent.getPaint().descent();
        float textHeight = descent - ascent;
        return (int) ((height * 1.0f - tvContent.getLineSpacingExtra()) / (textHeight + tvContent.getLineSpacingExtra()));
    }

    public void setReadBookControl(ReadBookControl readBookControl) {
        setTextKind(readBookControl);
        setBg(readBookControl);
        setTypeface(readBookControl);
    }

    public void setTypeface(ReadBookControl readBookControl) {
        tvContent.setTypeface(readBookControl.getTypeface());
        tvTitle.setTypeface(readBookControl.getTypeface());
        tvPage.setTypeface(readBookControl.getTypeface());
        tvLoading.setTypeface(readBookControl.getTypeface());
        tvErrorInfo.setTypeface(readBookControl.getTypeface());

    }

    public void setBg(ReadBookControl readBookControl) {
        ivBg.setImageResource(readBookControl.getTextBackground());
        tvTitle.setTextColor(readBookControl.getTextColor());
        tvContent.setTextColor(readBookControl.getTextColor());
        tvPage.setTextColor(readBookControl.getTextColor());
        vBottom.setBackgroundColor(readBookControl.getTextColor());
        tvLoading.setTextColor(readBookControl.getTextColor());
        tvErrorInfo.setTextColor(readBookControl.getTextColor());
    }

    public void setTextKind(ReadBookControl readBookControl) {
        tvContent.setTextSize(readBookControl.getTextSize());
        tvContent.setLineSpacing(readBookControl.getTextExtra(), 1);

    }

    /**
     * 设置TextView段落间距
     *
     * @param context          上下文
     * @param tv               给谁设置段距，就传谁
     * @param content          文字内容
     * @param paragraphSpacing 请输入段落间距（单位dp）
     * @param lineSpacingExtra xml中设置的的行距（单位dp）
     */

    public static void setParagraphSpacing(Context context, TextView tv, String content, int paragraphSpacing, int lineSpacingExtra) {
//        if (!content.contains("jun")) {
//            tv.setText(content);
//            return; }
        //记录每个段落开始的index
        int previousIndex = content.indexOf("jun");
         List<Integer> nextParagraphBeginIndexes = new ArrayList<>();
         nextParagraphBeginIndexes.add(previousIndex);
//         while (previousIndex != -1) {
//             //第一段没有，从第二段开始
//             int nextIndex = content.indexOf("\n\r", previousIndex + 2);
//             previousIndex = nextIndex;
//             if (previousIndex != -1) {
//                 nextParagraphBeginIndexes.add(previousIndex);
//             } }
             //获取行高（包含文字高度和行距）
         float lineHeight = tv.getLineHeight();
        // 把\r替换成透明长方形（宽:1px，高：字高+段距）
         SpannableString spanString = new SpannableString(content);
         Drawable d = ContextCompat.getDrawable(context, R.drawable.icon_library_nor);
         float density = context.getResources().getDisplayMetrics().density;
         //int强转部分为：行高 - 行距 + 段距 left top是组件左上角在容器中的坐标。后两个是宽度和高度
        d.setBounds(0, 0, 1, (int) ((lineHeight - lineSpacingExtra * density) / 1.2 + (paragraphSpacing - lineSpacingExtra) * density));
        for (int index : nextParagraphBeginIndexes) {
            // \r在String中占一个index,替换为图片
             spanString.setSpan(d, index , index + 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); }
             tv.setText(spanString); }

}
//    public static void setParagraphSpacing(Context context, TextView tv, String content, int paragraphSpacing, int lineSpacingExtra) {
//        if (!content.contains("\n")) {
//            tv.setText(content);
//            return; }
//        content = content.replace("\n", "\n\r");
//        //记录每个段落开始的index，第一段没有，从第二段开始
//        int previousIndex = content.indexOf("\n\r");
//
//         List<Integer> nextParagraphBeginIndexes = new ArrayList<>();
//         nextParagraphBeginIndexes.add(previousIndex);
//         while (previousIndex != -1) {
//             int nextIndex = content.indexOf("\n\r", previousIndex + 2);
//             previousIndex = nextIndex;
//             if (previousIndex != -1) {
//                 nextParagraphBeginIndexes.add(previousIndex);
//             } }
//             //获取行高（包含文字高度和行距）
//         float lineHeight = tv.getLineHeight();
//        // 把\r替换成透明长方形（宽:1px，高：字高+段距）
//         SpannableString spanString = new SpannableString(content);
//         Drawable d = ContextCompat.getDrawable(context, R.drawable.bg_shadow);
//         float density = context.getResources().getDisplayMetrics().density;
//         //int强转部分为：行高 - 行距 + 段距 left top是组件左上角在容器中的坐标。后两个是宽度和高度
//        d.setBounds(0, 0, 1, (int) ((lineHeight - lineSpacingExtra * density) / 1.2 + (paragraphSpacing - lineSpacingExtra) * density));
//        for (int index : nextParagraphBeginIndexes) {
//            // \r在String中占一个index,替换为图片
//             spanString.setSpan(d, index + 1, index + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); }
//             tv.setText(spanString); }
//
//}
