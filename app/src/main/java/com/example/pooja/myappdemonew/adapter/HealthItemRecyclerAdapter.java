package com.example.pooja.myappdemonew.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.model.HealthItemModel;

import java.util.ArrayList;

import hyogeun.github.com.colorratingbarlib.ColorRatingBar;

/**
 * Created by Zafar.Hussain on 05/12/2017.
 */

public class HealthItemRecyclerAdapter extends RecyclerView.Adapter<HealthItemRecyclerAdapter.ViewHolder> {
    Context mContext;
    ArrayList<HealthItemModel> mArrList;

    public HealthItemRecyclerAdapter(Context mContext, ArrayList<HealthItemModel> mArrList) {
        this.mContext = mContext;
        this.mArrList = mArrList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_health_items, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvItemName.setText(mArrList.get(position).getItemName());
        holder.ivItemPhoto.setImageDrawable(mArrList.get(position).getItemPhoto());
        holder.tvAddress.setText(mArrList.get(position).getAddress());
      //  holder.colorRatingBar.setRating(Float.parseFloat(mArrList.get(position).getRating()));
        holder.mRatingBar.setRating(Float.parseFloat(mArrList.get(position).getRating()));
        holder.tvViewOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.tvOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //will show popup menu here
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, holder.tvOptions);
                //inflating menu from xml resource
                popup.inflate(R.menu.health_option_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemPhoto, ivFavorite;
        TextView tvItemName, tvOptions, tvAddress, tvViewOffers;
        RatingBar mRatingBar;
        ColorRatingBar colorRatingBar;


        public ViewHolder(View itemView) {
            super(itemView);

            ivItemPhoto = (ImageView) itemView.findViewById(R.id.iv_item_photo);
            ivFavorite = (ImageView) itemView.findViewById(R.id.iv_favorite);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            tvOptions = (TextView) itemView.findViewById(R.id.tv_options);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_item_address);
            tvViewOffers = (TextView) itemView.findViewById(R.id.tv_offer);

            mRatingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);

           // colorRatingBar = new ColorRatingBar(mContext);
            colorRatingBar=(ColorRatingBar)itemView.findViewById(R.id.rating_1);
            colorRatingBar.setMax(5);

           // colorRatingBar.setRatingProgressColor(R.color.LightGrey);
           // colorRatingBar.setRatingEmptyColor(R.color.colorAccent);
            //colorRatingBar.setRating(3.0f);
        }
    }
}
