package com.luoyang.llreader.bean;

import java.util.List;

/**
 * package: com.luoyang.llreader.bean
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/8
 */
public class ReadBookContentBean {
    private List<BookContentBean> bookContentList;
    private int pageIndex;

    public ReadBookContentBean(List<BookContentBean> bookContentList,int pageIndex){
        this.bookContentList =  bookContentList;
        this.pageIndex = pageIndex;
    }

    public List<BookContentBean> getBookContentList() {
        return bookContentList;
    }

    public void setBookContentList(List<BookContentBean> bookContentList) {
        this.bookContentList = bookContentList;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
