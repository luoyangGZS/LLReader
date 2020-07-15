package com.luoyang.llreader.presenter;

import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.mvprxjava.IPresenter;

/**
 * package: com.luoyang.llreader.presenter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface IBookDetailPresenter extends IPresenter {

    int getOpenfrom();

    SearchBookBean getSearchBook();

    BookShelfBean getBookShelf();

    Boolean getInBookShelf();

    void getBookShelfInfo();

    void addToBookShelf();

    void removeFromBookShelf();
}
