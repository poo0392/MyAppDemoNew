package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.example.pooja.myappdemonew.adapter.HealthItemRecyclerAdapter;
import com.example.pooja.myappdemonew.adapter.HealthSubItemRecyclerAdapter;
import com.example.pooja.myappdemonew.adapter.UltraPagerAdapter;
import com.example.pooja.myappdemonew.model.HealthItemModel;
import com.example.pooja.myappdemonew.utils.ClickListener;
import com.example.pooja.myappdemonew.utils.RecyclerTouchListener;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;

import static com.example.pooja.myappdemonew.utils.Globals.back_press_screen;

/**
 * Created by Zafar.Hussain on 04/12/2017.
 */

public class HealthFragment extends Fragment {
    Context mContext;
    RecyclerView subItemRecyclerView, healthItemRecyclerView;
    LinearLayoutManager subItemLLMgr, healthItemLLMgr;
    UltraViewPager adsViewPager;
    UltraPagerAdapter adapter;
    ArrayList<String> subItemArrayList;
    ArrayList<HealthItemModel> healthItemArrList;
    HealthSubItemRecyclerAdapter mHealthSubItemAdapter;
    HealthItemRecyclerAdapter mHealthItemAdapter;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        setTootlbarTitle("Health");
        // setHasOptionsMenu(false);
        back_press_screen=2;
        mContext = getActivity();
        attachView(view);
        setAdsUtViewPager(view);
        setListeners();
        return view;
    }

    private void setListeners() {
        healthItemRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, healthItemRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(mContext,ItemDetailActivity.class);
                mContext.startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setTootlbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toobarTextColor));
    }
    private void setAdsUtViewPager(View view) {
        adsViewPager = (UltraViewPager) view.findViewById(R.id.ads_ut_viewpager);
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
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
        //set the alignment
        adsViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        adsViewPager.getIndicator().setMargin(0,0,0,10);
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
        subItemArrayList = addSubItemList();
        //  Log.v("","ArrayList Size: "+subItemArrayList.size());
        mHealthSubItemAdapter = new HealthSubItemRecyclerAdapter(mContext, subItemArrayList);
        subItemRecyclerView.setAdapter(mHealthSubItemAdapter);
        mHealthSubItemAdapter.notifyDataSetChanged();


        healthItemRecyclerView.setHasFixedSize(true);
        healthItemRecyclerView.setItemAnimator(new DefaultItemAnimator());
        healthItemLLMgr = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        healthItemRecyclerView.setLayoutManager(healthItemLLMgr);
        healthItemArrList = addHealthItemList();
        mHealthItemAdapter = new HealthItemRecyclerAdapter(mContext, healthItemArrList);
        healthItemRecyclerView.setAdapter(mHealthItemAdapter);
        mHealthItemAdapter.notifyDataSetChanged();
    }

    private ArrayList<HealthItemModel> addHealthItemList() {
        healthItemArrList = new ArrayList<>();
        healthItemArrList.add(new HealthItemModel("Santosh Health Clinic", "4.5", mContext.getResources().getString(R.string.address1),mContext.getResources().getDrawable(R.drawable.health_img_one)));
        healthItemArrList.add(new HealthItemModel("True Health Homoeopathic Pharmacy", "3.5", mContext.getResources().getString(R.string.address2),mContext.getResources().getDrawable(R.drawable.health_img_two)));
        healthItemArrList.add(new HealthItemModel("Santosh Health Clinic", "4.5", mContext.getResources().getString(R.string.address1),mContext.getResources().getDrawable(R.drawable.health_img_one)));
        healthItemArrList.add(new HealthItemModel("True Health Homoeopathic Pharmacy", "3.5", mContext.getResources().getString(R.string.address2),mContext.getResources().getDrawable(R.drawable.health_img_two)));
        healthItemArrList.add(new HealthItemModel("Santosh Health Clinic", "4.5", mContext.getResources().getString(R.string.address1),mContext.getResources().getDrawable(R.drawable.health_img_one)));
        healthItemArrList.add(new HealthItemModel("True Health Homoeopathic Pharmacy", "3.5", mContext.getResources().getString(R.string.address2),mContext.getResources().getDrawable(R.drawable.health_img_two)));
        healthItemArrList.add(new HealthItemModel("Santosh Health Clinic", "4.5", mContext.getResources().getString(R.string.address1),mContext.getResources().getDrawable(R.drawable.health_img_one)));
        healthItemArrList.add(new HealthItemModel("True Health Homoeopathic Pharmacy", "3.5", mContext.getResources().getString(R.string.address2),mContext.getResources().getDrawable(R.drawable.health_img_two)));
        return healthItemArrList;
    }

    private ArrayList<String> addSubItemList() {
        subItemArrayList = new ArrayList<>();
        subItemArrayList.add("Doctors & Clinics");
        subItemArrayList.add("Diagnostic Center");
        subItemArrayList.add("Chemist");
        subItemArrayList.add("Gym");
        subItemArrayList.add("Sports Clubs");
        return subItemArrayList;
    }

    public static HealthFragment newInstance(ArrayList<HealthItemModel> healthItemArrList) {
        Bundle args = new Bundle();
        HealthFragment fragment = new HealthFragment();
        //args.putBundle("healt items", healthItemArrList);
        fragment.setArguments(args);
        return fragment;
    }
}
