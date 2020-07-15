package com.luoyang.llreader.presenter;

import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.mvprxjava.IPresenter;

/**
 * package: com.luoyang.llreader.presenter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface IChoiceBookPresenter extends IPresenter {

    int getPage();

    void initPage();

    void toSearchBooks(String key);

    void addBookToShelf(final SearchBookBean searchBookBean);

    String getTitle();
}
