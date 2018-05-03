package com.etisalat.sampletask.foodList;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseFragment;
import com.etisalat.sampletask.common.helper.Constants;
import com.etisalat.sampletask.common.helper.RxBusEvent;
import com.etisalat.sampletask.common.helper.Utils;
import com.etisalat.sampletask.common.models.FoodListItem;
import com.etisalat.sampletask.foodList.adapter.FoodListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends BaseFragment<FoodListPresenter> implements FoodListPresenterListener {


    private RecyclerView recyclerViewFoodList;
    private FoodListRecyclerViewAdapter foodListRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Context context;
    private ArrayList<FoodListItem> foodListItemArrayList;
    private FoodListPresenter foodListPresenter;
    private SwipeRefreshLayout swipeContainer;

    public FoodListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        initializeView(view);
        setListeners();
        setFoodRecyclerViewAdapter();

        return view;
    }

    public static FoodListFragment newInstance() {
        return new FoodListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        swipeContainer.setColorSchemeColors(ContextCompat.getColor(context, R.color.secondaryColor));
        foodListPresenter = setupPresenter();
        foodListPresenter.getFoodListItem(context);


    }

    @Override
    public void initializeView(View v) {
        swipeContainer = v.findViewById(R.id.swipeContainer);

        recyclerViewFoodList = v.findViewById(R.id.recyclerViewFoodList);
    }

    @Override
    public void setListeners() {
        swipeContainer.setOnRefreshListener(swipeContainerClickListener);
    }


    @Override
    public void initializeView() {

    }

    @Override
    public void setToolbar() {

    }

    private SwipeRefreshLayout.OnRefreshListener swipeContainerClickListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            foodListItemArrayList.clear();
            recyclerViewFoodList.getRecycledViewPool().clear();
            foodListRecyclerViewAdapter.notifyDataSetChanged();
            foodListPresenter.getFoodListItem(context);
            HashMap<Integer, String> hashMap = new HashMap<>();
            hashMap.put(Constants.publishLastRefreshDate, Utils.getLastRefreshTime());
            RxBusEvent.getInstance().setRxBusEvent(hashMap);


        }
    };

    @Override
    public void onSuccess(ArrayList<FoodListItem> foodListItems) {

        foodListPresenter.sortMenuList(foodListItems);

    }

    @Override
    public void onSortMenuItemFinish(ArrayList<FoodListItem> foodListItems) {
        swipeContainer.setRefreshing(false);
        foodListItemArrayList.addAll(foodListItems);
        foodListRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onReceiveEvent(HashMap<Integer, String> integerStringHashMap) {

    }

    @Override
    public void onError(String error) {
        swipeContainer.setRefreshing(false);
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(Constants.publishErrorRetrofitCode, error);
        RxBusEvent.getInstance().setRxBusEvent(hashMap);

    }

    @Override
    protected FoodListPresenter setupPresenter() {
        return new FoodListPresenter(this);
    }


    @Override
    public void setFoodRecyclerViewAdapter() {
        foodListItemArrayList = new ArrayList<>();
        foodListRecyclerViewAdapter = new FoodListRecyclerViewAdapter(foodListItemArrayList);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);
        recyclerViewFoodList.setAdapter(foodListRecyclerViewAdapter);
    }
}
