package com.luoyang.llreader.presenter;

import com.luoyang.mvprxjava.IPresenter;

import java.util.LinkedHashMap;

/**
 * package: com.luoyang.llreader.presenter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public interface ILibraryPresenter extends IPresenter {

    LinkedHashMap<String, String> getKinds();

    void getLibraryData();

}
