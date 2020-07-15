package com.luoyang.llreader.view;

import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.llreader.view.adapter.ChoiceBookAdapter;
import com.luoyang.mvprxjava.IView;

import java.util.List;

/**
 * package: com.luoyang.llreader.view
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface IChoiceBookView extends IView {

    void refreshSearchBook(List<SearchBookBean> books);

    void loadMoreSearchBook(List<SearchBookBean> books);

    void refreshFinish(Boolean isAll);

    void loadMoreFinish(Boolean isAll);

    void searchBookError();

    void addBookShelfSuccess(List<SearchBookBean> searchBooks);

    void addBookShelfFailed(int code);

    ChoiceBookAdapter getSearchBookAdapter();

    void updateSearchItem(int index);

    void startRefreshAnim();
}
