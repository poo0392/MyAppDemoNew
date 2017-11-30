package com.example.pooja.myappdemonew.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.UltraPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tmall.ultraviewpager.UltraViewPager;

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
    private UltraViewPager ultraViewPager;
    private UltraPagerAdapter adapter;

    private SlidingTabLayout tabLayout;
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

        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tl_4);
        tabLayout.setViewPager(flycoViewpager);
        tabLayout.notifyDataSetChanged();
        //tabLayout.setOnClickListener();

        // tabLayout.setupWithViewPager(flycoViewpager);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //flycoViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        /*flycoViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
                // tabLayout.updateTabSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

    }

    private void setupViewPager(ViewPager flycoViewpager) {
        MyFlycoPagerAdapter mAdapter = new MyFlycoPagerAdapter(getFragmentManager());
        mAdapter.addFragment(new ServicesFragment(), "Services For You");
        mAdapter.addFragment(new ShopStoresFragment(), "Shop-Stores");
        mAdapter.addFragment(new ProfessionsFragment(), "Professions");
        // mViewPager.setAdapter(mAdapter);
        flycoViewpager.setAdapter(mAdapter);
    }

    private void setViewPager(View view) {
     /*   mPagerAdapter = new MyPagerAdapter(mContext);
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
        });*/
        ultraViewPager = (UltraViewPager) view.findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //initialize UltraPagerAdapter，and add child view to UltraViewPager
      //  adapter = new UltraPagerAdapter(false);
        adapter=new UltraPagerAdapter(mContext,"home",false);
        ultraViewPager.setAdapter(adapter);

        //initialize built-in indicator
        ultraViewPager.initIndicator();
        //set style of indicators
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.WHITE)
                .setNormalColor(getResources().getColor(R.color.LightGrey))
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //set the alignment
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //construct built-in indicator, and add it to  UltraViewPager
        ultraViewPager.getIndicator().build();

        //set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        ultraViewPager.setAutoScroll(2000);

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

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
