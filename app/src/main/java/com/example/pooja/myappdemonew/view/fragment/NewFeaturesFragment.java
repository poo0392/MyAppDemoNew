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
    String from;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_features, container, false);
        Bundle args = getArguments();
        if (args != null) {
            from = args.getString("from");
        }

        context = getActivity();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        setTootlbarTitle("All Categories");
        setHasOptionsMenu(true);
        attachViews(view);
        setClickListeners();

        return view;
    }


    private void attachViews(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_categories);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        //  mTabLayout.setTabTextColors(ColorStateList.valueOf(context.getResources().getColor(R.color.toobarTextColor)));

        if (from.equals("Services")) {
            mViewPager.setCurrentItem(0);
        } else if (from.equals("Shops")) {
            mViewPager.setCurrentItem(1);
        } else if (from.equals("Profession")) {
            mViewPager.setCurrentItem(2);
        }
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
        mAdapter.addFragment(new ServicesFragment(), "Services");
        mAdapter.addFragment(new StoresFragment(), "Stores");
        mAdapter.addFragment(new ProfessionsFragment(), "Professions");
        // mViewPager.setAdapter(mAdapter);
        ntb_viewpager.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // findViews(view);
        readBundle(getArguments());
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            from = bundle.getString("from");
        }
    }

    public static NewFeaturesFragment newInstance(String value) {
        Bundle args = new Bundle();
        NewFeaturesFragment fragment = new NewFeaturesFragment();
        args.putString("from", value);
        fragment.setArguments(args);
        return fragment;
    }
}
