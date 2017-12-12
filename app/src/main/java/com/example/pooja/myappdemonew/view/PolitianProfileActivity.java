package com.example.pooja.myappdemonew.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.MyNtbAdapter;
import com.example.pooja.myappdemonew.view.fragment.PolGalleryFragment;
import com.example.pooja.myappdemonew.view.fragment.PolStoryFragment;

/**
 * Created by Zafar.Hussain on 07/12/2017.
 */

public class PolitianProfileActivity extends AppCompatActivity{

    ViewPager viewPager;
    TabLayout mTabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pol_profile_layout);

        View photoHeader = findViewById(R.id.photoHeader);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        /* For devices equal or higher than lollipop set the translation above everything else */
            photoHeader.setTranslationZ(6);
        /* Redraw the view to show the translation */
            photoHeader.invalidate();
        }

        attachView();
    }

    private void attachView() {
        viewPager = (ViewPager) findViewById(R.id.pol_viewpager);
        setupViewPager(viewPager);
        //mPagerAdapter=new MyPagerAdapter(mContext);
        // vp_ntb.setAdapter(mPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.pol_tab_layout);
        mTabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        MyNtbAdapter mAdapter = new MyNtbAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new PolStoryFragment(), "Story");
        mAdapter.addFragment(new PolGalleryFragment(), "Gallery");
        // mViewPager.setAdapter(mAdapter);
        viewPager.setAdapter(mAdapter);
    }
}
