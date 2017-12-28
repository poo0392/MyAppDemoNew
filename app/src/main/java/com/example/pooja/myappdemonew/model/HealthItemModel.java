package com.example.pooja.myappdemonew.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by POOJA on 12/5/2017.
 */

public class HealthItemModel implements Parcelable {
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

    protected HealthItemModel(Parcel in) {
        itemName = in.readString();
        rating = in.readString();
        address = in.readString();
        offers = in.readString();
    }

    public static final Creator<HealthItemModel> CREATOR = new Creator<HealthItemModel>() {
        @Override
        public HealthItemModel createFromParcel(Parcel in) {
            return new HealthItemModel(in);
        }

        @Override
        public HealthItemModel[] newArray(int size) {
            return new HealthItemModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(rating);
        dest.writeString(address);
        dest.writeString(offers);
    }
}
