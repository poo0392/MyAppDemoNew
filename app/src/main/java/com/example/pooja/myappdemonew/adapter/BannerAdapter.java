package com.example.pooja.myappdemonew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.model.BannerListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zafar.Hussain on 28/11/2017.
 */

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    Context mContext;
    List<BannerListModel> mList;

    public BannerAdapter(Context mContext, ArrayList<BannerListModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_banner_new, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_content.setText(mList.get(position).getContent());
        holder.iv_photo.setImageDrawable(mList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_content;
        ImageView iv_photo;

        public ViewHolder(View itemView) {
            super(itemView);

           // tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);

           /* UltraViewPager ultraViewPager = (UltraViewPager)itemView.findViewById(R.id.list_item_viewpager);
            ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
            //initialize UltraPagerAdapter，and add child view to UltraViewPager
            PagerAdapter adapter = new UltraPagerAdapter(false);
            ultraViewPager.setAdapter(adapter);

           *//* //initialize built-in indicator
            ultraViewPager.initIndicator();
            //set style of indicators
            ultraViewPager.getIndicator()
                    .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                    .setFocusColor(Color.GREEN)
                    .setNormalColor(Color.WHITE)
                    .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, mContext.getResources().getDisplayMetrics()));
            //set the alignment
            ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
            //construct built-in indicator, and add it to  UltraViewPager
            ultraViewPager.getIndicator().build();*//*

            //set an infinite loop
            ultraViewPager.setInfiniteLoop(true);
                //enable auto-scroll mode
                    ultraViewPager.setAutoScroll(2000);*/

        }
    }
}
