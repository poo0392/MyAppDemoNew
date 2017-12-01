package com.example.pooja.myappdemonew.view;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.MyPagerAdapter;
import com.example.pooja.myappdemonew.adapter.UltraPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
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
        colors = mContext.getResources().getStringArray(R.array.default_preview);
        vp_ntb = (ViewPager) view.findViewById(R.id.vp_horizontal_ntb);
        setupViewPager(vp_ntb);
        //mPagerAdapter=new MyPagerAdapter(mContext);
       // vp_ntb.setAdapter(mPagerAdapter);

        navigationTabBar = (NavigationTabBar) view.findViewById(R.id.ntb_horizontal);
        models = new ArrayList<>();
        addNtbListItems();
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(vp_ntb);

      /*  vp_ntb.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_vp_list, null, false);

                final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(new RecycleAdapter());

                container.addView(view);
                return view;
            }
        });*/




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
                        Color.parseColor(colors[0]))
                        .title("Services")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_stores),
                        Color.parseColor(colors[1]))
                        .title("Stores")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_profession),
                        Color.parseColor(colors[2]))
                        .title("Profession")
                        .build()
        );
    }

    private void attachViews(View view) {
        iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
        tv_content = (TextView) view.findViewById(R.id.tv_work_content);

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
        worksUtViewPager.setAutoScroll(2000);
    }

    private void setFlycoViewPager(View view) {
        flycoViewpager = (ViewPager) view.findViewById(R.id.flyco_tab_vp);
        setupViewPager(flycoViewpager);

        //  TabLayout tb=(TabLayout) view.findViewById(R.id.tabLayout);
        //  tb.setupWithViewPager(flycoViewpager);

        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tl_4);
        tabLayout.setViewPager(flycoViewpager);
        tabLayout.notifyDataSetChanged();
        //tabLayout.setOnClickListener();

        // tabLayout.setupWithViewPager(flycoViewpager);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //flycoViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        /*flycoViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
                // tabLayout.updateTabSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

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
     /*   mPagerAdapter = new MyPagerAdapter(mContext);
        vpPager = (ViewPager) view.findViewById(R.id.vpPager);
        vpPager.setAdapter(mPagerAdapter);
        titleIndicator = (CircleIndicator) view.findViewById(R.id.indicator);
       // titleIndicator.configureIndicator(20, 20, 5);
        titleIndicator.setUltraOffersViewPager(vpPager);


        vpPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);
                } else if (position <= 1) { // Page to the left, page centered, page to the right
                    // modify page view animations here for pages in view
                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });*/
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
                .setFocusColor(Color.WHITE)
                .setNormalColor(getResources().getColor(R.color.LightGrey))
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //set the alignment
        offersUtViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //construct built-in indicator, and add it to  UltraViewPager
        offersUtViewPager.getIndicator().build();

        //set an infinite loop
        offersUtViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        offersUtViewPager.setAutoScroll(2000);

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
