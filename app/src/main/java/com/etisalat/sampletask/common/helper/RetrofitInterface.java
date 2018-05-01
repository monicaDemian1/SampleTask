package com.etisalat.sampletask.common.helper;

import com.etisalat.sampletask.common.models.responses.FoodListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by monica on 4/29/2018.
 */

interface RetrofitInterface {
    @GET("/pizza/?format=xml")
    Single<FoodListResponse> getFoodList();
}
