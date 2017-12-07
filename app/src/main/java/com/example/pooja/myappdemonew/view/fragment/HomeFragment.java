package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.MyPagerAdapter;
import com.example.pooja.myappdemonew.adapter.UltraPagerAdapter;
import com.example.pooja.myappdemonew.view.HomeActivity;
import com.example.pooja.myappdemonew.view.PolitianProfileActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by POOJA on 11/11/2017.
 */

public class HomeFragment extends Fragment {
    private Context mContext;
    private MyPagerAdapter mPagerAdapter;
    private ViewPager vpPager;
    private CircleIndicator titleIndicator;
    private ViewPager flycoViewpager;
    private UltraViewPager offersUtViewPager, worksUtViewPager;
    private UltraPagerAdapter adapter;
    private ImageView iv_photo;
    private TextView tv_content;
    private SlidingTabLayout tabLayout;
    private final String[] mTitles = {"Services For You", "Shop-Stores", "Professions"};
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] colors;
    //navigation tab bar details
    private NavigationTabBar navigationTabBar;
    private ViewPager vp_ntb;
    private ArrayList<NavigationTabBar.Model> models;
    private TextView txt;
    private EditText edt;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        //setHasOptionsMenu(false);
        mContext = getActivity();
        attachViews(view);
        setUltraOffersViewPager(view);
        //setFlycoViewPager(view);
        setHorizontalNtb(view);
        setWorksViewPager(view);

        return view;
    }

    private void setHorizontalNtb(final View view) {
      //  colors = mContext.getResources().getStringArray(R.array.default_preview);
        vp_ntb = (ViewPager) view.findViewById(R.id.vp_horizontal_ntb);
        setupViewPager(vp_ntb);
        //mPagerAdapter=new MyPagerAdapter(mContext);
        // vp_ntb.setAdapter(mPagerAdapter);

        navigationTabBar = (NavigationTabBar) view.findViewById(R.id.ntb_horizontal);
        models = new ArrayList<>();
        addNtbListItems();
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(vp_ntb);

       /* navigationTabBar.post(new Runnable() {
            @Override
            public void run() {
                final View viewPager = view.findViewById(R.id.vp_horizontal_ntb);
                ((ViewGroup.MarginLayoutParams) viewPager.getLayoutParams()).topMargin =
                        (int) -navigationTabBar.getBadgeMargin();
                viewPager.requestLayout();
            }
        });*/


    }

    private void addNtbListItems() {
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_services),
                        mContext.getResources().getColor(R.color.colorAccent))
                        .title("Services")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_stores),
                        mContext.getResources().getColor(R.color.colorAccent))
                        .title("Stores")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_profession),
                        mContext.getResources().getColor(R.color.colorAccent))
                        .title("Profession")
                        .build()
        );
    }

    private void attachViews(View view) {
        iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
        tv_content = (TextView) view.findViewById(R.id.tv_work_content);

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PolitianProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setWorksViewPager(View view) {
        worksUtViewPager = (UltraViewPager) view.findViewById(R.id.ultra_photo_vp);
        worksUtViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //initialize UltraPagerAdapter，and add child view to UltraViewPager
        //  adapter = new UltraPagerAdapter(false);
        adapter = new UltraPagerAdapter(mContext, "works", false);
        worksUtViewPager.setAdapter(adapter);
        //set an infinite loop
        worksUtViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        worksUtViewPager.setAutoScroll(4000);
    }


    private void setupViewPager(ViewPager ntb_viewpager) {

        MyNtbAdapter mAdapter = new MyNtbAdapter(getFragmentManager());
        mAdapter.addFragment(new ServicesFragment(), "Services For You");
        mAdapter.addFragment(new ShopStoresFragment(), "Shop-Stores");
        mAdapter.addFragment(new ProfessionsFragment(), "Professions");
        // mViewPager.setAdapter(mAdapter);
        ntb_viewpager.setAdapter(mAdapter);
    }

    private void setUltraOffersViewPager(View view) {
        offersUtViewPager = (UltraViewPager) view.findViewById(R.id.ultra_viewpager);
        offersUtViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //initialize UltraPagerAdapter，and add child view to UltraViewPager
        //  adapter = new UltraPagerAdapter(false);
        adapter = new UltraPagerAdapter(mContext, "home", false);
        offersUtViewPager.setAdapter(adapter);

        //initialize built-in indicator
        offersUtViewPager.initIndicator();
        //set style of indicators
        offersUtViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(getResources().getColor(R.color.colorAccent))
                .setNormalColor(getResources().getColor(R.color.LightGrey))
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //set the alignment
        offersUtViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //cons
        // truct built-in indicator, and add it to  UltraViewPager
        offersUtViewPager.getIndicator().build();

        //set an infinite loop
        offersUtViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        offersUtViewPager.setAutoScroll(3000);

    }


    private class MyNtbAdapter extends FragmentPagerAdapter {

        public MyNtbAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitleList.add(title);
        }

       /* @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }*/
    }

    //removed flycotab
    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.txt.setText(String.format("Navigation Item #%d", position));
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txt;

            public ViewHolder(final View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(R.id.txt_vp_item_list);
            }
        }
    }
}
