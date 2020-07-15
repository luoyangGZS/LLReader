package com.luoyang.llreader;

import java.util.HashMap;
import java.util.Map;

/**
 * package: com.luoyang.llreader
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public class BitIntentDataManager {
    public static Map<String,Object> bigData;

    private static BitIntentDataManager instance = null;

    public static BitIntentDataManager getInstance(){
        if(instance == null){
            synchronized (BitIntentDataManager.class){
                if(instance == null){
                    instance = new BitIntentDataManager();
                }
            }
        }
        return instance;
    }

    private BitIntentDataManager(){
        bigData = new HashMap<>();
    }
    public Object getData(String key){
        return bigData.get(key);
    }
    public void putData(String key,Object data){
        bigData.put(key,data);
    }
    public void cleanData(String key){
        bigData.remove(key);
    }
}
