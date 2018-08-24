package com.six.jd.utils;

import com.six.jd.api.Api;
import com.six.jd.server.IServer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    //懒汉式加载
    public static RetrofitUtils retrofitUtils;
    private static IServer iServer;
    private static Retrofit retrofit;
    private static OkHttpClient client;
    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            retrofitUtils=new RetrofitUtils();
        }
        return retrofitUtils;
    }
    //访问数据
    public IServer getServer(){
        return iServer==null?getRetrofit(IServer.class):iServer;
    }
    //实例化retrofit
    private <T> T getRetrofit(Class<T> service){
        retrofit = new Retrofit.Builder()
                .client(client())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.LRBASE)
                .build();
        return retrofit.create(service);
    }

    private OkHttpClient client() {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        return client;
    }


}
