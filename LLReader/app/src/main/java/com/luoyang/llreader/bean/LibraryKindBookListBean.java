package com.luoyang.llreader.bean;

import java.util.List;

/**
 * package: com.luoyang.llreader.bean
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 *
 *  书城 书籍分类推荐列表
 */
public class LibraryKindBookListBean {
    private String kindName;
    private String kindUrl;
    private List<SearchBookBean> books;

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getKindUrl() {
        return kindUrl;
    }

    public void setKindUrl(String kindUrl) {
        this.kindUrl = kindUrl;
    }

    public List<SearchBookBean> getBooks() {
        return books;
    }

    public void setBooks(List<SearchBookBean> books) {
        this.books = books;
    }
}
