package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.HealthSubItemRecyclerAdapter;
import com.example.pooja.myappdemonew.adapter.UltraPagerAdapter;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;

/**
 * Created by Zafar.Hussain on 04/12/2017.
 */

public class HealthFragment extends Fragment {
    Context mContext;
    RecyclerView subItemRecyclerView, healthItemRecyclerView;
    LinearLayoutManager subItemLLMgr,healthItemLLMgr;
    UltraViewPager adsViewPager;
    UltraPagerAdapter adapter;
    ArrayList<String> subItemArrayList;
    HealthSubItemRecyclerAdapter mHealthSubItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Health");
        // setHasOptionsMenu(false);

        mContext = getActivity();
        attachView(view);
        setAdsUtViewPager(view);
        return view;
    }

    private void setAdsUtViewPager(View view) {
        adsViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //initialize UltraPagerAdapter，and add child view to UltraViewPager
        adapter = new UltraPagerAdapter(mContext, "home", false);
        adsViewPager.setAdapter(adapter);

        //initialize built-in indicator
        adsViewPager.initIndicator();
        //set style of indicators
        adsViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.WHITE)
                .setNormalColor(getResources().getColor(R.color.LightGrey))
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        //set the alignment
        adsViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //cons
        // truct built-in indicator, and add it to  UltraViewPager
        adsViewPager.getIndicator().build();

        //set an infinite loop
        adsViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        adsViewPager.setAutoScroll(3000);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // super.onCreateOptionsMenu(menu,);
        inflater.inflate(R.menu.home, menu);
        MenuItem profItem = menu.findItem(R.id.action_profile);
        profItem.setVisible(false);
    }

    private void attachView(View view) {

        subItemRecyclerView = (RecyclerView) view.findViewById(R.id.sub_item_recyclerview);
        healthItemRecyclerView = (RecyclerView) view.findViewById(R.id.health_item_recyclerview);

        subItemRecyclerView.setHasFixedSize(true);
        subItemRecyclerView.setItemAnimator(new DefaultItemAnimator());
        subItemLLMgr = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        subItemRecyclerView.setLayoutManager(subItemLLMgr);
        addSubItemList();
        mHealthSubItemAdapter = new HealthSubItemRecyclerAdapter(mContext, subItemArrayList);
        subItemRecyclerView.setAdapter(mHealthSubItemAdapter);
        mHealthSubItemAdapter.notifyDataSetChanged();
    }

    private void addSubItemList() {
        subItemArrayList= new ArrayList<>();
        subItemArrayList.add("Ambulance");
        subItemArrayList.add("Blood Bank");
        subItemArrayList.add("Chemist");
        subItemArrayList.add("Gym");

    }
}
