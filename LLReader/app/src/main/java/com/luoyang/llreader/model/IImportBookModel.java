package com.luoyang.llreader.model;

import com.luoyang.llreader.bean.LocBookShelfBean;

import java.io.File;

import io.reactivex.Observable;

/**
 * package: com.luoyang.llreader.model
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface IImportBookModel  {
    Observable<LocBookShelfBean> importBook(File book);
}
