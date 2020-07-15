package com.luoyang.llreader.view;

import com.luoyang.mvprxjava.IView;

import java.io.File;

/**
 * package: com.luoyang.llreader.presenter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface IImportBookView extends IView {
    /**
     * 新增书籍
     * @param newFile
     */
    void addNewBook(File newFile);

    /**
     * 书籍搜索完成
     */
    void searchFinish();

    /**
     * 添加成功
     */
    void addSuccess();

    /**
     * 添加失败
     */
    void addError();
}
