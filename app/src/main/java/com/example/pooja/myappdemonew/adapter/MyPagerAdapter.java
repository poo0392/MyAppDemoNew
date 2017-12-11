package com.example.pooja.myappdemonew.adapter;



import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.pooja.myappdemonew.R;

public class MyPagerAdapter extends PagerAdapter{
	private int[] image={R.drawable.offers_one,R.drawable.offers_two,R.drawable.offers_three,R.drawable.offers_four};
	private Context ctx;
	private LayoutInflater mLayoutInflater;

	 public MyPagerAdapter(Context ctx) {
		 this.ctx=ctx;
		 mLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
	}
	@Override
	public int getCount() {
		return image.length;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return "Page " + position;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		  return view == ((LinearLayout) object);
	}
	
	 @Override
	    public Object instantiateItem(ViewGroup container, int position) {
	        View itemView = mLayoutInflater.inflate(R.layout.swipe_layout, container, false);
	 
	        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
	        imageView.setImageResource(image[position]);
	 
	        container.addView(itemView);
	 
	        return itemView;
	    }
	 
	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	        container.removeView((LinearLayout) object);
	    }

}
