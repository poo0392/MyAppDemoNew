package com.example.pooja.myappdemonew.adapter;

/**
 * Created by POOJA on 12/11/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyNtbAdapter extends FragmentPagerAdapter {

    private final List<String> mFragmentTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public MyNtbAdapter(FragmentManager fm) {
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

       /* @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }*/
}