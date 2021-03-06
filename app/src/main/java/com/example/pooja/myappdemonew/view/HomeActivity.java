package com.example.pooja.myappdemonew.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pooja.myappdemonew.R;
import com.example.pooja.myappdemonew.utils.SessionManager;
import com.example.pooja.myappdemonew.view.fragment.HomeFragment;
import com.example.pooja.myappdemonew.view.fragment.InboxFragment;
import com.example.pooja.myappdemonew.view.fragment.MyProfileFragment;
import com.example.pooja.myappdemonew.view.fragment.NewFeaturesFragment;
import com.example.pooja.myappdemonew.view.fragment.NotificationFragment;
import com.example.pooja.myappdemonew.view.fragment.OffersFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.pooja.myappdemonew.utils.Globals.back_press_screen;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private SessionManager session;

    private LinearLayout ll_home, ll_profile, ll_notification, ll_offers, ll_linBase, ll_inbox, ll_custom_search;
    private ImageView iv_profile, iv_offers, iv_notification, iv_home, iv_inbox, photo_imageView;
    CircleImageView profile_image;
    private TextView txt_home, txt_profile, txt_notification, txt_offers, txt_inbox, txt_name, txt_email;
    private SearchView simpleSearchView;
    private RelativeLayout search_layout;
    private Menu myMenu;
    protected DrawerLayout drawer;
    private String loginFrom, personName, email,personPhotoUrl;
    GoogleApiClient mGoogleApiClient;
    ActionBarDrawerToggle toggle;
    Fragment fragment;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setAppToolbar();
        setToggle();
        attachViews();
        setListeners();
        callHomeFragment();
        // setUpBottomNav();
    }

   /* private void setUpBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.notifications:
                                // callNotificationFragment();
                                fragment = new NotificationFragment();
                                setActiveImageColor(iv_notification, iv_inbox, iv_profile, iv_home, iv_offers);
                                setActiveTextColor(txt_notification, txt_inbox, txt_profile, txt_home, txt_offers);
                                break;

                            case R.id.home:
                                // callHomeFragment();
                                fragment = new HomeFragment();
                                setActiveImageColor(iv_home, iv_inbox, iv_profile, iv_offers, iv_notification);
                                setActiveTextColor(txt_home, txt_inbox, txt_profile, txt_offers, txt_notification);
                                break;

                            case R.id.offers:
                                // callOffersFragment();
                                fragment = new OffersFragment();
                                setActiveImageColor(iv_offers, iv_inbox, iv_home, iv_notification, iv_profile);
                                setActiveTextColor(txt_offers, txt_inbox, txt_home, txt_notification, txt_profile);
                                break;

                            case R.id.my_profile:
                                // callProfileFragment();
                                fragment = new MyProfileFragment();
                                setActiveImageColor(iv_profile, iv_home, iv_offers, iv_inbox, iv_notification);
                                setActiveTextColor(txt_profile, txt_home, txt_offers, txt_inbox, txt_notification);
                                break;
                            case R.id.inbox:
                                //callInboxFragment();
                                fragment = new InboxFragment();
                                setActiveImageColor(iv_inbox, iv_profile, iv_home, iv_offers, iv_notification);
                                setActiveTextColor(txt_inbox, txt_profile, txt_home, txt_offers, txt_notification);
                                break;
                        }
                        if (fragment != null) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, fragment);
                            ft.commit();
                        }
                        return true;
                    }
                });
    }*/

    //aded Listenerss
    public void setListeners() {
        ll_notification.setOnClickListener(this);
        ll_home.setOnClickListener(this);
        ll_profile.setOnClickListener(this);
        ll_offers.setOnClickListener(this);
        ll_inbox.setOnClickListener(this);
        //  ll_custom_search.setOnClickListener(this);

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

    public void attachViews() {

        loginFrom = getIntent().getStringExtra("loginFrom");


        // Session manager
        session = new SessionManager(getApplicationContext());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //  simpleSearchView = (SearchView) findViewById(R.id.search_view); // inititate a search view
        // search_layout = (RelativeLayout) findViewById(R.id.search_layout);
        //   CharSequence query = simpleSearchView.getQuery();

        // bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        profile_image = (CircleImageView)header.findViewById(R.id.profile_image);
        photo_imageView = (ImageView)header.findViewById(R.id.photo_imageView);
        txt_name = (TextView)header.findViewById(R.id.txt_name);
        txt_email = (TextView)header.findViewById(R.id.txt_email);

        iv_notification = (ImageView) findViewById(R.id.iv_notification);
        iv_offers = (ImageView) findViewById(R.id.iv_offers);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_inbox = (ImageView) findViewById(R.id.iv_inbox);

        txt_notification = (TextView) findViewById(R.id.txt_notification);
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_profile = (TextView) findViewById(R.id.txt_profile);
        txt_offers = (TextView) findViewById(R.id.txt_offers);
        txt_inbox = (TextView) findViewById(R.id.txt_inbox);

        ll_linBase = (LinearLayout) findViewById(R.id.ll_linBase);
        ll_custom_search = (LinearLayout) findViewById(R.id.ll_custom_search);

        ll_offers = (LinearLayout) findViewById(R.id.ll_offers);
        ll_home = (LinearLayout) findViewById(R.id.ll_home);
        ll_profile = (LinearLayout) findViewById(R.id.ll_profile);
        ll_notification = (LinearLayout) findViewById(R.id.ll_notification);
        ll_inbox = (LinearLayout) findViewById(R.id.ll_inbox);
        try {
            GoogleSignInAccount account = getIntent().getParcelableExtra("ACCOUNT");

            personName = account.getDisplayName();
            personPhotoUrl = account.getPhotoUrl().toString();
            email = account.getEmail();


            Log.e("getIntent ", "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);


        } catch (Exception e) {
            e.printStackTrace();
        }
        txt_name.setText(personName);
        txt_email.setText(email);
        Picasso.with(HomeActivity.this).load(personPhotoUrl).into(profile_image);

       /* byte[] decodedString = Base64.decode(personPhotoUrl, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        System.out.println("bitmap get image:=>" + decodedByte);
        try {
            if (decodedByte == null || decodedByte.equals("")) {
                photo_imageView.setImageResource(android.R.drawable.sym_def_app_icon);
            } else {
                photo_imageView.setImageBitmap(decodedByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    protected void setAppToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        //ActivityCompat.invalidateOptionsMenu(HomeActivity.this);
    }

    protected void setToggle() {
        //setAppToolbar();

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                //super.onBackPressed();


                if (back_press_screen == 1) {
                    callHomeFragment();
                } else if (back_press_screen == 2) {
                    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                    //  Bundle args = new Bundle();
                    //  args.putString("From", "Profession");
                    NewFeaturesFragment fragmentB = NewFeaturesFragment.newInstance("Services");
                    tx.replace(R.id.content_frame, fragmentB);
                    //  tx.addToBackStack(null);
                    tx.commit();
                } else {
                    //  this.finish();
                    ActivityCompat.finishAffinity(HomeActivity.this);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        myMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        setColorFilterMenu(menu);

        setSearchViewMenu(menu);


        return true;

    }

    protected void setTootlbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toobarTextColor));
    }

    public void setSearchViewMenu(final Menu menu) {
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean newViewFocus) {
                if (!newViewFocus) {
                    //Collapse the action item.
                    searchItem.collapseActionView();
                    //Clear the filter/search query.
                    menu.findItem(R.id.action_search).setVisible(true);
                    menu.findItem(R.id.action_notificattion).setVisible(true);
                    menu.findItem(R.id.action_profile).setVisible(true);
                } else {
                    menu.findItem(R.id.action_notificattion).setVisible(false);
                    menu.findItem(R.id.action_profile).setVisible(false);
                }
            }
        });
    }

    public void setColorFilterMenu(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.toolbarIconColor), PorterDuff.Mode.SRC_ATOP);
            }
        }
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
        } else if (id == android.R.id.home) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);  // OPEN DRAWER
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            //logoutUser();
            closeAppAction();
        } else if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            //  callHomeFragment();
            setActiveImageColor(iv_home, iv_inbox, iv_profile, iv_offers, iv_notification);
            setActiveTextColor(txt_home, txt_inbox, txt_profile, txt_offers, txt_notification);

        } else if (id == R.id.nav_notifications) {
            fragment = new NotificationFragment();
            // callNotificationFragment();
            setActiveImageColor(iv_notification, iv_inbox, iv_profile, iv_home, iv_offers);
            //setActiveTextColor(txt_notification, txt_inbox, txt_profile, txt_home, txt_offers);
            back_press_screen = 1;
        } else if (id == R.id.nav_offers) {
            fragment = new OffersFragment();
            // callOffersFragment();
            setActiveImageColor(iv_offers, iv_inbox, iv_home, iv_notification, iv_profile);
            setActiveTextColor(txt_offers, txt_inbox, txt_home, txt_notification, txt_profile);
            back_press_screen = 1;
        } else if (id == R.id.nav_inbox) {
            fragment = new InboxFragment();
            // callInboxFragment();
            setActiveImageColor(iv_inbox, iv_profile, iv_home, iv_offers, iv_notification);
            setActiveTextColor(txt_inbox, txt_profile, txt_home, txt_offers, txt_notification);
            back_press_screen = 1;
        } else if (id == R.id.nav_profile) {
            fragment = new MyProfileFragment();
            //callProfileFragment();
            setActiveImageColor(iv_profile, iv_home, iv_offers, iv_inbox, iv_notification);
            setActiveTextColor(txt_profile, txt_home, txt_offers, txt_inbox, txt_notification);
            back_press_screen = 1;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

/*    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        toggle.onConfigurationChanged(newConfig);
    }*/

    /*  public void selectDrawerItem(MenuItem menuItem) {
          // Create a new fragment and specify the fragment to show based on nav item clicked
          Fragment fragment = null;
          Class fragmentClass;
          switch(menuItem.getItemId()) {
              case R.id.nav_first_fragment:
                  fragmentClass = FirstFragment.class;
                  break;
              case R.id.nav_second_fragment:
                  fragmentClass = SecondFragment.class;
                  break;
              case R.id.nav_third_fragment:
                  fragmentClass = ThirdFragment.class;
                  break;
              default:
                  fragmentClass = FirstFragment.class;
          }

          try {
              fragment = (Fragment) fragmentClass.newInstance();
          } catch (Exception e) {
              e.printStackTrace();
          }

          // Insert the fragment by replacing any existing fragment
          FragmentManager fragmentManager = getSupportFragmentManager();
          fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

          // Highlight the selected item has been done by NavigationView
          menuItem.setChecked(true);
          // Set action bar title
          setTitle(menuItem.getTitle());
          // Close the navigation drawer
          mDrawer.closeDrawers();
      }
  */
    private void callProfileFragment() {
        setActiveLayoutColor(ll_profile, ll_home, ll_offers, ll_inbox, ll_notification);
        setActiveImageColor(iv_profile, iv_home, iv_offers, iv_inbox, iv_notification);
        setActiveTextColor(txt_profile, txt_home, txt_offers, txt_inbox, txt_notification);

        FragmentTransaction propFrag = getSupportFragmentManager().beginTransaction();
        propFrag.replace(R.id.content_frame, new MyProfileFragment());
        propFrag.commit();
    }

    private void callInboxFragment() {
        setActiveLayoutColor(ll_inbox, ll_profile, ll_home, ll_offers, ll_notification);
        setActiveImageColor(iv_inbox, iv_profile, iv_home, iv_offers, iv_notification);
        setActiveTextColor(txt_inbox, txt_profile, txt_home, txt_offers, txt_notification);

        FragmentTransaction inFrag = getSupportFragmentManager().beginTransaction();
        inFrag.replace(R.id.content_frame, new InboxFragment());
        inFrag.commit();
    }

    private void callOffersFragment() {
        setActiveLayoutColor(ll_offers, ll_inbox, ll_home, ll_notification, ll_profile);
        setActiveImageColor(iv_offers, iv_inbox, iv_home, iv_notification, iv_profile);
        setActiveTextColor(txt_offers, txt_inbox, txt_home, txt_notification, txt_profile);

        FragmentTransaction offersFrag = getSupportFragmentManager().beginTransaction();
        offersFrag.replace(R.id.content_frame, new OffersFragment());
        offersFrag.commit();
    }

    private void callNotificationFragment() {
        setActiveLayoutColor(ll_notification, ll_inbox, ll_profile, ll_home, ll_offers);
        setActiveImageColor(iv_notification, iv_inbox, iv_profile, iv_home, iv_offers);
        setActiveTextColor(txt_notification, txt_inbox, txt_profile, txt_home, txt_offers);

        FragmentTransaction notifFrag = getSupportFragmentManager().beginTransaction();
        notifFrag.replace(R.id.content_frame, new NotificationFragment());
        notifFrag.commit();
    }

    public void callHomeFragment() {
        //  setActiveLayoutColor(ll_home, ll_inbox, ll_profile, ll_offers, ll_notification);
        setActiveImageColor(iv_home, iv_inbox, iv_profile, iv_offers, iv_notification);
        setActiveTextColor(txt_home, txt_inbox, txt_profile, txt_offers, txt_notification);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new HomeFragment());
        // tx.addToBackStack(null);
        tx.commit();
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     */

    private void closeAppAction() {
//
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogCustom);
        builder.setTitle("Confirm Please...");
        builder.setMessage("Do you want to close the app ?");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finishAffinity();
                        // finish();

                        //for lower api version than 16
                        // ActivityCompat.finishAffinity(HomeActivity.this);
                        if (loginFrom == "normal") {
                            session.setLogin(false);

                            // Launching the login activity
                            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            session.setLogin(false);
                            //login thru google
                            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                    new ResultCallback<Status>() {
                                        @Override
                                        public void onResult(Status status) {
                                            //  updateUI(false);
                                            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                        }
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert1 = builder.create();
        alert1.show();
    }

    public void logoutUser() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.ll_custom_search:
                break;*/
            case R.id.ll_notification:
                // callNotificationFragment();
                fragment = new NotificationFragment();
                setActiveImageColor(iv_notification, iv_inbox, iv_profile, iv_home, iv_offers);
                setActiveTextColor(txt_notification, txt_inbox, txt_profile, txt_home, txt_offers);
                back_press_screen = 1;
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
                back_press_screen = 1;
                break;

            case R.id.ll_profile:
                // callProfileFragment();
                fragment = new MyProfileFragment();
                setActiveImageColor(iv_profile, iv_home, iv_offers, iv_inbox, iv_notification);
                setActiveTextColor(txt_profile, txt_home, txt_offers, txt_inbox, txt_notification);
                back_press_screen = 1;
                break;
            case R.id.ll_inbox:
                //callInboxFragment();
                fragment = new InboxFragment();
                setActiveImageColor(iv_inbox, iv_profile, iv_home, iv_offers, iv_notification);
                setActiveTextColor(txt_inbox, txt_profile, txt_home, txt_offers, txt_notification);
                back_press_screen = 1;
                break;

        }
        try {
            //replacing the fragment
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setActiveTextColor(TextView activeText, TextView inactiveText1, TextView inactiveText2, TextView inactiveText3, TextView inactiveText4) {
        activeText.setTextColor(getResources().getColor(R.color.colorPrimary));
        inactiveText1.setTextColor(getResources().getColor(R.color.Gray));
        inactiveText2.setTextColor(getResources().getColor(R.color.Gray));
        inactiveText3.setTextColor(getResources().getColor(R.color.Gray));
        inactiveText4.setTextColor(getResources().getColor(R.color.Gray));
    }

    public void setActiveImageColor(ImageView activeImg, ImageView inactiveImg1, ImageView inactiveImg2, ImageView inactiveImg3, ImageView inactiveImg4) {

        activeImg.setColorFilter(getResources().getColor(R.color.colorPrimary));
        inactiveImg1.setColorFilter(getResources().getColor(R.color.Gray));
        inactiveImg2.setColorFilter(getResources().getColor(R.color.Gray));
        inactiveImg3.setColorFilter(getResources().getColor(R.color.Gray));
        inactiveImg4.setColorFilter(getResources().getColor(R.color.Gray));
    }

    private void setActiveLayoutColor(LinearLayout activeLayout, LinearLayout inactiveLayout1, LinearLayout inactiveLayout2, LinearLayout inactiveLayout3, LinearLayout inactiveLayout4) {
       /* activeLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        inactiveLayout1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        inactiveLayout2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        inactiveLayout3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        inactiveLayout4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));*/

    }
}
