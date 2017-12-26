package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.ServicesAdapter;
import com.example.pooja.myappdemonew.model.FeaturesModel;
import com.example.pooja.myappdemonew.utils.ClickListener;
import com.example.pooja.myappdemonew.utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by POOJA on 11/12/2017.
 */

public class StoresFragment extends Fragment {
    private Context mContext;
    private RecyclerView mServicesRecycler;
    private GridLayoutManager mGrisdLayoutManager;
    private ServicesAdapter mServicesAdapter;
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
                switch (pos) {
                    case 0:
                       /* Intent intent = new Intent(getActivity(), HealthActivity.class);
                        startActivity(intent);*/
                       /* FragmentTransaction propFrag = getActivity().getSupportFragmentManager().beginTransaction();
                        propFrag.replace(R.id.content_frame, new HealthFragment());
                        propFrag.commit();*/
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    //Added list items
    private ArrayList<FeaturesModel> addListItems() {
        mServicesList = new ArrayList<>();
        mServicesList.add(new FeaturesModel("Need for home", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Food & Beverages", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Interior", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Appliances", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Lifestyle", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Collections", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Education", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        mServicesList.add(new FeaturesModel("Wholesellers", mContext.getResources().getDrawable(R.drawable.ic_party)));
        mServicesList.add(new FeaturesModel("Health", mContext.getResources().getDrawable(R.drawable.ic_profession)));
        return mServicesList;
    }
}
