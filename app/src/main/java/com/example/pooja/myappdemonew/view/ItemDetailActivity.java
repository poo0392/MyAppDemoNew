package com.example.pooja.myappdemonew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pooja.myappdemonew.R;

/**
 * Created by POOJA on 1/9/2018.
 */

public class ItemDetailActivity extends AppCompatActivity{
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        toolbar=(Toolbar)findViewById(R.id.prof_toolbar);
    }
}
