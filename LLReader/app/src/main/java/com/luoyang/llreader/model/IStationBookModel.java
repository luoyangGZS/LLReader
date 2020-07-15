package com.luoyang.llreader.model;

import com.luoyang.llreader.bean.BookContentBean;
import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.llreader.listener.OnGetChapterListListener;

import java.util.List;

import io.reactivex.Observable;

/**
 * package: com.luoyang.llreader.model
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public interface IStationBookModel {

    /**
     * 搜索书籍
     */
    Observable<List<SearchBookBean>> searchBook(String content, int page);

    /**
     * 网络请求并解析书籍信息
     */
    Observable<BookShelfBean> getBookInfo(final BookShelfBean bookShelfBean);

    /**
     * 网络解析图书目录
     */
    void getChapterList(final BookShelfBean bookShelfBean, OnGetChapterListListener getChapterListListener);

    /**
     * 章节缓存
     */
    Observable<BookContentBean> getBookContent(final String durChapterUrl, final int durChapterIndex);
}
