package com.example.pooja.myappdemonew.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.ServicesAdapter;
import com.example.pooja.myappdemonew.utils.ClickListener;
import com.example.pooja.myappdemonew.utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by POOJA on 11/12/2017.
 */

public class ServicesFragment extends Fragment {
    private Context mContext;
    private RecyclerView mServicesRecycler;
    private GridLayoutManager mGrisdLayoutManager;
    private ServicesAdapter mServicesAdapter;
    private ArrayList<String> mServicesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);
      //  Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //toolbar.setTitle("Home");
        //setHasOptionsMenu(false);
        mContext = getActivity();
        attachView(view);
        return view;
    }

    private void attachView(View view) {
        mServicesRecycler=(RecyclerView)view.findViewById(R.id.services_recyclerview);
        mServicesRecycler.setHasFixedSize(true);
        mGrisdLayoutManager=new GridLayoutManager(mContext,4);
        mServicesRecycler.setLayoutManager(mGrisdLayoutManager);


        mServicesList= addListItems();
        mServicesAdapter=new ServicesAdapter(mContext,mServicesList);
        mServicesRecycler.setAdapter(mServicesAdapter);
        mServicesAdapter.notifyDataSetChanged();


        mServicesRecycler.addOnItemTouchListener(new RecyclerTouchListener(mContext, mServicesRecycler, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                int pos = (int) mServicesAdapter.getItemId(position);
                System.out.println("position on recCLick: " + pos);
                switch (pos) {
                    case 0:
                        Intent intent = new Intent(getActivity(), HealthActivity.class);
                        startActivity(intent);
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

    private ArrayList<String> addListItems() {
        mServicesList=new ArrayList<>();
        mServicesList.add("Health");
        mServicesList.add("Home & Office");
        mServicesList.add("Beauty & Fashion");
        mServicesList.add("Vehicle");
        mServicesList.add("Finance");
        mServicesList.add("Emergency");
        mServicesList.add("Children & beloved");
        mServicesList.add("Function & party");
        return mServicesList;
    }
}
