package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.GridAdapter;
import com.example.pooja.myappdemonew.adapter.MyNtbAdapter;
import com.example.pooja.myappdemonew.adapter.MyPagerAdapter;
import com.example.pooja.myappdemonew.adapter.UltraPagerAdapter;
import com.example.pooja.myappdemonew.model.FeaturesModel;
import com.example.pooja.myappdemonew.view.PolitianProfileActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;

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

    private String[] colors;
    //navigation tab bar details
    private NavigationTabBar navigationTabBar;
    private ViewPager vp_ntb;
    private ArrayList<NavigationTabBar.Model> models;
    private TextView txt;
    private EditText edt;
    Toolbar toolbar;
    TabLayout mTabLayout;
    LinearLayout cv_services, cv_shops, cv_profession;
    NewFeaturesFragment fragmentB;
    RecyclerView free_services_recyclerview;
    GridAdapter mGridAdapter;
    ArrayList<FeaturesModel> mGridItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        setTootlbarTitle("Home");


        //setHasOptionsMenu(false);
        mContext = getActivity();
        attachViews(view);
        setClickListeners();
        setUltraOffersViewPager(view);
        //setFlycoViewPager(view);
        //setHorizontalNtb(view);
        setWorksViewPager(view);

        return view;
    }

    private void setClickListeners() {
        cv_profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                //  Bundle args = new Bundle();
                //  args.putString("From", "Profession");
                fragmentB = NewFeaturesFragment.newInstance("Profession");
                tx.replace(R.id.content_frame, fragmentB);
                tx.addToBackStack(null);
                // tx.disallowAddToBackStack();
                tx.commit();


                /*Intent tx= new Intent(getActivity(),NewFeaturesFragmentActivity.class);
                tx.putExtra("From","Profession");
                getActivity().startActivity(tx);*/
            }
        });

        cv_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // NewFeaturesFragmentActivity fragmentB = new NewFeaturesFragmentActivity ();
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                // Bundle args = new Bundle();
                // args.putString("From", "Services");
                // fragmentB.setArguments(args);
                fragmentB = NewFeaturesFragment.newInstance("Services");
                tx.replace(R.id.content_frame, fragmentB);
                tx.addToBackStack(null);
                //tx.disallowAddToBackStack();
                tx.commit();

             /*   Intent tx= new Intent(getActivity(),NewFeaturesFragmentActivity.class);
                tx.putExtra("From","Services");
                getActivity().startActivity(tx);*/
            }
        });
        cv_shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // NewFeaturesFragmentActivity fragmentB = new NewFeaturesFragmentActivity ();
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                // Bundle args = new Bundle();
                //  args.putString("From", "Shops");
                //  fragmentB.setArguments(args);
                fragmentB = NewFeaturesFragment.newInstance("Shops");
                tx.replace(R.id.content_frame, fragmentB);
                tx.addToBackStack(null);
                //  tx.disallowAddToBackStack();
                tx.commit();

             /*   Intent tx= new Intent(getActivity(),NewFeaturesFragmentActivity.class);
                tx.putExtra("From","Shops");
                getActivity().startActivity(tx);*/
            }
        });
    }

    private void setTootlbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toobarTextColor));
    }

    private void setHorizontalNtb(final View view) {
        //  colors = mContext.getResources().getStringArray(R.array.default_preview);
        vp_ntb = (ViewPager) view.findViewById(R.id.vp_horizontal_ntb);
        setupViewPager(vp_ntb);
        //mPagerAdapter=new MyPagerAdapter(mContext);
        // vp_ntb.setAdapter(mPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(vp_ntb);

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
        mGridItemList = new ArrayList<>();
        free_services_recyclerview = (RecyclerView) view.findViewById(R.id.free_services_recyclerview);
        iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
        // tv_content = (TextView) view.findViewById(R.id.tv_work_content);

        cv_profession = (LinearLayout) view.findViewById(R.id.ll_profession);
        cv_shops = (LinearLayout) view.findViewById(R.id.ll_shop);
        cv_services = (LinearLayout) view.findViewById(R.id.ll_services);

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PolitianProfileActivity.class);
                startActivity(intent);
            }
        });
        free_services_recyclerview.setHasFixedSize(true);
        //   mGrisdLayoutManager=new GridLayoutManager(mContext,3);

        free_services_recyclerview.setLayoutManager(new GridLayoutManager(mContext, 4));


        mGridItemList = addListItems();
        mGridAdapter = new GridAdapter(mGridItemList, mContext);
        free_services_recyclerview.setAdapter(mGridAdapter);
        mGridAdapter.notifyDataSetChanged();
    }

    private ArrayList<FeaturesModel> addListItems() {
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.ic_profession)));
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.notification)));
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.ic_home)));
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.ic_offers)));
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.ic_profile)));
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.ic_party)));
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.ic_offers)));
        mGridItemList.add(new FeaturesModel("Govt Schools", getResources().getDrawable(R.drawable.ic_health)));

        return mGridItemList;
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
        mAdapter.addFragment(new ServicesFragment(), "Services");
        mAdapter.addFragment(new StoresFragment(), "Stores");
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
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
        //set the alignment
        offersUtViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        offersUtViewPager.getIndicator().setMargin(0, 0, 0, 10);
        //cons
        // truct built-in indicator, and add it to  UltraViewPager
        offersUtViewPager.getIndicator().build();

        //set an infinite loop
        offersUtViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        offersUtViewPager.setAutoScroll(8000);


    }


}
