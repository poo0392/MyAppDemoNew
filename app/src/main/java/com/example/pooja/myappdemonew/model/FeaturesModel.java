package com.example.pooja.myappdemonew.model;

import android.graphics.drawable.Drawable;

/**
 * Created by POOJA on 12/3/2017.
 */

public class FeaturesModel {
    public String item_name;
    public Drawable item_icon;

    public FeaturesModel(String item_name, Drawable item_icon) {
        this.item_name = item_name;
        this.item_icon = item_icon;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Drawable getItem_icon() {
        return item_icon;
    }

    public void setItem_icon(Drawable item_icon) {
        this.item_icon = item_icon;
    }
}
