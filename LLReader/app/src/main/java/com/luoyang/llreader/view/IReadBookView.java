package com.luoyang.llreader.view;

import android.graphics.Paint;

import com.luoyang.mvprxjava.IView;

/**
 * package: com.luoyang.llreader.view
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/8
 */
public interface IReadBookView extends IView {

    /**
     * 获取当前阅读界面UI画笔
     * @return
     */
    Paint getPaint();

    /**
     * 获取当前小说内容可绘制宽度
     * @return
     */
    int getContentWidth();

    /**
     * 小说数据初始化成功
     * @param durChapterIndex
     * @param chapterAll
     * @param durPageIndex
     */
    void initContentSuccess(int durChapterIndex, int chapterAll, int durPageIndex);

    /**
     * 开始加载
     */
    void startLoadingBook();

    void setHpbReadProgressMax(int count);

    void initPop();

    void showLoadBook();

    void dimissLoadBook();

    void loadLocationBookError();

    void showDownloadMenu();
}
