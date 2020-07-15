package com.luoyang.llreader.model.impl;

import android.util.Log;

import com.luoyang.llreader.Common;
import com.luoyang.llreader.ErrorAnalyContentManager;
import com.luoyang.llreader.base.observer.SimpleObserver;
import com.luoyang.llreader.bean.BookContentBean;
import com.luoyang.llreader.bean.BookInfoBean;
import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.bean.ChapterListBean;
import com.luoyang.llreader.bean.SearchBookBean;
import com.luoyang.llreader.bean.WebChapterBean;
import com.luoyang.llreader.common.api.IWoqugeApi;
import com.luoyang.llreader.common.api.Itsw888Api;
import com.luoyang.llreader.listener.OnGetChapterListListener;
import com.luoyang.llreader.model.IStationBookModel;
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
 * on 2018/11/10
 */
public class beifen_Itsw888k2SSBookModelImpl extends BaseModelImpl implements IStationBookModel {


    public static final String TAG = "http://www.ltsw888.com";

    public static beifen_Itsw888k2SSBookModelImpl getInstance() {
        return new beifen_Itsw888k2SSBookModelImpl();
    }



    /**
     * 该网站搜索小说
     * http://www.ltsw888.com/s.php?ie=gbk&q=斗罗大陆
     *
     * http://www.ltsw888.com/search.php?keyword=%E5%9C%A3%E5%A2%9F
     */
    @Override
    public Observable<List<SearchBookBean>> searchBook(String content, int page) {
        Log.d(Common.TAG,TAG+" doing searchBook");
        return getRetrofitObject(TAG).create(Itsw888Api.class).searchBook( content).flatMap(new Function<String, ObservableSource<List<SearchBookBean>>>() {
            @Override
            public ObservableSource<List<SearchBookBean>> apply(String s) throws Exception {
                return analySearchBook(s);
            }
        });
    }


    private ObservableSource<List<SearchBookBean>> analySearchBook(final String s) {
        Log.d(Common.TAG,TAG + " search ---success!" + s);
        return Observable.create(new ObservableOnSubscribe<List<SearchBookBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SearchBookBean>> emitter) throws Exception {
                try {
                    Document doc = Jsoup.parse(s);
                    Elements booksE = doc.getElementsByClass("so_list bookcase").get(0).getElementsByClass("bookbox");
                    if (null != booksE && booksE.size() >= 1) {
                        List<SearchBookBean> books = new ArrayList<SearchBookBean>();
                        for (int i = 0; i < booksE.size(); i++) {
                            SearchBookBean item = new SearchBookBean();
                            Element booksEP10 = booksE.get(i).getElementsByClass("p10").get(0);
                            item.setTag(TAG);
                            item.setOrigin("ltsw888.com");
                            item.setCoverUrl(TAG + booksEP10.getElementsByClass("bookimg").get(0).getElementsByTag("a")
                                    .get(0).getElementsByTag("img").get(0).attr("src"));
                            item.setNoteUrl(TAG + booksEP10.getElementsByClass("bookimg").get(0).getElementsByTag("a")
                                    .get(0).attr("href"));
                            item.setName(booksEP10.getElementsByClass("bookinfo").get(0).getElementsByClass("bookname")
                                    .get(0).getElementsByTag("a").get(0).text());

                            String kind = booksEP10.getElementsByClass("bookinfo").get(0).getElementsByClass("cat").get(0).text();
                            kind = kind.replace("分类：", "");
                            item.setKind(kind);
                            String author = booksEP10.getElementsByClass("bookinfo").get(0).getElementsByClass("author").get(0).text();
                            author = author.replace(" ", "").replace("  ", "").replace("作者：", "");
                            item.setAuthor(author);

                            item.setLastChapter(booksEP10.getElementsByClass("bookinfo").get(0).getElementsByClass("update")
                                    .get(0).getElementsByTag("a").get(0).text());
                            item.setDesc(booksEP10.getElementsByClass("bookinfo").get(0).getElementsByTag("p").get(0).text());
                            books.add(item);
                        }
                        emitter.onNext(books);
                    } else {
                        emitter.onNext(new ArrayList<SearchBookBean>());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    emitter.onNext(new ArrayList<SearchBookBean>());
                }
                emitter.onComplete();
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取书的具体信息
     */


    @Override
    public Observable<BookShelfBean> getBookInfo(final BookShelfBean bookShelfBean) {
        return getRetrofitObject(TAG)
                .create(Itsw888Api.class)
                .getBookInfo(bookShelfBean.getNoteUrl()
                        .replace(TAG, ""))
                .flatMap(new Function<String, ObservableSource<BookShelfBean>>() {
                    @Override
                    public ObservableSource<BookShelfBean> apply(String s) throws Exception {
                        String s1 = s;
                        return analyBookInfo(s, bookShelfBean);
                    }
                });
    }

    private ObservableSource<BookShelfBean> analyBookInfo(final String s, final BookShelfBean bookShelfBean) {
        return Observable.create(new ObservableOnSubscribe<BookShelfBean>() {

            @Override
            public void subscribe(ObservableEmitter<BookShelfBean> emitter) throws Exception {
                //BookShelfBean bookShelfBean1 = bookShelfBean;
                bookShelfBean.setTag(TAG);
                bookShelfBean.setBookInfoBean(analyBookinfo(s, bookShelfBean.getNoteUrl()));
                //  bookShelfBean.setBookInfoBean(new BookInfoBean());
                emitter.onNext(bookShelfBean);
                emitter.onComplete();

            }
        });
    }


    private BookInfoBean analyBookinfo(String s, String novelUrl) {

        BookInfoBean bookInfoBean = new BookInfoBean();

        bookInfoBean.setNoteUrl(novelUrl);   //id
        bookInfoBean.setTag(TAG);
        Document doc = Jsoup.parse(s);
        Element resultE = doc.getElementById("maininfo");
        bookInfoBean.setCoverUrl(TAG + resultE.getElementById("fmimg").getElementsByTag("img")
                .get(0).attr("src"));
        bookInfoBean.setName(resultE.getElementById("info").getElementsByTag("h1").get(0).text());

        String author = resultE.getElementById("info").getElementsByTag("p").get(2).text().trim();
        author = author.replace(" ", "").replace("  ", "").replace("作者：", "");
        bookInfoBean.setAuthor(author);

        List<TextNode> contentEs = resultE.getElementById("intro").getElementsByTag("p").get(0).textNodes();
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < contentEs.size(); i++) {
            String temp = contentEs.get(i).text().trim();
            temp = temp.replaceAll(" ", "").replaceAll("  ", "");
            if (temp.length() > 0) {
                content.append("\u3000\u3000" + temp);
                if (i < contentEs.size() - 1) {
                    content.append("\r\n");
                }
            }
        }

        bookInfoBean.setIntroduce(content.toString());
        bookInfoBean.setChapterUrl(novelUrl);
        bookInfoBean.setOrigin("ltsw888.com");
        return bookInfoBean;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取章节列表
     */
    @Override
    public void getChapterList(final BookShelfBean bookShelfBean, final OnGetChapterListListener getChapterListListener) {
        getRetrofitObject(TAG).create(Itsw888Api.class).getChapterList(bookShelfBean.getBookInfoBean().getChapterUrl().replace(TAG, "")).flatMap(new Function<String, ObservableSource<WebChapterBean<BookShelfBean>>>() {
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
        Elements chapterlist = doc.getElementsByClass("listmain").get(0).getElementsByTag("dd");
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
                    Document doc = Jsoup.parse(s);
                    //textNodes() 将文本字符串代表为文档层次中的结点。
                    List<TextNode> contentEs = doc.getElementById("content").textNodes();
                    StringBuilder content = new StringBuilder();
                    for (int i = 0; i < contentEs.size(); i++) {
                        String temp = contentEs.get(i).text().trim();
                        temp = temp.replaceAll(" ", "").replaceAll(" ", "");
                        if (temp.length() > 0) {
                            content.append("\u3000\u3000" + temp + "\n");
                            if (i < contentEs.size() - 1) {
                                content.append("\r\n");
                            }
                        }
                    }
                    //+"\n\n洛洛小说改进阅读讨论组:949762199内测需要你"
                    bookContentBean.setDurCapterContent("\n"+content.toString()+"\n洛洛阅读讨论组:949762199");
                    bookContentBean.setRight(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    ErrorAnalyContentManager.getInstance().writeNewErrorUrl(durChapterUrl);
                    bookContentBean.setDurCapterContent(durChapterUrl.substring(0, durChapterUrl.indexOf('/', 8)) + durChapterUrl + "站点暂时不支持解析，请反馈给luoyang QQ:1845313665,半小时内解决，超级效率的程序员");
                    bookContentBean.setRight(false);
                }
                e.onNext(bookContentBean);
                e.onComplete();
            }
        });
    }

}