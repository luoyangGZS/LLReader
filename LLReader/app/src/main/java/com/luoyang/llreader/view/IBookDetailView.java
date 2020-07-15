package com.luoyang.llreader.view;

import com.luoyang.mvprxjava.IView;

/**
 * package: com.luoyang.llreader.view
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface IBookDetailView extends IView {
    /**
     * 更新书籍详情UI
     */
    void updateView();

    /**
     * 数据获取失败
     */
    void getBookShelfError();
}
