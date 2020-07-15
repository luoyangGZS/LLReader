package com.luoyang.llreader.model;

import com.luoyang.llreader.bean.LibraryBean;
import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.llreader.cache.ACache;

import java.util.List;

import io.reactivex.Observable;

/**
 * package: com.luoyang.llreader.model
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public interface IGxwztvBookModel extends IStationBookModel {

    Observable<List<SearchBookBean>> getKindBook(String url, int page);

    /**
     * 获取主页信息
     */
    Observable<LibraryBean> getLibraryData(ACache aCache);

    /**
     * 解析主页数据
     */
    Observable<LibraryBean> analyLibraryData(String data);
}
