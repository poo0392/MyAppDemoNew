package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.ServicesAdapter;
import com.example.pooja.myappdemonew.model.FeaturesModel;
import com.example.pooja.myappdemonew.model.HealthItemModel;
import com.example.pooja.myappdemonew.utils.ClickListener;
import com.example.pooja.myappdemonew.utils.RecyclerTouchListener;

import java.util.ArrayList;

import static com.example.pooja.myappdemonew.utils.Globals.back_press_screen;

/**
 * Created by POOJA on 11/12/2017.
 */

public class ServicesFragment extends Fragment {
    private Context mContext;
    private RecyclerView mServicesRecycler;
    private GridLayoutManager mGrisdLayoutManager;
    private ServicesAdapter mServicesAdapter;
    private ArrayList<FeaturesModel> mServicesList;
    ArrayList<HealthItemModel> healthItemArrList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        mContext = getActivity();
        back_press_screen=1;
        attachView(view);
        return view;
    }

    private void attachView(View view) {
        mServicesRecycler = (RecyclerView) view.findViewById(R.id.services_recyclerview);
        mServicesRecycler.setHasFixedSize(true);
        //   mGrisdLayoutManager=new GridLayoutManager(mContext,3);

        mServicesRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));


        mServicesList = addListItems();
        mServicesAdapter = new ServicesAdapter(mContext, mServicesList);
        mServicesRecycler.setAdapter(mServicesAdapter);
        mServicesAdapter.notifyDataSetChanged();


        mServicesRecycler.addOnItemTouchListener(new RecyclerTouchListener(mContext, mServicesRecycler, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                int pos = (int) mServicesAdapter.getItemId(position);
                System.out.println("position on recCLick: " + pos);
                healthItemArrList = addHealthItemList();
                //if (mServicesList.get(pos).getItem_name()== healthItemArrList)
                    for (int i = 0; i < mServicesList.size(); i++) {

                        FragmentTransaction propFrag = getActivity().getSupportFragmentManager().beginTransaction();
                        propFrag.replace(R.id.content_frame, new HealthFragment());
                      //  propFrag.addToBackStack(null);
                        propFrag.commit();

                    }

                  /* switch(pos){
                       case 0:
                           FragmentTransaction propFrag = getActivity().getSupportFragmentManager().beginTransaction();
                         //  propFrag = HealthFragment.newInstance(healthItemArrList);
                           propFrag.replace(R.id.content_frame, new HealthFragment());
                           //  propFrag.addToBackStack(null);
                           propFrag.commit();
                           break;
                       case 1:
                           break;
                   }*/
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
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
    //Added list items
    private ArrayList<FeaturesModel> addListItems() {
        mServicesList = new ArrayList<>();
        /*mServicesList.add(new FeaturesModel("Health",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Home & Office",mContext.getResources().getDrawable(R.drawable.ic_home_office)));
        mServicesList.add(new FeaturesModel("Beauty & Fashion",mContext.getResources().getDrawable(R.drawable.ic_fashion)));
        mServicesList.add(new FeaturesModel("Finance",mContext.getResources().getDrawable(R.drawable.ic_finance)));
        mServicesList.add(new FeaturesModel("Vehicle",mContext.getResources().getDrawable(R.drawable.ic_vehicle)));
        mServicesList.add(new FeaturesModel("Children & Beloved",mContext.getResources().getDrawable(R.drawable.ic_children)));
        mServicesList.add(new FeaturesModel("Function & Party",mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Repair",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Mahila Udyog",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Emergency",mContext.getResources().getDrawable(R.drawable.ic_emergency)));*/

        mServicesList.add(new FeaturesModel("Health", mContext.getResources().getDrawable(R.drawable.health)));
        mServicesList.add(new FeaturesModel("Home & Office", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Beauty & Lifestyle", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Finance", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Vehicle", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Children & Beloved", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Functions & Party", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Repair", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Mahila Udyog", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Emergency", mContext.getResources().getDrawable(R.drawable.ic_party)));
        return mServicesList;
    }


}
