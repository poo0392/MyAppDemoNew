package com.example.pooja.myappdemonew.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.model.FeaturesModel;
import com.example.pooja.myappdemonew.view.fragment.HealthFragment;

import java.util.ArrayList;

/**
 * Created by POOJA on 11/12/2017.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    Context context;
    ArrayList<FeaturesModel> mServicesList;
    String[] colors;

    public ServicesAdapter(Context context, ArrayList<FeaturesModel> mServicesList) {
        this.context = context;
        this.mServicesList = mServicesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_grid_item, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //colors = context.getResources().getStringArray(R.array.services_icon_color);
        holder.itemTitle.setText(mServicesList.get(position).getItem_name());
        holder.itemIcon.setImageDrawable(mServicesList.get(position).getItem_icon());
        //holder.itemIcon.setColorFilter(String.valueOf(colors[position]));
      //  holder.itemIcon.setColorFilter(ContextCompat.getColor(context, Color.parseColor(colors[position])), android.graphics.PorterDuff.Mode.MULTIPLY);
      //  holder.itemTitle.setTextColor(Integer.parseInt(colors[position]));

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
        TextView itemTitle;
        ImageView itemIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            itemIcon = (ImageView) itemView.findViewById(R.id.iv_item_photo);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction propFrag = getSupportFragmentManager().beginTransaction();
                    propFrag.replace(R.id.content_frame, new HealthFragment());
                    propFrag.commit();
                }
            });*/

        }
    }
}
