package com.luoyang.llreader.common.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * package: com.luoyang.llreader.common.api
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/6
 */
public interface ILingdiankanshuApi {
    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:Keep-Alive",
            "Cache-Control:no-cache"})
    Observable<String> getBookInfo(@Url String url);

    //https://sou.xanbhx.com/search?siteid=lingdiankanshuco&t=920895234054625192&q=%E5%9C%A3%E5%A2%9F
    @GET("/search")
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:Keep-Alive",
            "Cache-Control:no-cache"})
    Observable<String> searchBook( @Query("siteid") String siteid, @Query("t") String time,@Query("q") String content);

    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:Keep-Alive",
            "Cache-Control:no-cache"})
    Observable<String> getBookContent(@Url String url);

    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:Keep-Alive",
            "Cache-Control:no-cache"})
    Observable<String> getChapterList(@Url String url);
}
