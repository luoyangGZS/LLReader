package com.luoyang.llreader.bean;

import java.util.List;

/**
 * package: com.luoyang.llreader.bean
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 * 书城整体Data bean
 */

public class LibraryBean {
    private List<LibraryNewBookBean> libraryNewBooks;
    private List<LibraryKindBookListBean> kindBooks;

    public List<LibraryNewBookBean> getLibraryNewBooks() {
        return libraryNewBooks;
    }

    public void setLibraryNewBooks(List<LibraryNewBookBean> libraryNewBooks) {
        this.libraryNewBooks = libraryNewBooks;
    }

    public List<LibraryKindBookListBean> getKindBooks() {
        return kindBooks;
    }

    public void setKindBooks(List<LibraryKindBookListBean> kindBooks) {
        this.kindBooks = kindBooks;
    }
}