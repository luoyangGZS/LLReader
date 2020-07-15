package com.luoyang.mvprxjava;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * package: com.luoyang.mvprxjava
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/3
 *
 * Converter 变流器
 *增加返回值为字符串的支持(以实体类返回)
 */
public class EncodeConverter extends Converter.Factory {

    private String encode = "utf-8";

    private EncodeConverter(){

    }
    private EncodeConverter(String encode){
        this.encode = encode;
    }

    public static EncodeConverter create(){
        return new EncodeConverter();
    }

    public static EncodeConverter create(String en){
        return new EncodeConverter(en);
    }

    @Override
    public Converter<ResponseBody, String> responseBodyConverter(Type type, Annotation[] annotations,
                                                                 Retrofit retrofit) {
        return new Converter<ResponseBody, String>() {
            @Override
            public String convert(ResponseBody value) throws IOException {
                BufferedSource bufferedSource = Okio.buffer(value.source());
                String responseData = bufferedSource.readString(Charset.forName(encode));
                return responseData;
            }
        };
    }
}
