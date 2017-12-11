package com.example.pooja.myappdemonew.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.BannerAdapter;
import com.example.pooja.myappdemonew.model.BannerListModel;

import java.util.ArrayList;

/**
 * Created by Zafar.Hussain on 28/11/2017.
 */

public class BannerWithLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView app_icon;
    private RecyclerView banner_recyclerview;
    private TextView tv_sign_up, tv_login, tv_skip;
    // private UltraViewPager ultraViewPager;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<BannerListModel> mBannerList;
    private BannerAdapter mBannerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_with_login_layout);

        attachViews();
        setListeners();
    }


    private void attachViews() {
        app_icon = (ImageView) findViewById(R.id.app_icon);

        tv_sign_up = (TextView) findViewById(R.id.tv_sign_up);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_skip = (TextView) findViewById(R.id.tv_skip);

        banner_recyclerview = (RecyclerView) findViewById(R.id.banner_recycler);
        banner_recyclerview.setHasFixedSize(true);
        banner_recyclerview.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(this);
        banner_recyclerview.setLayoutManager(mLayoutManager);
        addItems();
        mBannerAdapter = new BannerAdapter(this, mBannerList);
        banner_recyclerview.setAdapter(mBannerAdapter);
        mBannerAdapter.notifyDataSetChanged();

      /*  ultraViewPager = (UltraViewPager)findViewById(R.id.list_item_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //initialize UltraPagerAdapter，and add child view to UltraViewPager
        PagerAdapter adapter = new UltraPagerAdapter(false);
        ultraViewPager.setAdapter(adapter);
        //set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        ultraViewPager.setAutoScroll(2000);*/

    }

    private void addItems() {
        mBannerList = new ArrayList<>();
        mBannerList.add(new BannerListModel(getResources().getDrawable(R.drawable.mall), getResources().getString(R.string.default_text)));
        mBannerList.add(new BannerListModel(getResources().getDrawable(R.drawable.garden), getResources().getString(R.string.default_text)));
        mBannerList.add(new BannerListModel(getResources().getDrawable(R.drawable.building), getResources().getString(R.string.default_text)));

    }

    private void setListeners() {
        tv_sign_up.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                Intent intent = new Intent(BannerWithLoginActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_skip:
                Intent in = new Intent(BannerWithLoginActivity.this, HomeActivity.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
