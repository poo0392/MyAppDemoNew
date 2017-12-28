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

import static com.example.pooja.myappdemonew.utils.Globals.back_press_screen;

/**
 * Created by Zafar.Hussain on 27/12/2017.
 */

public class NewFeaturesFragment extends Fragment {

    public ViewPager mViewPager;
    TabLayout mTabLayout;
    String from;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_features, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("All Categories");
        setHasOptionsMenu(false);
        back_press_screen=1;
        context = getActivity();
        initializeComp(view);
        return view;
    }

    private void initializeComp(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_categories);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        //  mTabLayout.setTabTextColors(ColorStateList.valueOf(context.getResources().getColor(R.color.toobarTextColor)));
       /* if (!from.equals("")) {
            if (from.equals("Services")) {
                mViewPager.setCurrentItem(0);
            } else if (from.equals("Shops")) {
                mViewPager.setCurrentItem(1);
            } else if (from.equals("Profession")) {
                mViewPager.setCurrentItem(2);
            }

        }*/
    }

    private void setupViewPager(ViewPager ntb_viewpager) {

        MyNtbAdapter mAdapter = new MyNtbAdapter(getFragmentManager());
        mAdapter.addFragment(new ServicesFragment(), "Services");
        mAdapter.addFragment(new StoresFragment(), "Stores");
        mAdapter.addFragment(new ProfessionsFragment(), "Professions");
        // mViewPager.setAdapter(mAdapter);
        ntb_viewpager.setAdapter(mAdapter);
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            from = bundle.getString("from");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // findViews(view);
        readBundle(getArguments());
    }

    public static NewFeaturesFragment newInstance(String value) {
        Bundle args = new Bundle();
        NewFeaturesFragment fragment = new NewFeaturesFragment();
        args.putString("from", value);
        fragment.setArguments(args);
        return fragment;
    }
}
