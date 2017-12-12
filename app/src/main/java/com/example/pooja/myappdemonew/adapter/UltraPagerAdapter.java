package com.example.pooja.myappdemonew.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;

import java.util.ArrayList;

/**
 * Created by Zafar.Hussain on 28/11/2017.
 */

public class UltraPagerAdapter extends PagerAdapter {
    private Context mContext;
    private String from;
    private ArrayList<Integer> mList;
    private boolean isMultiScr;
    private int[] image = {R.drawable.mall, R.drawable.building, R.drawable.garden,R.drawable.building};
    private int[] imageTwo = {R.drawable.offers_one, R.drawable.offers_two, R.drawable.offers_three, R.drawable.offers_four};
    private String[] title = {"Inaugaration of Mall", "New Project Development", "Green City Project", "New Project Development"};

    public UltraPagerAdapter(Context mContext, String from, boolean isMultiScr) {
        this.isMultiScr = isMultiScr;
        this.mContext = mContext;
        this.from = from;
    }

    public UltraPagerAdapter(boolean isMultiScr) {
        this.isMultiScr = isMultiScr;
    }

    @Override
    public int getCount() {
        if (from.equals("home")) {
            return imageTwo.length;
        } else {
            return image.length;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /*LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child, null);
        //new LinearLayout(container.getContext());
        TextView textView = (TextView) linearLayout.findViewById(R.id.pager_textview);
        textView.setText(position + "");
        linearLayout.setId(R.id.item_id);
        switch (position) {
            case 0:
                linearLayout.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
            case 1:
                linearLayout.setBackgroundColor(Color.parseColor("#673AB7"));
                break;
            case 2:
                linearLayout.setBackgroundColor(Color.parseColor("#009688"));
                break;
            case 3:
                linearLayout.setBackgroundColor(Color.parseColor("#607D8B"));
                break;
            case 4:
                linearLayout.setBackgroundColor(Color.parseColor("#F44336"));
                break;*/
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.swipe_layout, container, false);
        TextView tv_item_title = (TextView) itemView.findViewById(R.id.tv_item_title);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        if (from.equals("home")) {
            imageView.setImageResource(imageTwo[position]);
            tv_item_title.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(image[position]);
            tv_item_title.setText(title[position]);
        }
        container.addView(itemView);

        return itemView;
    }
       /* container.addView(linearLayout);
            //        linearLayout.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, container.getContext().getResources().getDisplayMetrics());
            //        linearLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, container.getContext().getResources().getDisplayMetrics());
        return linearLayout;*/

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       /* LinearLayout view = (LinearLayout) object;
        container.removeView(view);*/
        container.removeView((RelativeLayout) object);
    }
}