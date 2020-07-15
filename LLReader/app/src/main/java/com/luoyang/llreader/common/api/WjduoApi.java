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
 * on 2018/11/12
 */
public interface WjduoApi {

    //Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0

//    @GET
//    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
//            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0",
//            "Accept-Charset:UTF-8",
//            "Keep-Alive:300",
//            "Connection:keep-alive",
//            "Cache-Control:no-cache"})
//    Observable<String> getLibraryData(@Url String url);

    //http://www.wjduo.com/s.php?ie=gbk&q=%B6%FA%B8%F9
    //http://www.kushuge.com/s.php?ie=gbk&q=%CA%A5%D0%E6
    @GET("/s.php")
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> searchBook(@Query("ie") String gbk,@Query("q") String content);


    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> getBookInfo(@Url String url);


    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> getBookContent(@Url String url);

    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> getChapterList(@Url String url);

    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> getKindBooks(@Url String url);


}
