package com.luoyang.llreader.presenter;

import android.app.Activity;

import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.presenter.impl.ReadBookPresenterImpl;
import com.luoyang.llreader.widget.contentswitchview.BookContentView;
import com.luoyang.mvprxjava.IPresenter;

/**
 * package: com.luoyang.llreader.presenter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/8
 */
public interface IReadBookPresenter extends IPresenter {


    int getOpen_from();

    BookShelfBean getBookShelf();

    void initContent();

    void loadContent(BookContentView bookContentView, long bookTag, final int chapterIndex, final int page);

    void updateProgress(int chapterIndex, int pageIndex);

    void saveProgress();

    String getChapterTitle(int chapterIndex);

    void setPageLineCount(int pageLineCount);

    void addToShelf(final ReadBookPresenterImpl.OnAddListner addListner);

    Boolean getAdd();

    void initData(Activity activity);

    void openBookFromOther(Activity activity);
}
