package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.ServicesAdapter;
import com.example.pooja.myappdemonew.adapter.VerticalListAdapter;
import com.example.pooja.myappdemonew.model.FeaturesModel;
import com.example.pooja.myappdemonew.utils.ClickListener;
import com.example.pooja.myappdemonew.utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by POOJA on 11/12/2017.
 */

public class ProfessionsFragment extends Fragment {
    private Context mContext;
    private RecyclerView mServicesRecycler;
    private GridLayoutManager mGrisdLayoutManager;
    private VerticalListAdapter mListAdapter;
    private ArrayList<FeaturesModel> mServicesList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        mContext = getActivity();
        attachView(view);
        return view;
    }

    private void attachView(View view) {
        mServicesRecycler = (RecyclerView) view.findViewById(R.id.services_recyclerview);
        mServicesRecycler.setHasFixedSize(true);
        //   mGrisdLayoutManager=new GridLayoutManager(mContext,3);

        mServicesRecycler.setLayoutManager(new LinearLayoutManager(mContext));


        mServicesList = addListItems();
        mListAdapter = new VerticalListAdapter(mContext, mServicesList);
        mServicesRecycler.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();


        mServicesRecycler.addOnItemTouchListener(new RecyclerTouchListener(mContext, mServicesRecycler, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                int pos = (int) mListAdapter.getItemId(position);
                System.out.println("position on recCLick: " + pos);

                FragmentTransaction propFrag = getActivity().getSupportFragmentManager().beginTransaction();
                propFrag.replace(R.id.content_frame, new HealthFragment());
                propFrag.commit();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    //Added list items
    private ArrayList<FeaturesModel> addListItems() {
        mServicesList=new ArrayList<>();
        mServicesList.add(new FeaturesModel("Graphic Designer",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Architect",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Astrologer",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Painter",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Photographer",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Care-taker",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Lawyer",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Civil Contractor",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Interior Designer",mContext.getResources().getDrawable(R.drawable.ic_health)));
        mServicesList.add(new FeaturesModel("Watchman",mContext.getResources().getDrawable(R.drawable.ic_health)));
        return mServicesList;
    }
}
