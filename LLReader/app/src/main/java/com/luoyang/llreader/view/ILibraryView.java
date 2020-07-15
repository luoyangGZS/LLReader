package com.luoyang.llreader.view;

import android.view.View;

import com.luoyang.llreader.bean.LibraryBean;
import com.luoyang.mvprxjava.IView;

/**
 * package: com.luoyang.llreader.view
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public interface ILibraryView extends IView {

    /**
     * 书城书籍获取成功  更新UI
     * @param library
     */
    void updateUI(LibraryBean library);

    /**
     * 书城数据刷新成功 更新UI
     */
    void finishRefresh();
}
