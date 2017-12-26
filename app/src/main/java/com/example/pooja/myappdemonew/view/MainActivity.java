package com.example.pooja.myappdemonew.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pooja.myappdemonew.R;

public class MainActivity extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setAppToolbar();
        attachViews();
        setListeners();
        callHomeFragment();
    }
}
