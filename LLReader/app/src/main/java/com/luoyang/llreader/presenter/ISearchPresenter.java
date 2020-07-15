package com.luoyang.llreader.presenter;

import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.mvprxjava.IPresenter;

/**
 * package: com.luoyang.llreader.presenter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface ISearchPresenter extends IPresenter {
    Boolean getHasSearch();

    void setHasSearch(Boolean hasSearch);

    void insertSearchHistory();

    void querySearchHistory();

    void cleanSearchHistory();

    int getPage();

    void initPage();

    void toSearchBooks(String key,Boolean fromError);

    void addBookToShelf(final SearchBookBean searchBookBean);

    Boolean getInput();

    void setInput(Boolean input);
}

