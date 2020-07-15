package com.luoyang.llreader.view;

import android.widget.EditText;

import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.llreader.bean.SearchHistoryBean;
import com.luoyang.llreader.view.adapter.SearchBookAdapter;
import com.luoyang.mvprxjava.IView;

import java.util.List;

/**
 * package: com.luoyang.llreader.view
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface ISearchView extends IView {

    /**
     * 成功 新增查询记录
     * @param searchHistoryBean
     */
    void insertSearchHistorySuccess(SearchHistoryBean searchHistoryBean);

    /**
     * 成功搜索 搜索记录
     * @param datas
     */
    void querySearchHistorySuccess(List<SearchHistoryBean> datas);

    /**
     * 首次查询成功 更新UI
     * @param books
     */
    void refreshSearchBook(List<SearchBookBean> books);

    /**
     * 加载更多书籍成功 更新UI
     * @param books
     */
    void loadMoreSearchBook(List<SearchBookBean> books);

    /**
     * 刷新成功
     * @param isAll
     */
    void refreshFinish(Boolean isAll);

    /**
     * 加载成功
     * @param isAll
     */
    void loadMoreFinish(Boolean isAll);

    /**
     * 搜索失败
     * @param isRefresh
     */
    void searchBookError(Boolean isRefresh);

    /**
     * 获取搜索内容EditText
     * @return
     */
    EditText getEdtContent();

    /**
     * 添加书籍失败
     * @param code
     */
    void addBookShelfFailed(int code);

    SearchBookAdapter getSearchBookAdapter();

    void updateSearchItem(int index);

    /**
     * 判断书籍是否已经在书架上
     * @param searchBookBean
     * @return
     */
    Boolean checkIsExist(SearchBookBean searchBookBean);
}
