package com.luoyang.llreader.view;

import android.content.Context;

import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.mvprxjava.IView;

import java.util.List;

/**
 * package: com.luoyang.llreader.view
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public interface IMainView extends IView {

    /**
     * 刷新书架书籍小说信息 更新UI
     * @param bookShelfBeanList
     */
    void refreshBookShelf(List<BookShelfBean> bookShelfBeanList);

    /**
     * 执行刷新书架小说信息
     */
    void activityRefreshView();

    /**
     * 刷新完成
     */
    void refreshFinish();

    /**
     * 刷新错误
     * @param error
     */
    void refreshError(String error);

    /**
     * 刷新书籍  UI进度修改
     */
    void refreshRecyclerViewItemAdd();

    /**
     * 设置刷新进度条最大值
     * @param x
     */
    void setRecyclerMaxProgress(int x);

}
