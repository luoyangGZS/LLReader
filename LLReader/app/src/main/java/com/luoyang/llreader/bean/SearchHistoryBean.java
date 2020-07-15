package com.luoyang.llreader.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * package: com.luoyang.llreader.bean
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
@Entity
public class SearchHistoryBean {
    @Id(autoincrement = true)
    private Long id = null;
    private int type;
    private String content;
    private long date;

    public SearchHistoryBean(int type, String content, long date) {
        this.type = type;
        this.content = content;
        this.date = date;
    }
    @Generated(hash = 488115752)
    public SearchHistoryBean(Long id, int type, String content, long date) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.date = date;
    }
    @Generated(hash = 1570282321)
    public SearchHistoryBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getDate() {
        return this.date;
    }
    public void setDate(long date) {
        this.date = date;
    }
}
