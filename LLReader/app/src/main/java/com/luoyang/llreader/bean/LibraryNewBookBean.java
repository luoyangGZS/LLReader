package com.luoyang.llreader.bean;

/**
 * package: com.luoyang.llreader.bean
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public class LibraryNewBookBean {

    private String name;
    private String url;
    private String tag;
    private String orgin;

    public LibraryNewBookBean(String name, String url, String tag, String orgin) {
        this.name = name;
        this.url = url;
        this.tag = tag;
        this.orgin = orgin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOrgin() {
        return orgin;
    }

    public void setOrgin(String orgin) {
        this.orgin = orgin;
    }
}
