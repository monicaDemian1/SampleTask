package com.etisalat.sampletask.foodList;

import android.view.View;

import com.etisalat.sampletask.bases.BasePresenterListener;
import com.etisalat.sampletask.common.models.FoodListItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by monica on 4/29/2018.
 */

interface FoodListPresenterListener extends BasePresenterListener {

    void setFoodRecyclerViewAdapter();

    void initializeView(View v);

    void initializeView();

    void setToolbar();

    void onSuccess(ArrayList<FoodListItem> foodListItems);

    void setListeners();

    void onError(String error);

    void onSortMenuItemFinish(ArrayList<FoodListItem> foodListItems);

    void onReceiveEvent(HashMap<Integer, String> integerStringHashMap);
}
