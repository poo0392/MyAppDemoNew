package com.example.pooja.myappdemonew.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Zafar.Hussain on 28/11/2017.
 */

public class BannerListModel {

    public Drawable photo;
    public String name;
    public String content;

    public BannerListModel(Drawable photo, String content) {
        this.photo = photo;
        this.content = content;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
