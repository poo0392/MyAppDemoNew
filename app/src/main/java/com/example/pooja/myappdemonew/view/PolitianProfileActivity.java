package com.example.pooja.myappdemonew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.MyNtbAdapter;
import com.example.pooja.myappdemonew.view.fragment.PolGalleryFragment;
import com.example.pooja.myappdemonew.view.fragment.PolStoryFragment;

/**
 * Created by Zafar.Hussain on 07/12/2017.
 */

public class PolitianProfileActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout mTabLayout;
    CoordinatorLayout maincontent;
    Toolbar toolbar;
    AppBarLayout appbar;
    ImageView header, imgUser;
    CollapsingToolbarLayout collapsing_toolbar;
    TextView tvName, tvProfess, tvAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_profile_layout);

        // View photoHeader = findViewById(R.id.photoHeader);

        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        *//* For devices equal or higher than lollipop set the translation above everything else *//*
            photoHeader.setTranslationZ(6);
        *//* Redraw the view to show the translation *//*
            photoHeader.invalidate();
        }
*/
        attachView();
    }

    private void attachView() {
        toolbar=(Toolbar)findViewById(R.id.prof_toolbar);

        viewPager = (ViewPager) findViewById(R.id.pol_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.pol_tab_layout);
        maincontent = (CoordinatorLayout) findViewById(R.id.maincontent);
        header = (ImageView) findViewById(R.id.header);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        tvName = (TextView) findViewById(R.id.tvName);
        tvProfess = (TextView) findViewById(R.id.tvProfess);
        tvAddress = (TextView) findViewById(R.id.tvAddress);


        setupViewPager(viewPager);
        mTabLayout.setupWithViewPager(viewPager);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsing_toolbar.setTitle(tvName.getText().toString());
                    isShow = true;
                } else if (isShow) {
                    collapsing_toolbar.setTitle("");
                    isShow = false;
                }
            }
        });
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    private void setupViewPager(ViewPager viewPager) {

        MyNtbAdapter mAdapter = new MyNtbAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new PolStoryFragment(), "Story");
        mAdapter.addFragment(new PolGalleryFragment(), "Gallery");
        viewPager.setAdapter(mAdapter);
    }
}
