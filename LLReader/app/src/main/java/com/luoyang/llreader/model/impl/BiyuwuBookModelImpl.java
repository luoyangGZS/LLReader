package com.luoyang.llreader.model.impl;

import android.util.Log;

import com.luoyang.llreader.Common;
import com.luoyang.llreader.ErrorAnalyContentManager;
import com.luoyang.llreader.base.observer.SimpleObserver;
import com.luoyang.llreader.bean.BookContentBean;
import com.luoyang.llreader.bean.BookInfoBean;
import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.bean.ChapterListBean;
import com.luoyang.llreader.bean.LibraryBean;
import com.luoyang.llreader.bean.LibraryKindBookListBean;
import com.luoyang.llreader.bean.LibraryNewBookBean;
import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.llreader.bean.WebChapterBean;
import com.luoyang.llreader.cache.ACache;
import com.luoyang.llreader.common.api.IBiyuwuApi;
import com.luoyang.llreader.common.api.IWoqugeApi;
import com.luoyang.llreader.listener.OnGetChapterListListener;
import com.luoyang.llreader.model.IBiyuwuBookModel;
import com.luoyang.llreader.presenter.impl.LibraryPresenterImpl;
import com.luoyang.mvprxjava.impl.BaseModelImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * package: com.luoyang.llreader.model.impl
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/11
 */
public class BiyuwuBookModelImpl extends BaseModelImpl implements IBiyuwuBookModel {

    //已加密 https
    //http://www.biyuwu.cc/html/87/87120/
    public static final String TAG = "https://www.biyuwu.cc";

    public static BiyuwuBookModelImpl getInstance() {
        return new BiyuwuBookModelImpl();
    }

    /**
     * 获取主页信息
     */
    @Override
    public Observable<LibraryBean> getLibraryData(final ACache aCache) {
        return getRetrofitObject(TAG).create(IBiyuwuApi.class).getLibraryData("").flatMap(new Function<String, ObservableSource<LibraryBean>>() {
            @Override
            public ObservableSource<LibraryBean> apply(String s) throws Exception {
                if (s != null && s.length() > 0 && aCache != null) {
                    aCache.put(LibraryPresenterImpl.LIBRARY_CACHE_KEY, s);
                }
                return analyLibraryData(s);
            }
        });
    }

    /**
     * 解析主页数据
     */
    @Override
    public Observable<LibraryBean> analyLibraryData(final String data) {
        return Observable.create(new ObservableOnSubscribe<LibraryBean>() {
            @Override
            public void subscribe(ObservableEmitter<LibraryBean> e) throws Exception {
                LibraryBean result = new LibraryBean();
                Document doc = Jsoup.parse(data);
                Element contentE = doc.getElementById("main");
                //解析最新入库小说
               // Elements newBookEs = contentE.getElementById("newscontent").getElementsByClass("r").get(0).getElementsByTag("li");
                //解析最近更新小说列表
                Elements newBookEs = contentE.getElementById("newscontent").getElementsByClass("l").get(0).getElementsByTag("li");
                List<LibraryNewBookBean> libraryNewBooks = new ArrayList<LibraryNewBookBean>();
                for (int i = 0; i < newBookEs.size(); i++) {
                    Element itemE = newBookEs.get(i).getElementsByTag("span").get(1).getElementsByTag("a").get(0);
                    LibraryNewBookBean item = new LibraryNewBookBean(itemE.text(), TAG + itemE.attr("href"), TAG, "biyuwu.cc");
                    libraryNewBooks.add(item);
                }
                result.setLibraryNewBooks(libraryNewBooks);
                //////////////////////////////////////////////////////////////////////
                List<LibraryKindBookListBean> kindBooks = new ArrayList<LibraryKindBookListBean>();
                //解析玄幻仙侠分类
                Elements hotEs = contentE.getElementsByClass("novelslist").get(0).getElementsByClass("content");
                for (int i = 0; i < hotEs.size(); i++) {
                    LibraryKindBookListBean kindItem = new LibraryKindBookListBean();
                    kindItem.setKindName(hotEs.get(i).getElementsByTag("h2").get(0).text());

                    List<SearchBookBean> books = new ArrayList<SearchBookBean>();

                    Element firstBookE = hotEs.get(i).getElementsByClass("top").get(0).getElementsByClass("image").get(0);
                    SearchBookBean firstBook = new SearchBookBean();
                    firstBook.setTag(TAG);
                    firstBook.setOrigin("biyuwu.cc");
                    firstBook.setName(firstBookE.getElementsByTag("a").get(0).getElementsByTag("img").get(0).attr("alt"));
                    firstBook.setNoteUrl(TAG + firstBookE.getElementsByTag("a").get(0).attr("href"));
                    firstBook.setCoverUrl(firstBookE.getElementsByTag("a").get(0).getElementsByTag("img").get(0).attr("src"));
                    firstBook.setKind(kindItem.getKindName());
                    books.add(firstBook);

                    Elements bookEsBefore = hotEs.get(i).getElementsByTag("ul");
                    for (int n = 0; n < bookEsBefore.size(); n++) {
                        Elements bookEs = bookEsBefore.get(n).getElementsByTag("li");
                        for(int j = 0 ;j<bookEs.size();j++){
                            SearchBookBean searchBookBean = new SearchBookBean();
                            searchBookBean.setOrigin("biyuwu.cc");
                            searchBookBean.setTag(TAG);
                            searchBookBean.setName(bookEs.get(j).getElementsByTag("a").get(0).text());
                            searchBookBean.setNoteUrl(TAG + bookEs.get(j).getElementsByTag("a").get(0).attr("href"));
                            //searchBookBean.setCoverUrl(bookEs.get(j).getElementsByTag("img").get(0).attr("src"));
                            books.add(searchBookBean);}
                    }
                    kindItem.setBooks(books);
                    kindBooks.add(kindItem);
                }
                //解析历史网游 分类推荐
                Elements kindEs = contentE.getElementsByClass("novelslist").get(1).getElementsByClass("content border");
                for (int i = 0; i < kindEs.size(); i++) {
                    LibraryKindBookListBean kindItem = new LibraryKindBookListBean();
                    kindItem.setKindName(kindEs.get(i).getElementsByTag("h2").get(0).text());
                    //kindItem.setKindUrl(TAG + kindEs.get(i).getElementsByClass("listMore").get(0).getElementsByTag("a").get(0).attr("href"));

                    List<SearchBookBean> books = new ArrayList<SearchBookBean>();
                    Element firstBookE = kindEs.get(i).getElementsByClass("top").get(0).getElementsByClass("image").get(0);
                    SearchBookBean firstBook = new SearchBookBean();
                    firstBook.setTag(TAG);
                    firstBook.setOrigin("biyuwu.cc");
                    firstBook.setName(firstBookE.getElementsByTag("a").get(0).getElementsByTag("img").get(0).attr("alt"));
                    firstBook.setNoteUrl(TAG + firstBookE.getElementsByTag("a").get(0).attr("href"));
                    firstBook.setCoverUrl(firstBookE.getElementsByTag("a").get(0).getElementsByTag("img").get(0).attr("src"));
                    firstBook.setKind(kindItem.getKindName());
                    books.add(firstBook);

                    Elements bookEsBefore = hotEs.get(i).getElementsByTag("ul");
                    for (int n = 0; n < bookEsBefore.size(); n++) {
                        Elements bookEs = bookEsBefore.get(n).getElementsByTag("li");
                        for(int j = 0 ;j<bookEs.size();j++){
                            SearchBookBean searchBookBean = new SearchBookBean();
                            searchBookBean.setOrigin("biyuwu.cc");
                            searchBookBean.setTag(TAG);
                            searchBookBean.setName(bookEs.get(j).getElementsByTag("a").get(0).text());
                            searchBookBean.setNoteUrl(TAG + bookEs.get(j).getElementsByTag("a").get(0).attr("href"));
                            //searchBookBean.setCoverUrl(bookEs.get(j).getElementsByTag("img").get(0).attr("src"));
                            books.add(searchBookBean);}
                    }
                    kindItem.setBooks(books);
                    kindBooks.add(kindItem);
                }
                //////////////
                result.setKindBooks(kindBooks);
                e.onNext(result);
                e.onComplete();
            }
        });
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 搜索小说，并解析
     * https://www.biyuwu.cc/search.php?keyword=%E5%9C%A3%E5%A2%9F
     */

    @Override
    public Observable<List<SearchBookBean>> searchBook(String content, int page) {
        Log.d(Common.TAG,TAG+" doing searchBook");
        return getRetrofitObject(TAG)
                .create(IBiyuwuApi.class)
                .searchBook(content)
                .flatMap(new Function<String, ObservableSource<List<SearchBookBean>>>() {
                    @Override
                    public ObservableSource<List<SearchBookBean>> apply(String s) throws Exception {
                        return analySearchBook(s);
                    }
                });
    }

    public Observable<List<SearchBookBean>> analySearchBook(final String s) {
        Log.d(Common.TAG,TAG + " search ---success!");
        return Observable.create(new ObservableOnSubscribe<List<SearchBookBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SearchBookBean>> e) throws Exception {
                try {
                    Document doc = Jsoup.parse(s);
                    Elements booksE = doc.getElementsByClass("result-list").get(0).getElementsByClass("result-item result-game-item");
                    if (null != booksE && booksE.size() > 1) {
                        List<SearchBookBean> books = new ArrayList<SearchBookBean>();
                        for (int i = 0; i < booksE.size(); i++) {
                            SearchBookBean item = new SearchBookBean();
                            item.setTag(TAG);
                            item.setAuthor(booksE.get(i).getElementsByClass("result-game-item-detail").get(0).getElementsByClass("result-game-item-info").get(0).getElementsByClass("result-game-item-info-tag").get(0).getElementsByTag("span").get(1).text());
                            item.setKind(booksE.get(i).getElementsByClass("result-game-item-detail").get(0).getElementsByClass("result-game-item-info").get(0).getElementsByClass("result-game-item-info-tag").get(1).getElementsByTag("span").get(1).text());
//                            item.setState();
                            item.setLastChapter(booksE.get(i).getElementsByClass("result-game-item-detail").get(0).getElementsByClass("result-game-item-info").get(0).getElementsByClass("result-game-item-info-tag").get(3).getElementsByTag("a").get(0).text());
                            item.setOrigin("biyuwu.cc");
                            item.setName(booksE.get(i).getElementsByClass("result-game-item-detail").get(0).getElementsByClass("result-item-title result-game-item-title").get(0).getElementsByTag("a").get(0).text());
                            item.setNoteUrl(booksE.get(i).getElementsByClass("result-game-item-detail").get(0).getElementsByClass("result-item-title result-game-item-title").get(0).getElementsByTag("a").get(0).attr("href"));
                            item.setDesc(booksE.get(i).getElementsByClass("result-game-item-detail").get(0).getElementsByTag("p").get(0).text());
                            item.setCoverUrl(booksE.get(i).getElementsByClass("result-game-item-pic").get(0).getElementsByTag("img").get(0).attr("src"));

                            books.add(item);
                        }
                        e.onNext(books);
                    } else {
                        e.onNext(new ArrayList<SearchBookBean>());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    e.onNext(new ArrayList<SearchBookBean>());
                }
                e.onComplete();
            }
        });
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取书的具体信息
     */

    @Override
    public Observable<BookShelfBean> getBookInfo(final BookShelfBean bookShelfBean) {
        return getRetrofitObject(TAG).create(IWoqugeApi.class)
                .getBookInfo(bookShelfBean.getNoteUrl().replace(TAG, ""))
                .flatMap(new Function<String, ObservableSource<BookShelfBean>>() {
                    @Override
                    public ObservableSource<BookShelfBean> apply(String s) throws Exception {
                        return analyBookInfo(s, bookShelfBean);
                    }
                });
    }

    private Observable<BookShelfBean> analyBookInfo(final String s, final BookShelfBean bookShelfBean) {
        return Observable.create(new ObservableOnSubscribe<BookShelfBean>() {
            @Override
            public void subscribe(ObservableEmitter<BookShelfBean> e) throws Exception {
                bookShelfBean.setTag(TAG);
                bookShelfBean.setBookInfoBean(analyBookinfo(s, bookShelfBean.getNoteUrl()));
               // bookShelfBean.setBookInfoBean(new BookInfoBean());
                e.onNext(bookShelfBean);
               //e.onNext(new BookShelfBean() );
                e.onComplete();
            }
        });
    }

    private BookInfoBean analyBookinfo(String s, String novelUrl) {
        BookInfoBean bookInfoBean = new BookInfoBean();
        bookInfoBean.setNoteUrl(novelUrl);   //id
        bookInfoBean.setTag(TAG);
        Document doc = Jsoup.parse(s);
        Element resultE = doc.getElementsByClass("box_con").get(0);
        bookInfoBean.setCoverUrl(resultE.getElementById("fmimg").getElementsByTag("img").get(0).attr("src"));
        bookInfoBean.setName(resultE.getElementById("info").getElementsByTag("h1").get(0).text());
        String author = resultE.getElementById("info").getElementsByTag("p").get(0).text().trim();
        author = author.replace(" ", "").replace("  ", "").replace("作者：", "");
        bookInfoBean.setAuthor(author);

       //List<TextNode> contentEs = resultE.getElementById("intro").getElementsByTag("p").get(0).textNodes();
       List<TextNode> contentEs = resultE.getElementById("intro").textNodes();
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < contentEs.size(); i++) {
            String temp = contentEs.get(i).text().trim();
            temp = temp.replaceAll(" ", "").replaceAll(" ", "");
            if (temp.length() > 0) {
                content.append("\u3000\u3000" + temp);
                if (i < contentEs.size() - 1) {
                    content.append("\r\n");
                }
            }
        }

        bookInfoBean.setIntroduce(content.toString());
        bookInfoBean.setChapterUrl(novelUrl);
        bookInfoBean.setOrigin("biyuwu.cc");
        return bookInfoBean;
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取章节列表
     */
    @Override
    public void getChapterList(final BookShelfBean bookShelfBean, final OnGetChapterListListener getChapterListListener) {
        getRetrofitObject(TAG).create(IWoqugeApi.class).getChapterList(bookShelfBean.getBookInfoBean().getChapterUrl().replace(TAG, "")).flatMap(new Function<String, ObservableSource<WebChapterBean<BookShelfBean>>>() {
            @Override
            public ObservableSource<WebChapterBean<BookShelfBean>> apply(String s) throws Exception {
                return analyChapterList(s, bookShelfBean);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<WebChapterBean<BookShelfBean>>() {
                    @Override
                    public void onNext(WebChapterBean<BookShelfBean> value) {
                        if (getChapterListListener != null) {
                            getChapterListListener.success(value.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (getChapterListListener != null) {
                            getChapterListListener.error();
                        }
                    }
                });
    }

    private Observable<WebChapterBean<BookShelfBean>> analyChapterList(final String s, final BookShelfBean bookShelfBean) {
        return Observable.create(new ObservableOnSubscribe<WebChapterBean<BookShelfBean>>() {
            @Override
            public void subscribe(ObservableEmitter<WebChapterBean<BookShelfBean>> e) throws Exception {
                bookShelfBean.setTag(TAG);
                WebChapterBean<List<ChapterListBean>> temp = analyChapterlist(s, bookShelfBean.getNoteUrl());
                bookShelfBean.getBookInfoBean().setChapterlist(temp.getData());
                e.onNext(new WebChapterBean<BookShelfBean>(bookShelfBean, temp.getNext()));
                e.onComplete();
            }
        });
    }

    private WebChapterBean<List<ChapterListBean>> analyChapterlist(String s, String novelUrl) {
        Document doc = Jsoup.parse(s);
        Elements chapterlist = doc.getElementById("list").getElementsByTag("dd");
        List<ChapterListBean> chapterBeans = new ArrayList<ChapterListBean>();
        for (int i = 0; i < chapterlist.size(); i++) {
            ChapterListBean temp = new ChapterListBean();
            temp.setDurChapterUrl(TAG + chapterlist.get(i).getElementsByTag("a").get(0).attr("href"));   //id
            temp.setDurChapterIndex(i);
            temp.setDurChapterName(chapterlist.get(i).getElementsByTag("a").get(0).text());
            temp.setNoteUrl(novelUrl);
            temp.setTag(TAG);

            chapterBeans.add(temp);
        }
        Boolean next = false;
        return new WebChapterBean<List<ChapterListBean>>(chapterBeans, next);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取书的内容
     */

    @Override
    public Observable<BookContentBean> getBookContent(final String durChapterUrl, final int durChapterIndex) {
        return getRetrofitObject(TAG).create(IWoqugeApi.class).getBookContent(durChapterUrl.replace(TAG, "")).flatMap(new Function<String, ObservableSource<BookContentBean>>() {
            @Override
            public ObservableSource<BookContentBean> apply(String s) throws Exception {
                return analyBookContent(s, durChapterUrl, durChapterIndex);
            }
        });
    }

    private Observable<BookContentBean> analyBookContent(final String s, final String durChapterUrl, final int durChapterIndex) {
        return Observable.create(new ObservableOnSubscribe<BookContentBean>() {
            @Override
            public void subscribe(ObservableEmitter<BookContentBean> e) throws Exception {
                BookContentBean bookContentBean = new BookContentBean();
                bookContentBean.setDurChapterIndex(durChapterIndex);
                bookContentBean.setDurChapterUrl(durChapterUrl);
                bookContentBean.setTag(TAG);
                try {
//                    Log.d(Common.TAG,TAG + " BookContent durChapterIndex" + durChapterIndex +"durChapterUrl:" + durChapterUrl);
//                    Log.d(Common.TAG,TAG + " BookContent " + s);
                    Document doc = Jsoup.parse(s);
                    List<TextNode> contentEs = doc.getElementById("content").textNodes();
                    StringBuilder content = new StringBuilder();
                    for (int i = 0; i < contentEs.size(); i++) {
                        String temp = contentEs.get(i).text().trim();
                        temp = temp.replaceAll(" ", "").replaceAll(" ", "");
                        if (temp.length() > 0) {
                            content.append("\u3000\u3000" + temp+"\n");
                            if (i < contentEs.size() - 1) {
                                content.append("\r\n");
                            }
                        }
                    }
                    //+"\n\n洛洛小说改进阅读讨论组:949762199内测需要你"
                    bookContentBean.setDurCapterContent("\n"+content.toString());
                    bookContentBean.setRight(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    ErrorAnalyContentManager.getInstance().writeNewErrorUrl(durChapterUrl);
                    bookContentBean.setDurCapterContent(durChapterUrl.substring(0, durChapterUrl.indexOf('/', 8)) +durChapterUrl+ "站点暂时不支持解析，请反馈给luoyang QQ:1845313665,半小时内解决，超级效率的程序员");
                    bookContentBean.setRight(false);
                }
                e.onNext(bookContentBean);
                e.onComplete();
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取分类书籍
     */
//    @Override
//    public Observable<List<SearchBookBean>> getKindBook(String url, int page) {
//        url = url + page + ".htm";
//        return getRetrofitObject(GxwztvBookModelImpl.TAG).create(IGxwztvApi.class).getKindBooks(url.replace(GxwztvBookModelImpl.TAG, "")).flatMap(new Function<String, ObservableSource<List<SearchBookBean>>>() {
//            @Override
//            public ObservableSource<List<SearchBookBean>> apply(String s) throws Exception {
//                return analySearchBook(s);
//            }
//        });
//    }



}
