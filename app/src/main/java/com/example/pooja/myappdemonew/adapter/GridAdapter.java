package com.example.pooja.myappdemonew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.model.FeaturesModel;

import java.util.ArrayList;

/**
 * Created by POOJA on 12/24/2017.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    ArrayList<FeaturesModel> mList;
    Context mContext;

    public GridAdapter(ArrayList<FeaturesModel> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.iv_item_photo.setImageDrawable(mList.get(position).getItem_icon());
        holder.tv_item_title.setText(mList.get(position).getItem_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item_photo;
        TextView tv_item_title;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_item_photo = (ImageView) itemView.findViewById(R.id.iv_item_photo);
            tv_item_title = (TextView) itemView.findViewById(R.id.tv_item_title);
        }
    }
}
