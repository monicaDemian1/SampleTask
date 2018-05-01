package com.etisalat.sampletask.common.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by monica on 4/29/2018.
 */

@Root(name = "item")
public class FoodListItem {

    @Element(name = "id")
    String id;

    @Element(name = "name")
    String name;

    @Element(name = "cost")
    String cost;

    @Element(name = "description")
    String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
