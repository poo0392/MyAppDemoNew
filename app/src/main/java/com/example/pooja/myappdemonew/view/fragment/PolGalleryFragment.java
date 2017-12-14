package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.GallerAdapter;
import com.example.pooja.myappdemonew.model.PolGalleryModel;

import java.util.ArrayList;

/**
 * Created by Zafar.Hussain on 12/12/2017.
 */

public class PolGalleryFragment extends Fragment {
    RecyclerView galleryRecyclerView;
    Context mContext;
    GridLayoutManager mGrisdLayoutManager;
    ArrayList<PolGalleryModel> mGalleryList;
    GallerAdapter mGalleryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pol_gal_story, container, false);
        mContext=getActivity();
        attachViews(view);
        return view;
    }

    private void attachViews(View view) {
        galleryRecyclerView = (RecyclerView)view.findViewById(R.id.pol_frag_recyclerview);
        galleryRecyclerView.setHasFixedSize(true);
        galleryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mGrisdLayoutManager=new GridLayoutManager(mContext,2);
        galleryRecyclerView.setLayoutManager(mGrisdLayoutManager);
        addItems();
        mGalleryAdapter = new GallerAdapter(mContext, mGalleryList);
        galleryRecyclerView.setAdapter(mGalleryAdapter);
        mGalleryAdapter.notifyDataSetChanged();
    }

    private void addItems() {
        mGalleryList=new ArrayList<>();

        mGalleryList.add(new PolGalleryModel(mContext.getResources().getDrawable(R.drawable.building)));
        mGalleryList.add(new PolGalleryModel(mContext.getResources().getDrawable(R.drawable.mall)));
        mGalleryList.add(new PolGalleryModel(mContext.getResources().getDrawable(R.drawable.garden)));
        mGalleryList.add(new PolGalleryModel(mContext.getResources().getDrawable(R.drawable.building)));

    }
}
