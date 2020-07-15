package com.luoyang.llreader.presenter;

import com.luoyang.mvprxjava.IPresenter;

import java.io.File;
import java.util.List;

/**
 * package: com.luoyang.llreader.presenter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public interface IImportBookPresenter extends IPresenter {
    void searchLocationBook();

    void importBooks(List<File> books);
}
