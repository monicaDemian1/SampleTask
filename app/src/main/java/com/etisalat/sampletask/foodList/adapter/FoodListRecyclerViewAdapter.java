package com.etisalat.sampletask.foodList.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.common.helper.Constants;
import com.etisalat.sampletask.common.models.FoodListItem;

import java.util.ArrayList;

/**
 * Created by monica on 4/29/2018.
 */

public class FoodListRecyclerViewAdapter extends RecyclerView.Adapter<FoodListRecyclerViewAdapter.ViewHolder> {


    private ArrayList<FoodListItem> foodListItemsArrayList;

    public FoodListRecyclerViewAdapter(ArrayList<FoodListItem> foodListItemsArrayList) {
        this.foodListItemsArrayList = foodListItemsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FoodListItem foodListItem = foodListItemsArrayList.get(position);
        holder.foodTitle.setText(foodListItem.getName());
        holder.foodDescription.setText(foodListItem.getDescription());
        holder.foodPrice.setText(String.format(Constants.PRICE_DOLLAR, foodListItem.getCost()));


    }

    @Override
    public int getItemCount() {
        return foodListItemsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView foodTitle;
        private TextView foodDescription;
        private TextView foodPrice;

        ViewHolder(View itemView) {
            super(itemView);
            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodDescription = itemView.findViewById(R.id.foodDescription);
            foodPrice = itemView.findViewById(R.id.foodPrice);


        }
    }


}
