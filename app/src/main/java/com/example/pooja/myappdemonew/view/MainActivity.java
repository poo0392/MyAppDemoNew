package com.example.pooja.myappdemonew.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.view.fragment.HomeFragment;

public class MainActivity extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setAppToolbar();
        attachViews();
        setListeners();
      //  callHomeFragment();

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new HomeFragment());
        tx.commit();
    }
}
