package com.example.pooja.myappdemonew.model;

import android.graphics.drawable.Drawable;

/**
 * Created by POOJA on 12/5/2017.
 */

public class HealthItemModel {
    public String itemName;
    public String rating;
    public String address;
    public Drawable itemPhoto;
    public String offers;

    public HealthItemModel(String itemName, String rating, String address, Drawable itemPhoto) {
        this.itemName = itemName;
        this.rating = rating;
        this.address = address;
        this.itemPhoto = itemPhoto;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Drawable getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(Drawable itemPhoto) {
        this.itemPhoto = itemPhoto;
    }
}
