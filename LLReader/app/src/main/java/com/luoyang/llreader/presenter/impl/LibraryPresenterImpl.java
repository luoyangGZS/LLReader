package com.luoyang.llreader.presenter.impl;

import android.os.Handler;

import com.luoyang.llreader.MApplication;
import com.luoyang.llreader.base.observer.SimpleObserver;
import com.luoyang.llreader.bean.LibraryBean;
import com.luoyang.llreader.cache.ACache;
import com.luoyang.llreader.model.impl.BiyuwuBookModelImpl;
import com.luoyang.llreader.presenter.ILibraryPresenter;
import com.luoyang.llreader.view.ILibraryView;
import com.luoyang.mvprxjava.impl.BasePresenter;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * package: com.luoyang.llreader.presenter.impl
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public class LibraryPresenterImpl extends BasePresenter<ILibraryView> implements ILibraryPresenter {

    public final static String LIBRARY_CACHE_KEY = "cache_library";
    private ACache mCache;
    private Boolean isFirst = true;

    private final LinkedHashMap<String,String> kinds = new LinkedHashMap<>();

    public LibraryPresenterImpl() {

//        kinds.put("历史架空","http://www.kushuge.com/lishi/");
//        kinds.put("东方玄幻","http://www.kushuge.com/xuanhuan/");
//        kinds.put("仙侠修真","http://www.kushuge.com/xiuzhen/");
//        kinds.put("其他小说","http://www.kushuge.com/qita/");
//
//        kinds.put("科幻","http://www.kushuge.com/kehuan/");
//        kinds.put("女生","http://www.kushuge.com/nvsheng/");
//        kinds.put("网游","http://www.kushuge.com/wangyou/");
//        kinds.put("全本","http://www.kushuge.com/quanben/");
//
        kinds.put("历史架空","http://www.ltsw888.com/lishi/");
        kinds.put("东方玄幻","http://www.ltsw888.com/xuanhuan/");
        kinds.put("仙侠修真","http://www.ltsw888.com/xiuzhen/");
        kinds.put("其他小说","http://www.ltsw888.com/qita/");

        kinds.put("科幻","http://www.ltsw888.com/kehuan/");
        kinds.put("女生","http://www.ltsw888.com/nvsheng/");
        kinds.put("网游","http://www.ltsw888.com/wangyou/");
        kinds.put("全本","http://www.ltsw888.com/quanben/");
//        kinds.put("洛洛小说改进","");
//        kinds.put("QQ讨论组:","");
//        kinds.put("949762199","");
//        kinds.put("内测需要你！","");


        mCache = ACache.get(MApplication.getInstance());
    }




    @Override
    public void getLibraryData() {

        if (isFirst) {
            isFirst = false;
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    String cache = mCache.getAsString(LIBRARY_CACHE_KEY);
                    e.onNext(cache);
                    e.onComplete();
                }
            }).flatMap(new Function<String, ObservableSource<LibraryBean>>() {
                @Override
                public ObservableSource<LibraryBean> apply(String s) throws Exception {
                    return BiyuwuBookModelImpl.getInstance().analyLibraryData(s);
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SimpleObserver<LibraryBean>() {
                        @Override
                        public void onNext(LibraryBean value) {
                            //执行刷新界面
                            mView.updateUI(value);
                            getLibraryNewData();
                        }

                        @Override
                        public void onError(Throwable e) {
                            getLibraryNewData();
                        }
                    });
        }else{
            getLibraryNewData();
        }

    }

    @Override
    public void detachView() {

    }

    private void getLibraryNewData() {
        BiyuwuBookModelImpl.getInstance().getLibraryData(mCache).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<LibraryBean>() {
                    @Override
                    public void onNext(final LibraryBean value) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mView.updateUI(value);
                                mView.finishRefresh();
                            }
                        },1000);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.finishRefresh();
                    }
                });
    }

    @Override
    public LinkedHashMap<String, String> getKinds() {
        return kinds;
    }
}
