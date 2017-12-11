package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.MyNtbAdapter;

/**
 * Created by POOJA on 12/11/2017.
 */

public class NewFeaturesFragment extends Fragment {

    Toolbar toolbar;
    public ViewPager mViewPager;
    TabLayout mTabLayout;

    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_features, container, false);
        context = getActivity();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        setTootlbarTitle("All Categories");

        attachViews(view);
        setClickListeners();

        return view;
    }

    private void attachViews(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_categories);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // onBackPressed();
            }
        });
    }

    private void setClickListeners() {

    }

    private void setTootlbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toobarTextColor));
    }

    private void setupViewPager(ViewPager ntb_viewpager) {

        MyNtbAdapter mAdapter = new MyNtbAdapter(getFragmentManager());
        mAdapter.addFragment(new ServicesFragment(), "Services For You");
        mAdapter.addFragment(new ShopStoresFragment(), "Shop-Stores");
        mAdapter.addFragment(new ProfessionsFragment(), "Professions");
        // mViewPager.setAdapter(mAdapter);
        ntb_viewpager.setAdapter(mAdapter);
    }

}
