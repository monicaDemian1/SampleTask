package com.etisalat.sampletask.common.models.responses;

import com.etisalat.sampletask.common.models.FoodListItem;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by monica on 4/29/2018.
 */

@Root(name = "menu")
public class FoodListResponse {

    @ElementList(inline = true)

    private ArrayList<FoodListItem> foodListItems;

    public ArrayList<FoodListItem> getFoodListItems() {
        return foodListItems;
    }

    public void setFoodListItems(ArrayList<FoodListItem> foodListItems) {
        this.foodListItems = foodListItems;
    }

}
