package com.etisalat.sampletask.common.helper;

import android.content.Context;

import com.etisalat.sampletask.common.models.responses.FoodListResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by monica on 4/29/2018.
 */

public class ServiceHelper {


    private static Retrofit retrofit;
    private Context context;
    private static final String BASE_URL = "https://api.androidhive.info";
    private static RetrofitInterface retrofitInterface;
    private static ServiceHelper mInstance;
    private OkHttpClient okHttpClient;
    private Cache cache;
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10 MB


    public static ServiceHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ServiceHelper(context);
        }
        return mInstance;
    }

    private ServiceHelper(Context context) {


        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(buildOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    private OkHttpClient buildOkHttpClient() {
        createCacheForOkHTTP();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkConnectionInterceptor(context))
                .addInterceptor(new ConnectionInterceptor(context))
                .cache(cache)
                .build();
        return okHttpClient;
    }

    private void createCacheForOkHTTP() {
        cache = new Cache(context.getCacheDir(), DISK_CACHE_SIZE);
    }




    public void getFoodMenuList(SingleObserver<FoodListResponse> foodListResponseSingleObserver) {

        retrofitInterface.getFoodList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(foodListResponseSingleObserver);


    }


}
