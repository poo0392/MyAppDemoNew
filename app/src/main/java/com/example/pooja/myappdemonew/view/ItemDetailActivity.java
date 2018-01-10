package com.example.pooja.myappdemonew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;

/**
 * Created by POOJA on 1/9/2018.
 */

public class ItemDetailActivity extends AppCompatActivity{
    Toolbar toolbar;
    CoordinatorLayout maincontent;
    AppBarLayout appbar;
    CollapsingToolbarLayout collapsing_toolbar;
    ImageView header, imgUser;
    TextView txt_item_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        attachView();
        setListeners();

    }

    private void setListeners() {
        /*appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsing_toolbar.setTitle(txt_item_name.getText().toString());
                    isShow = true;
                } else if (isShow) {
                    collapsing_toolbar.setTitle("");
                    isShow = false;
                }
            }
        });*/
    }

    private void attachView() {
        toolbar=(Toolbar)findViewById(R.id.item_detail_toolbar);
        maincontent = (CoordinatorLayout) findViewById(R.id.maincontent);
        header = (ImageView) findViewById(R.id.header);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        txt_item_name=(TextView)findViewById(R.id.txt_item_name);
    }
}
