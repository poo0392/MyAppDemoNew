package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.BannerAdapter;
import com.example.pooja.myappdemonew.model.BannerListModel;

import java.util.ArrayList;

/**
 * Created by Zafar.Hussain on 12/12/2017.
 */

public class PolStoryFragment extends Fragment {
    RecyclerView storyRecyclerView;
    Context mContext;
    LinearLayoutManager mLayoutManager;
    ArrayList<BannerListModel> mBannerList;
    BannerAdapter mBannerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pol_story, container, false);
        mContext=getActivity();
        attachViews(view);
        return view;
    }

    private void attachViews(View view) {
        storyRecyclerView = (RecyclerView)view.findViewById(R.id.stories_recyclerview);
        storyRecyclerView.setHasFixedSize(true);
        storyRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(mContext);
        storyRecyclerView.setLayoutManager(mLayoutManager);
        addItems();
        mBannerAdapter = new BannerAdapter(mContext, mBannerList,"Pol Story");
        storyRecyclerView.setAdapter(mBannerAdapter);
        mBannerAdapter.notifyDataSetChanged();
    }

    private void addItems() {
        mBannerList = new ArrayList<>();
        mBannerList.add(new BannerListModel(getResources().getDrawable(R.drawable.mall), getResources().getString(R.string.default_text)));
        mBannerList.add(new BannerListModel(getResources().getDrawable(R.drawable.garden), getResources().getString(R.string.default_text)));
        mBannerList.add(new BannerListModel(getResources().getDrawable(R.drawable.building), getResources().getString(R.string.default_text)));


    }
}
