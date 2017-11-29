package com.example.pooja.myappdemonew.model;

import android.support.v4.view.ViewPager;

/**
 * Created by Zafar.Hussain on 28/11/2017.
 */

public class BannerListModel {

    public int photo;
    public String name;
    public ViewPager mPager;

    public BannerListModel(int photo, String name) {
        this.photo = photo;
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
