package com.example.pooja.myappdemonew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.pooja.myappdemonew.R;

/**
 * Created by Zafar.Hussain on 18/12/2017.
 */

public class BaseActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);


      /*  bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;
                        switch (item.getItemId()) {
                            case R.id.ll_notification:
                                // callNotificationFragment();
                                fragment = new NotificationFragment();
                                setActiveImageColor(iv_notification, iv_inbox, iv_profile, iv_home, iv_offers);
                                setActiveTextColor(txt_notification, txt_inbox, txt_profile, txt_home, txt_offers);
                                break;

                            case R.id.ll_home:
                                // callHomeFragment();
                                fragment = new HomeFragment();
                                setActiveImageColor(iv_home, iv_inbox, iv_profile, iv_offers, iv_notification);
                                setActiveTextColor(txt_home, txt_inbox, txt_profile, txt_offers, txt_notification);
                                break;

                            case R.id.ll_offers:
                                // callOffersFragment();
                                fragment = new OffersFragment();
                                setActiveImageColor(iv_offers, iv_inbox, iv_home, iv_notification, iv_profile);
                                setActiveTextColor(txt_offers, txt_inbox, txt_home, txt_notification, txt_profile);
                                break;

                            case R.id.ll_profile:
                                // callProfileFragment();
                                fragment = new MyProfileFragment();
                                setActiveImageColor(iv_profile, iv_home, iv_offers, iv_inbox, iv_notification);
                                setActiveTextColor(txt_profile, txt_home, txt_offers, txt_inbox, txt_notification);
                                break;
                            case R.id.ll_inbox:
                                //callInboxFragment();
                                fragment = new InboxFragment();
                                setActiveImageColor(iv_inbox, iv_profile, iv_home, iv_offers, iv_notification);
                                setActiveTextColor(txt_inbox, txt_profile, txt_home, txt_offers, txt_notification);
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, fragment);
                        transaction.commit();
                        return true;
                    }
                });*/
    }
}
