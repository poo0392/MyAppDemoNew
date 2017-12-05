package com.example.pooja.myappdemonew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;

import java.util.ArrayList;

/**
 * Created by Zafar.Hussain on 01/12/2017.
 */

public class HealthSubItemRecyclerAdapter extends RecyclerView.Adapter<HealthSubItemRecyclerAdapter.ViewHolder> {
    Context mContext;
    ArrayList<String> mList;

    public HealthSubItemRecyclerAdapter(Context mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_health_sub, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_name.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;

        public ViewHolder(View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.tv_sub_item_name);
        }
    }
}
