package com.etisalat.sampletask.foodList;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseActivity;
import com.etisalat.sampletask.common.helper.Constants;
import com.etisalat.sampletask.common.models.FoodListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FoodListActivity extends BaseActivity<FoodListPresenter> implements FoodListPresenterListener {

    private Toolbar toolbar;
    private TextView lastUpdateTimeTextView;
    private FoodListPresenter foodListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        initializeView();
        setToolbar();
        foodListPresenter = getPresenter();
        foodListPresenter.getEventBus();
        loadFragment();

    }


    @Override
    protected FoodListPresenter setupPresenter() {
        return new FoodListPresenter(this);

    }

    private void loadFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, FoodListFragment.newInstance()).commit();
    }


    @Override
    public void onReceiveEvent(HashMap<Integer, String> integerStringHashMap) {
        //get HashMap keys
        List<Integer> indexes = new ArrayList<>(integerStringHashMap.keySet());

        if (indexes.get(0) == Constants.publishErrorRetrofitCode) {

            //if  key is publishErrorRetrofitCode , show snackbar
            String error = integerStringHashMap.get(Constants.publishErrorRetrofitCode);
            View v = getCurrentFocus();
            showSnackbar(error, v);
        } else {
            String lastRefreshTime = integerStringHashMap.get(Constants.publishLastRefreshDate);

            lastUpdateTimeTextView.setText(lastRefreshTime);
            //else   key is publishLastRefreshDate , show last refresh date on toolbar

        }
    }

    @Override
    public void setToolbar() {
        toolbar.setTitle(R.string.toolbar_title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(getResources().getDimension(R.dimen.marginPadding4));

        }
        setSupportActionBar(toolbar);


    }

    @Override
    public void initializeView() {

        toolbar = findViewById(R.id.toolbar);
        lastUpdateTimeTextView = findViewById(R.id.txtLastUpdateTime);
    }

    @Override
    public void onSuccess(ArrayList<FoodListItem> foodListItems) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onError(String error) {


    }

    @Override
    public void onSortMenuItemFinish(ArrayList<FoodListItem> foodListItems) {

    }

    @Override
    public void setFoodRecyclerViewAdapter() {

    }

    @Override
    public void initializeView(View v) {

    }


}
