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
 * Created by POOJA on 11/12/2017.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    Context context;
    ArrayList<String> mServicesList;

    public ServicesAdapter(Context context, ArrayList<String> mServicesList) {
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
        holder.mTitle.setText(mServicesList.get(position));

    }
    @Override
    public long getItemId(int position) {
        //return super.getItemId(position);
        return position;
    }
    @Override
    public int getItemCount() {
        return mServicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.title_txtView);

        }
    }
}
