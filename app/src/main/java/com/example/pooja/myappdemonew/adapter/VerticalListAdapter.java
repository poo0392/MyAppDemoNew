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

public class VerticalListAdapter extends RecyclerView.Adapter<VerticalListAdapter.ViewHolder>  {
    Context context;
    ArrayList<FeaturesModel> mServicesList;
    String[] colors;

    public VerticalListAdapter(Context context, ArrayList<FeaturesModel> mServicesList) {
        this.context = context;
        this.mServicesList = mServicesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_services, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(mServicesList.get(position).getItem_name());
        holder.itemIcon.setImageDrawable(mServicesList.get(position).getItem_icon());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mServicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        ImageView itemIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = (TextView) itemView.findViewById(R.id.title_txtView);
            itemIcon = (ImageView) itemView.findViewById(R.id.holder_img);

        }
    }
}
