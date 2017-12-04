package com.example.pooja.myappdemonew.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.utils.SessionManager;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private SessionManager session;

    private LinearLayout ll_home, ll_profile, ll_notification, ll_offers, ll_linBase;
    private ImageView iv_profile, iv_offers, iv_notification, iv_home;
    private TextView txt_home, txt_profile, txt_notification, txt_offers;
    private SearchView simpleSearchView;
    private RelativeLayout search_layout;
    private Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new HomeFragment());
        tx.commit();

        setAppToolbar();

        attachViews();
        setListeners();

    }
//aded Listenerss
    private void setListeners() {
        ll_notification.setOnClickListener(this);
        ll_home.setOnClickListener(this);
        ll_profile.setOnClickListener(this);
        ll_offers.setOnClickListener(this);

        // perform set on query text listener event
       /* simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
                return false;
            }
        });*/
    }

    private void attachViews() {
        // Session manager
        session = new SessionManager(getApplicationContext());

        //  simpleSearchView = (SearchView) findViewById(R.id.search_view); // inititate a search view
        // search_layout = (RelativeLayout) findViewById(R.id.search_layout);
        //   CharSequence query = simpleSearchView.getQuery();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        iv_notification = (ImageView) findViewById(R.id.iv_notification);
        iv_offers = (ImageView) findViewById(R.id.iv_offers);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        iv_home = (ImageView) findViewById(R.id.iv_home);

        txt_notification = (TextView) findViewById(R.id.txt_notification);
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_profile = (TextView) findViewById(R.id.txt_profile);
        txt_offers = (TextView) findViewById(R.id.txt_offers);

        ll_linBase = (LinearLayout) findViewById(R.id.ll_linBase);

        ll_offers = (LinearLayout) findViewById(R.id.ll_offers);
        ll_home = (LinearLayout) findViewById(R.id.ll_home);
        ll_profile = (LinearLayout) findViewById(R.id.ll_profile);
        ll_notification = (LinearLayout) findViewById(R.id.ll_notification);
    }

    private void setAppToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //ActivityCompat.invalidateOptionsMenu(HomeActivity.this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            ActivityCompat.finishAffinity(HomeActivity.this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        myMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean newViewFocus)
            {
                if (!newViewFocus)
                {
                    //Collapse the action item.
                    searchItem.collapseActionView();
                    //Clear the filter/search query.
                    menu.findItem(R.id.action_search).setVisible(true);
                    menu.findItem(R.id.action_notificattion).setVisible(true);
                    menu.findItem(R.id.action_profile).setVisible(true);
                }else{
                    menu.findItem(R.id.action_notificattion).setVisible(false);
                    menu.findItem(R.id.action_profile).setVisible(false);
                }
            }
        });
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notificattion) {
            return true;
        } else if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            logoutUser();
        } else if (id == R.id.nav_home) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, new HomeFragment());
            tx.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     */
    private void logoutUser() {
        session.setLogin(false);


        // Launching the login activity
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_notification:
                setActiveImageColor(iv_notification, iv_profile, iv_home, iv_offers);
                setActiveTextColor(txt_notification, txt_home, txt_profile, txt_offers);



                /*FragmentTransaction custFrag = getSupportFragmentManager().beginTransaction();
                custFrag.replace(R.id.content_frame, new CustomerListFragment());
                custFrag.commit();*/
                break;

            case R.id.ll_home:
                setActiveImageColor(iv_home, iv_profile, iv_offers, iv_notification);

                setActiveTextColor(txt_home, txt_notification, txt_profile, txt_offers);

                FragmentTransaction dashFrag = getSupportFragmentManager().beginTransaction();
                dashFrag.replace(R.id.content_frame, new HomeFragment());
                dashFrag.commit();
                break;

            case R.id.ll_offers:
                setActiveImageColor(iv_offers, iv_profile, iv_home, iv_notification);

                setActiveTextColor(txt_offers, txt_home, txt_notification, txt_profile);

             /*   FragmentTransaction reportsFrag = getSupportFragmentManager().beginTransaction();
                reportsFrag.replace(R.id.content_frame, new ReportsFragment());
                reportsFrag.commit();*/
                break;

            case R.id.ll_profile:

                setActiveImageColor(iv_profile, iv_home, iv_offers, iv_notification);
                setActiveTextColor(txt_profile, txt_offers, txt_home, txt_notification);
/*
                FragmentTransaction propFrag = getSupportFragmentManager().beginTransaction();
                propFrag.replace(R.id.content_frame, new PropertyListFragment());
                propFrag.commit();*/
                break;

        }
    }

    private void setActiveTextColor(TextView activeText, TextView inactiveText1, TextView inactiveText2, TextView inactiveText3) {
        activeText.setTextColor(getResources().getColor(R.color.colorPrimary));
        inactiveText1.setTextColor(getResources().getColor(R.color.md_grey_500));
        inactiveText2.setTextColor(getResources().getColor(R.color.md_grey_500));
        inactiveText3.setTextColor(getResources().getColor(R.color.md_grey_500));
        inactiveText2.setTextColor(getResources().getColor(R.color.md_grey_500));
    }

    private void setActiveImageColor(ImageView activeImg, ImageView inactiveImg1, ImageView inactiveImg2, ImageView inactiveImg3) {

        activeImg.setColorFilter(getResources().getColor(R.color.colorPrimary));
        inactiveImg1.setColorFilter(getResources().getColor(R.color.md_grey_500));
        inactiveImg2.setColorFilter(getResources().getColor(R.color.md_grey_500));
        inactiveImg3.setColorFilter(getResources().getColor(R.color.md_grey_500));

    }
}
