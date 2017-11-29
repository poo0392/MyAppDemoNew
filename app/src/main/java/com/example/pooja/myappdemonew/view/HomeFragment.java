package com.example.pooja.myappdemonew.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.MyPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by POOJA on 11/11/2017.
 */

public class HomeFragment extends Fragment {
    private Context mContext;
    private PagerAdapter mPagerAdapter;
    private ViewPager vpPager;
    private CircleIndicator titleIndicator;
    private ViewPager flycoViewpager;

    private SlidingTabLayout tabLayout_10;
    private final String[] mTitles = {"Services For You", "Shop-Stores", "Professions"};
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        //setHasOptionsMenu(false);
        mContext = getActivity();
        //attachViews(view);
        setViewPager(view);
        setFlycoViewPager(view);

        return view;
    }

    private void setFlycoViewPager(View view) {
        flycoViewpager = (ViewPager) view.findViewById(R.id.flyco_tab_vp);
        setupViewPager(flycoViewpager);

      //  TabLayout tb=(TabLayout) view.findViewById(R.id.tabLayout);
      //  tb.setupWithViewPager(flycoViewpager);

        tabLayout_10 = (SlidingTabLayout) view.findViewById(R.id.tl_10);
        tabLayout_10.setViewPager(flycoViewpager);
        tabLayout_10.notifyDataSetChanged();
        //tabLayout_10.setOnClickListener();

       // tabLayout_10.setupWithViewPager(flycoViewpager);
        tabLayout_10.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                flycoViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        flycoViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout_10.setCurrentTab(position);
               // tabLayout_10.updateTabSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager(ViewPager flycoViewpager) {
        MyFlycoPagerAdapter mAdapter = new MyFlycoPagerAdapter(getFragmentManager());
        mAdapter.addFragment(new ServicesFragment(),"Services For You");
        mAdapter.addFragment(new ShopStoresFragment(),"Shop-Stores");
        mAdapter.addFragment(new ProfessionsFragment(),"Professions");
        // mViewPager.setAdapter(mAdapter);
        flycoViewpager.setAdapter(mAdapter);
    }

    private void setViewPager(View view) {
        mPagerAdapter = new MyPagerAdapter(mContext);
        vpPager = (ViewPager) view.findViewById(R.id.vpPager);
        vpPager.setAdapter(mPagerAdapter);
        titleIndicator = (CircleIndicator) view.findViewById(R.id.indicator);
       // titleIndicator.configureIndicator(20, 20, 5);
        titleIndicator.setViewPager(vpPager);


        vpPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);
                } else if (position <= 1) { // Page to the left, page centered, page to the right
                    // modify page view animations here for pages in view
                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
    }


    private class MyFlycoPagerAdapter extends FragmentPagerAdapter {

        public MyFlycoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        public void addFragment(Fragment fragment,String title) {
            mFragments.add(fragment);
             mFragmentTitleList.add(title);
        }
        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
