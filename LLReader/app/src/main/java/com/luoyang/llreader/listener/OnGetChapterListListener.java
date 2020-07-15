package com.luoyang.llreader.listener;

import com.luoyang.llreader.bean.BookShelfBean;

/**
 * package: com.luoyang.llreader.listener
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public interface  OnGetChapterListListener {
    public void success(BookShelfBean bookShelfBean);
    public void error();
}
