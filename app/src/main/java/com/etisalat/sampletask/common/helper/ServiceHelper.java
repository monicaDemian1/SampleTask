package com.etisalat.sampletask.common.helper;

import com.etisalat.sampletask.common.models.responses.FoodListResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by monica on 4/29/2018.
 */

public class ServiceHelper {


    private static Retrofit retrofit;
    public static final String BASE_URL = "https://api.androidhive.info";
    private static RetrofitInterface retrofitInterface;
    private static ServiceHelper mInstance;

    public static ServiceHelper getInstance() {
        if (mInstance == null) {
            mInstance = new ServiceHelper();
        }
        return mInstance;
    }

    ServiceHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }


    public void getFoodMenuList(SingleObserver<FoodListResponse> foodListResponseSingleObserver) {

        retrofitInterface.getFoodList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(foodListResponseSingleObserver);


    }


}
