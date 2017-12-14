package com.example.pooja.myappdemonew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.model.PolGalleryModel;

import java.util.ArrayList;

/**
 * Created by Zafar.Hussain on 14/12/2017.
 */

public class GallerAdapter extends RecyclerView.Adapter<GallerAdapter.ViewHolder> {
    Context mContext;
    ArrayList<PolGalleryModel> mGalleryList;

    public GallerAdapter(Context mContext, ArrayList<PolGalleryModel> mGalleryList) {
        this.mContext = mContext;
        this.mGalleryList = mGalleryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gallery, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.gallery_item.setImageDrawable(mGalleryList.get(position).images);
    }

    @Override
    public int getItemCount() {
        return mGalleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gallery_item;
        public ViewHolder(View itemView) {
            super(itemView);

            gallery_item=(ImageView)itemView.findViewById(R.id.gallery_item);


        }
    }
}
