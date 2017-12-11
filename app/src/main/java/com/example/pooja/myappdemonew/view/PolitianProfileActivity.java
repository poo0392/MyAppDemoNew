package com.example.pooja.myappdemonew.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pooja.myappdemonew.R;

/**
 * Created by Zafar.Hussain on 07/12/2017.
 */

public class PolitianProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pol_profile_layout);

       /* View photoHeader = findViewById(R.id.photoHeader);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        *//* For devices equal or higher than lollipop set the translation above everything else *//*
            photoHeader.setTranslationZ(6);
        *//* Redraw the view to show the translation *//*
            photoHeader.invalidate();
        }*/
    }
}
