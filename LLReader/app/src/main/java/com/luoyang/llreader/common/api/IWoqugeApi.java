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
 * on 2018/11/10
 */
public interface IWoqugeApi {
    //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763
    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> getBookInfo(@Url String url);

    //搜索https://sou.xanbhx.com/search?siteid=lingdiankanshuco&t=920895234054625192&q=%E6%88%91
    //搜索https://sou.xanbhx.com/search?siteid=lingdiankanshuco&q=%E6%88%91
    //搜索搜索https://www.woquge.com/modules/article/search.php?searchkey=%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9
    //@GET("search.xhtml")
    //http://search.17k.com/search.xhtml?c.q=斗破苍穹
    //@GET("/modules/article/search.php")
    //http://www.biyuwu.cc/search.php?keyword=%E6%88%91%E6%AC%B2%E5%B0%81%E5%A4%A9
    //https://www.biyuwu.cc/search.php?keyword=%E5%9C%A3%E5%A2%9F
    @GET("search.php")
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> searchBook(@Query("keyword") String content);
    //     @GET
//    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
//            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0",
//            "Accept-Charset:UTF-8",
//            "Keep-Alive:300",
//            "Connection:keep-alive",
//            "Cache-Control:no-cache"})
//    Observable<String> searchBook(@Query("q") String content, @Query("p") int page, @Query("s") String time);

    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> getBookContent(@Url String url);

    @GET
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.1400",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:keep-alive",
            "Cache-Control:no-cache"})
    Observable<String> getChapterList(@Url String url);
}
