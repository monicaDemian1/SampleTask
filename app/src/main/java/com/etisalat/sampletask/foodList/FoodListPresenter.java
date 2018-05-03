package com.etisalat.sampletask.foodList;

import android.content.Context;
import android.net.http.HttpResponseCache;

import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.common.helper.RxBusEvent;
import com.etisalat.sampletask.common.helper.ServiceHelper;
import com.etisalat.sampletask.common.models.FoodListItem;
import com.etisalat.sampletask.common.models.responses.FoodListResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by monica on 4/29/2018.
 */

class FoodListPresenter extends BasePresenter<FoodListController, FoodListPresenterListener> {


    private FoodListPresenterListener foodListPresenterListener;

    FoodListPresenter(FoodListPresenterListener listener) {
        super(listener);
        foodListPresenterListener = listener;
    }


    void getFoodListItem(Context context) {
        foodListPresenterListener.showProgress();

        ServiceHelper.getInstance(context).getFoodMenuList(singleObserver);

    }


    SingleObserver<FoodListResponse> singleObserver = new SingleObserver<FoodListResponse>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(FoodListResponse foodListResponse) {
            foodListPresenterListener.hideProgress();
            foodListPresenterListener.onSuccess(foodListResponse.getFoodListItems());
        }

        @Override
        public void onError(Throwable e) {
            foodListPresenterListener.hideProgress();
            foodListPresenterListener.onError(e.toString());
        }
    };

    void sortMenuList(ArrayList<FoodListItem> foodListItemArrayList) {
        Collections.sort(foodListItemArrayList, new Comparator<FoodListItem>() {
            @Override
            public int compare(FoodListItem foodListItem1, FoodListItem foodListItem2) {
                return foodListItem1.getName().compareTo(foodListItem2.getName());
            }
        });
        foodListPresenterListener.onSortMenuItemFinish(foodListItemArrayList);
    }

    void getEventBus() {
        RxBusEvent.getInstance().getEvent().subscribe(eventBusObserver);

    }

    private Observer<HashMap<Integer, String>> eventBusObserver = new Observer<HashMap<Integer, String>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(HashMap<Integer, String> integerStringHashMap) {

            foodListPresenterListener.onReceiveEvent(integerStringHashMap);


        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

}
