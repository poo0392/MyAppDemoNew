package com.example.pooja.myappdemonew.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Zafar.Hussain on 14/12/2017.
 */

public class PolGalleryModel {

    public Drawable images;
    public String title;
    public String id;

    public PolGalleryModel(Drawable images, String title, String id) {
        this.images = images;
        this.title = title;
        this.id = id;
    }

    public PolGalleryModel(Drawable drawable) {
        this.images = drawable;
    }
}
