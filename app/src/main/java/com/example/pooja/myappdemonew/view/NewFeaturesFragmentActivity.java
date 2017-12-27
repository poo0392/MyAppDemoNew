package com.example.pooja.myappdemonew.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.adapter.MyNtbAdapter;
import com.example.pooja.myappdemonew.view.fragment.ProfessionsFragment;
import com.example.pooja.myappdemonew.view.fragment.ServicesFragment;
import com.example.pooja.myappdemonew.view.fragment.StoresFragment;

/**
 * Created by POOJA on 12/11/2017.
 */

public class NewFeaturesFragmentActivity extends HomeActivity {

    Toolbar toolbar;
    public ViewPager mViewPager;
    TabLayout mTabLayout;
    String from;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.fragment_home_features, null, false);
        drawer.addView(contentView, 0);
        //setContentView(R.layout.fragment_home_features);
        setAppToolbar();
        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTootlbarTitle("All Categories");
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        // setHasOptionsMenu(true);
        attachViews();
        setListeners();

        initializeComp();
    }

    private void initializeComp() {
        mViewPager = (ViewPager) findViewById(R.id.vp_categories);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        //  mTabLayout.setTabTextColors(ColorStateList.valueOf(context.getResources().getColor(R.color.toobarTextColor)));

       /* if (from.equals("Services")) {
            mViewPager.setCurrentItem(0);
        } else if (from.equals("Shops")) {
            mViewPager.setCurrentItem(1);
        } else if (from.equals("Profession")) {
            mViewPager.setCurrentItem(2);
        }*/

    }

    // @Override
   /* public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_features, container, false);
        Bundle args = getArguments();
        if (args != null) {
            from = args.getString("from");
        }

        context = getActivity();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        setTootlbarTitle("All Categories");
        setHasOptionsMenu(true);
        attachViews(view);
        setClickListeners();

        return view;
    }*/


    private void setupViewPager(ViewPager ntb_viewpager) {

        MyNtbAdapter mAdapter = new MyNtbAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new ServicesFragment(), "Services");
        mAdapter.addFragment(new StoresFragment(), "Stores");
        mAdapter.addFragment(new ProfessionsFragment(), "Professions");
        // mViewPager.setAdapter(mAdapter);
        ntb_viewpager.setAdapter(mAdapter);
    }

    /*  @Override
      public void onResume() {
          super.onResume();
          // findViews(view);
          readBundle(getArguments());
      }
  */
    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            from = bundle.getString("from");
        }
    }

   /* public static NewFeaturesFragmentActivity newInstance(String value) {
        Bundle args = new Bundle();
        NewFeaturesFragmentActivity fragment = new NewFeaturesFragmentActivity();
        args.putString("from", value);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(NewFeaturesFragmentActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}
