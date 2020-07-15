package com.luoyang.llreader.bean;

/**
 * package: com.luoyang.llreader.bean
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public class LocBookShelfBean {
    private Boolean isNew;
    private BookShelfBean bookShelfBean;

    public LocBookShelfBean(Boolean isNew,BookShelfBean bookShelfBean){
        this.isNew = isNew;
        this.bookShelfBean = bookShelfBean;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public BookShelfBean getBookShelfBean() {
        return bookShelfBean;
    }

    public void setBookShelfBean(BookShelfBean bookShelfBean) {
        this.bookShelfBean = bookShelfBean;
    }
}
