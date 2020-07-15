package com.luoyang.llreader.presenter.impl;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.luoyang.llreader.base.observer.SimpleObserver;
import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.llreader.bean.SearchHistoryBean;
import com.luoyang.llreader.common.RxBusTag;
import com.luoyang.llreader.dao.DbHelper;
import com.luoyang.llreader.dao.SearchHistoryBeanDao;
import com.luoyang.llreader.listener.OnGetChapterListListener;
import com.luoyang.llreader.model.impl.BiqugeBookModelImpl;
import com.luoyang.llreader.model.impl.BiyuwuBookModelImpl;
import com.luoyang.llreader.model.impl.GxwztvBookModelImpl;
import com.luoyang.llreader.model.impl.Itsw888k2SSBookModelImpl;
import com.luoyang.llreader.model.impl.LingdiankanshuStationBookModelImpl;
import com.luoyang.llreader.model.impl.WebBookModelImpl;

import com.luoyang.llreader.model.impl.WjduoBookModelImpl;
import com.luoyang.llreader.presenter.ISearchPresenter;
import com.luoyang.llreader.utils.NetworkUtil;
import com.luoyang.llreader.view.ISearchView;
import com.luoyang.mvprxjava.IView;
import com.luoyang.mvprxjava.impl.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * package: com.luoyang.llreader.presenter.impl
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public class SearchPresenterImpl extends BasePresenter<ISearchView> implements ISearchPresenter {
    public static final String TAG_KEY = "tag";
    public static final String HASMORE_KEY = "hasMore";
    public static final String HASLOAD_KEY = "hasLoad";
    public static final String DURREQUESTTIME = "durRequestTime";    //当前搜索引擎失败次数  成功一次会重新开始计数
    public static final String MAXREQUESTTIME = "maxRequestTime";   //最大连续请求失败次数

    public static final int BOOK = 2;

    private Boolean hasSearch = false;   //判断是否搜索过

    private int page = 1;
    private List<Map> searchEngine;
    private long startThisSearchTime;
    private String durSearchKey;

    private List<BookShelfBean> bookShelfs = new ArrayList<>();   //用来比对搜索的书籍是否已经添加进书架

    private Boolean isInput = false;

    public SearchPresenterImpl() {
        Observable.create(new ObservableOnSubscribe<List<BookShelfBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<BookShelfBean>> e) throws Exception {
                List<BookShelfBean> temp = DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().queryBuilder().list();
                if (temp == null)
                    temp = new ArrayList<BookShelfBean>();
                e.onNext(temp);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<List<BookShelfBean>>() {
                    @Override
                    public void onNext(List<BookShelfBean> value) {
                        bookShelfs.addAll(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

        //搜索引擎初始化
        searchEngine = new ArrayList<>();

        /**
         *    Map前一个请求未成功，搜索未成功  会对后一个进行阻塞
         */


//        Map biquge = new HashMap();
//        biquge.put(TAG_KEY, BiqugeBookModelImpl.TAG);
//        biquge.put(HASMORE_KEY, true);
//        biquge.put(HASLOAD_KEY, false);
//        biquge.put(DURREQUESTTIME, 1);
//        biquge.put(MAXREQUESTTIME, 2);
//        searchEngine.add(biquge);



//        Map gxwztv = new HashMap();
//        gxwztv.put(TAG_KEY, GxwztvBookModelImpl.TAG);
//        gxwztv.put(HASMORE_KEY, true);
//        gxwztv.put(HASLOAD_KEY, false);
//        gxwztv.put(DURREQUESTTIME, 1);
//        gxwztv.put(MAXREQUESTTIME, 2);
//        searchEngine.add(gxwztv);

//        Map lingdiankanshu = new HashMap();
//        lingdiankanshu.put(TAG_KEY, LingdiankanshuStationBookModelImpl.TAG);
//        lingdiankanshu.put(HASMORE_KEY, true);
//        lingdiankanshu.put(HASLOAD_KEY, false);
//        lingdiankanshu.put(DURREQUESTTIME, 1);
//        lingdiankanshu.put(MAXREQUESTTIME, 2);
//        searchEngine.add(lingdiankanshu);


        Map biyuwu = new HashMap();
        biyuwu.put(TAG_KEY, BiyuwuBookModelImpl.TAG);
        biyuwu.put(HASMORE_KEY, true);
        biyuwu.put(HASLOAD_KEY, false);
        biyuwu.put(DURREQUESTTIME, 1);
        biyuwu.put(MAXREQUESTTIME, 2);
        searchEngine.add(biyuwu);

        Map wjduoMap = new HashMap();
        wjduoMap.put(TAG_KEY, WjduoBookModelImpl.TAG);
        wjduoMap.put(HASMORE_KEY, true);
        wjduoMap.put(HASLOAD_KEY, false);
        wjduoMap.put(DURREQUESTTIME, 1);
        wjduoMap.put(MAXREQUESTTIME, 2);
        searchEngine.add(wjduoMap);

//        Map Itsw888 = new HashMap();
//        Itsw888.put(TAG_KEY, Itsw888k2SSBookModelImpl.TAG);
//        Itsw888.put(HASMORE_KEY, true);
//        Itsw888.put(HASLOAD_KEY, false);
//        Itsw888.put(DURREQUESTTIME, 1);
//        Itsw888.put(MAXREQUESTTIME, 2);
//        searchEngine.add(Itsw888);

    }

    @Override
    public Boolean getHasSearch() {
        return hasSearch;
    }

    @Override
    public void setHasSearch(Boolean hasSearch) {
        this.hasSearch = hasSearch;
    }

    @Override
    public void insertSearchHistory() {
        final int type = SearchPresenterImpl.BOOK;
        final String content = mView.getEdtContent().getText().toString().trim();
        Observable.create(new ObservableOnSubscribe<SearchHistoryBean>() {
            @Override
            public void subscribe(ObservableEmitter<SearchHistoryBean> e) throws Exception {
                List<SearchHistoryBean> datas = DbHelper.getInstance().getmDaoSession().getSearchHistoryBeanDao()
                        .queryBuilder()
                        .where(SearchHistoryBeanDao.Properties.Type.eq(type), SearchHistoryBeanDao.Properties.Content.eq(content))
                        .limit(1)
                        .build().list();
                SearchHistoryBean searchHistoryBean = null;
                if (null != datas && datas.size() > 0) {
                    searchHistoryBean = datas.get(0);
                    searchHistoryBean.setDate(System.currentTimeMillis());
                    DbHelper.getInstance().getmDaoSession().getSearchHistoryBeanDao().update(searchHistoryBean);
                } else {
                    searchHistoryBean = new SearchHistoryBean(type, content, System.currentTimeMillis());
                    DbHelper.getInstance().getmDaoSession().getSearchHistoryBeanDao().insert(searchHistoryBean);
                }
                e.onNext(searchHistoryBean);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<SearchHistoryBean>() {
                    @Override
                    public void onNext(SearchHistoryBean value) {
                        mView.insertSearchHistorySuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void cleanSearchHistory() {
        final int type = SearchPresenterImpl.BOOK;
        final String content = mView.getEdtContent().getText().toString().trim();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                int a = DbHelper.getInstance().getDb().delete(SearchHistoryBeanDao.TABLENAME, SearchHistoryBeanDao.Properties.Type.columnName + "=? and " + SearchHistoryBeanDao.Properties.Content.columnName + " like ?", new String[]{String.valueOf(type), "%" + content + "%"});
                e.onNext(a);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<Integer>() {
                    @Override
                    public void onNext(Integer value) {
                        if (value > 0) {
                            mView.querySearchHistorySuccess(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void querySearchHistory() {
        final int type = SearchPresenterImpl.BOOK;
        final String content = mView.getEdtContent().getText().toString().trim();
        Observable.create(new ObservableOnSubscribe<List<SearchHistoryBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SearchHistoryBean>> e) throws Exception {
                List<SearchHistoryBean> datas = DbHelper.getInstance().getmDaoSession().getSearchHistoryBeanDao()
                        .queryBuilder()
                        .where(SearchHistoryBeanDao.Properties.Type.eq(type), SearchHistoryBeanDao.Properties.Content.like("%" + content + "%"))
                        .orderDesc(SearchHistoryBeanDao.Properties.Date)
                        .limit(20)
                        .build().list();
                e.onNext(datas);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<List<SearchHistoryBean>>() {
                    @Override
                    public void onNext(List<SearchHistoryBean> value) {
                        if (null != value)
                            mView.querySearchHistorySuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void initPage() {
        this.page = 1;
    }

    @Override
    public void toSearchBooks(String key, Boolean fromError) {
        if (key != null) {
            durSearchKey = key;
            this.startThisSearchTime = System.currentTimeMillis();
            for (int i = 0; i < searchEngine.size(); i++) {
                searchEngine.get(i).put(HASMORE_KEY, true);
                searchEngine.get(i).put(HASLOAD_KEY, false);
                searchEngine.get(i).put(DURREQUESTTIME, 1);
            }
        }
        searchBook(durSearchKey, startThisSearchTime, fromError);
    }

    private void searchBook(final String content, final long searchTime, Boolean fromError) {
        if (searchTime == startThisSearchTime) {
            Boolean canLoad = false;
            for (Map temp : searchEngine) {
                if ((Boolean) temp.get(HASMORE_KEY) && (int) temp.get(DURREQUESTTIME) <= (int) temp.get(MAXREQUESTTIME)) {
                    canLoad = true;
                    break;
                }
            }
            if (canLoad) {
                int searchEngineIndex = -1;
                for (int i = 0; i < searchEngine.size(); i++) {
                    if (!(Boolean) searchEngine.get(i).get(HASLOAD_KEY) && (int) searchEngine.get(i).get(DURREQUESTTIME) <= (int) searchEngine.get(i).get(MAXREQUESTTIME)) {
                        searchEngineIndex = i;
                        break;
                    }
                }
                if (searchEngineIndex == -1) {
                    this.page++;
                    for (Map item : searchEngine) {
                        item.put(HASLOAD_KEY, false);
                    }
                    if (!fromError) {
                        if (page - 1 == 1) {
                            mView.refreshFinish(false);
                        } else {
                            mView.loadMoreFinish(false);
                        }
                    } else {
                        searchBook(content, searchTime, false);
                    }
                } else {
                    final int finalSearchEngineIndex = searchEngineIndex;
                    WebBookModelImpl.getInstance().searchOtherBook(content, page, (String) searchEngine.get(searchEngineIndex).get(TAG_KEY))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(new SimpleObserver<List<SearchBookBean>>() {
                                @Override
                                public void onNext(List<SearchBookBean> value) {
                                    if (searchTime == startThisSearchTime) {
                                        searchEngine.get(finalSearchEngineIndex).put(HASLOAD_KEY, true);
                                        searchEngine.get(finalSearchEngineIndex).put(DURREQUESTTIME, 1);
                                        if (value.size() == 0) {
                                            searchEngine.get(finalSearchEngineIndex).put(HASMORE_KEY, false);
                                        } else {
                                            for (SearchBookBean temp : value) {
                                                for (BookShelfBean bookShelfBean : bookShelfs) {
                                                    if (temp.getNoteUrl().equals(bookShelfBean.getNoteUrl())) {
                                                        temp.setAdd(true);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        if (page == 1 && finalSearchEngineIndex == 0) {
                                            mView.refreshSearchBook(value);
                                        } else {
                                            if (value != null && value.size() > 0 && !mView.checkIsExist(value.get(0)))
                                                mView.loadMoreSearchBook(value);
                                            else {
                                                searchEngine.get(finalSearchEngineIndex).put(HASMORE_KEY, false);
                                            }
                                        }
                                        searchBook(content, searchTime, false);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                    if (searchTime == startThisSearchTime) {
                                        searchEngine.get(finalSearchEngineIndex).put(HASLOAD_KEY, false);
                                        searchEngine.get(finalSearchEngineIndex).put(DURREQUESTTIME, ((int) searchEngine.get(finalSearchEngineIndex).get(DURREQUESTTIME)) + 1);
                                        mView.searchBookError(page == 1 && (finalSearchEngineIndex == 0 || (finalSearchEngineIndex > 0 && mView.getSearchBookAdapter().getItemcount() == 0)));
                                    }
                                }
                            });
                }
            } else {
                if (page == 1) {
                    mView.refreshFinish(true);
                } else {
                    mView.loadMoreFinish(true);
                }
                this.page++;
                for (Map item : searchEngine) {
                    item.put(HASLOAD_KEY, false);
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void addBookToShelf(final SearchBookBean searchBookBean) {
        final BookShelfBean bookShelfResult = new BookShelfBean();
        bookShelfResult.setNoteUrl(searchBookBean.getNoteUrl());
        bookShelfResult.setFinalDate(0);
        bookShelfResult.setDurChapter(0);
        bookShelfResult.setDurChapterPage(0);
        bookShelfResult.setTag(searchBookBean.getTag());
        WebBookModelImpl.getInstance().getBookInfo(bookShelfResult)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<BookShelfBean>() {
                    @Override
                    public void onNext(BookShelfBean value) {
                        WebBookModelImpl.getInstance().getChapterList(value, new OnGetChapterListListener() {
                            @Override
                            public void success(BookShelfBean bookShelfBean) {
                                saveBookToShelf(bookShelfBean);
                            }

                            @Override
                            public void error() {
                                mView.addBookShelfFailed(NetworkUtil.ERROR_CODE_OUTTIME);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.addBookShelfFailed(NetworkUtil.ERROR_CODE_OUTTIME);
                    }
                });
    }

    private void saveBookToShelf(final BookShelfBean bookShelfBean) {
        Observable.create(new ObservableOnSubscribe<BookShelfBean>() {
            @Override
            public void subscribe(ObservableEmitter<BookShelfBean> e) throws Exception {
                DbHelper.getInstance().getmDaoSession().getChapterListBeanDao().insertOrReplaceInTx(bookShelfBean.getBookInfoBean().getChapterlist());
                DbHelper.getInstance().getmDaoSession().getBookInfoBeanDao().insertOrReplace(bookShelfBean.getBookInfoBean());
                //网络数据获取成功  存入BookShelf表数据库
                DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().insertOrReplace(bookShelfBean);
                e.onNext(bookShelfBean);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<BookShelfBean>() {
                    @Override
                    public void onNext(BookShelfBean value) {
                        //成功   //发送RxBus
                        RxBus.get().post(RxBusTag.HAD_ADD_BOOK, value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.addBookShelfFailed(NetworkUtil.ERROR_CODE_OUTTIME);
                    }
                });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void attachView(@NonNull IView iView) {
        super.attachView(iView);
        RxBus.get().register(this);
    }

    @Override
    public void detachView() {
        RxBus.get().unregister(this);
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(RxBusTag.HAD_ADD_BOOK)
            }
    )
    public void hadAddBook(BookShelfBean bookShelfBean) {
        bookShelfs.add(bookShelfBean);
        List<SearchBookBean> datas = mView.getSearchBookAdapter().getSearchBooks();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getNoteUrl().equals(bookShelfBean.getNoteUrl())) {
                datas.get(i).setAdd(true);
                mView.updateSearchItem(i);
                break;
            }
        }
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(RxBusTag.HAD_REMOVE_BOOK)
            }
    )
    public void hadRemoveBook(BookShelfBean bookShelfBean) {
        if (bookShelfs != null) {
            for (int i = 0; i < bookShelfs.size(); i++) {
                if (bookShelfs.get(i).getNoteUrl().equals(bookShelfBean.getNoteUrl())) {
                    bookShelfs.remove(i);
                    break;
                }
            }
        }
        List<SearchBookBean> datas = mView.getSearchBookAdapter().getSearchBooks();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getNoteUrl().equals(bookShelfBean.getNoteUrl())) {
                datas.get(i).setAdd(false);
                mView.updateSearchItem(i);
                break;
            }
        }
    }

    @Override
    public Boolean getInput() {
        return isInput;
    }

    @Override
    public void setInput(Boolean input) {
        isInput = input;
    }
}
