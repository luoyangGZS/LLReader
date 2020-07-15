package com.luoyang.mvprxjava.impl;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;

/**
 * package: com.luoyang.mvprxjava.impl
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/3
 *
 * retry ：重试
 * Interceptor：拦截机
 */
public class RetryInterceptor implements Interceptor {

    public int maxRetry;//最大重试次数
    private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）


    public RetryInterceptor(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && retryNum < maxRetry) {
            retryNum++;
            response = chain.proceed(request);
        }
        return response;
    }
}
