package com.example.pooja.myappdemonew.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cresco.CommunityConnectForJJSConnect.Classes.Globals;
import com.cresco.CommunityConnectForJJSConnect.Php.PhpFiles;
import com.cresco.CommunityConnectForJJSConnect.Services.CrescoTextView;
import com.cresco.CommunityConnectForJJSConnect.Services.DBHelper;
import com.cresco.CommunityConnectForJJSConnect.Services.FileUtils;
import com.cresco.CommunityConnectForJJSConnect.Services.InternetUtils;
import com.cresco.CommunityConnectForJJSConnect.Services.JSONParser;
import com.cresco.CommunityConnectForJJSConnect.Services.PreferenceHelper;
import com.cresco.CommunityConnectForJJSConnect.Services.WebviewTags;
import com.cresco.CommunityConnectForJJSConnect.Tables.Address;
import com.cresco.CommunityConnectForJJSConnect.Tables.GCMUsers;
import com.cresco.CommunityConnectForJJSConnect.Tables.GroupsANDFamilies;
import com.cresco.CommunityConnectForJJSConnect.Tables.Join;
import com.cresco.CommunityConnectForJJSConnect.Tables.Members;
import com.cresco.CommunityConnectForJJSConnect.Tables.PersonalDetails;
import com.google.android.gms.appinvite.AppInviteInvitation;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by CRESCO on 04-Jul-16.
 */
public class HomeActivityNew extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {
    Toolbar toolbar;
    CrescoTextView toolbarTextview;
    Typeface typeface;
    protected DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private static final int REQUEST_INVITE = 0;
    private static final String TAG = "HomeActivity";
    Intent i;
    PersonalDetails pd;
    GCMUsers gcmUsers;
    Members members;
    JSONObject jo_resp;
    String ls_mobNum;
    Bundle b1;
    String mobile_no;
    int custID = 0;
    Join j;
    String minDate, maxDate;
    // String ls_dob = "11-26-1995";
    // String ls_dob = "21-06-1995"; // 21 jun
    Date inputDate;
    Calendar cal;
    int month, day;
    SimpleDateFormat dateFormat1, dateFormat2;
    PreferenceHelper mPreferenceHelper;
    String zodiacSign;
    // public String[] zodiacSigns;
    String ls_message;
    public String[] zodiacSigns = new String[]
            {
                    "Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra",
                    "Scorpio", "Sagittarius"


            };

    LinearLayout ll_diretcory, ll_chat, ll_community, ll_noticeBoard, ll_gallery, ll_dailyPanchang, ll_dailyHoroscope;
    LinearLayout ll_news, ll_reminders, ll_drawer_profile, ll_settings, ll_about, ll_rate, ll_share, ll_appTour;
    ImageView iv_horoscope;
    CrescoTextView tv_dirctory, tv_messages, tv_community, tv_horoscope, tv_panchang, tv_profile, tv_settings, tv_noticeBoard, tv_gallery, tv_about, tv_rate, tv_share;

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "on Start Called");
        setSharepreference();

    }

    private  void setSharepreference(){
        if (InternetUtils.isConnected(this)) {
            try {
                getAppOption();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            //
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG, "on Create Called");
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.title_activity_toolbar);
      //  FileUtils.copyDB(this);
        FileUtils.deleteDB(this);
        mPreferenceHelper = new PreferenceHelper(this);
        pd = new PersonalDetails(this);
        j = new Join(this);

        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/Dosis-Medium.ttf");
        // typeface = Typeface.createFromAsset(this.getAssets(), "fonts/Dosis-Medium.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //    toolbarTextview=(CrescoTextView)toolbar.findViewById(R.id.toolbar_textview);
        //    toolbarTextview.setTextColor(getResources().getColor(R.color.White));
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        iv_horoscope = (ImageView) findViewById(R.id.iv_horoscope);
        //getMobNumFromDb();


        getDOB();


    }

    public String getMobNumFromDb() {
        //pd = new PersonalDetails(this);
        b1 = new Bundle();
        b1 = pd.getMobileNum();
        ls_mobNum = b1.getString(PhpFiles.TAG_MOB_NUM);
        return ls_mobNum;
    }

    protected void setToggle() {
        //setAppToolbar();

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    protected void setAppToolbar(String title) {
        //this.toolbar =moolbar;

        setSupportActionBar(toolbar);
        CrescoTextView toolbarTextview = (CrescoTextView) toolbar.findViewById(R.id.toolbar_textview);

        toolbarTextview.setTypeface(typeface);
        toolbarTextview.setText(title);
        toolbarTextview.setTextColor(getResources().getColor(R.color.White));

    }

    protected Toolbar getAppToolbar() {
        if (null != toolbar)
            return toolbar;
        return toolbar;
    }


    protected boolean useToolbar() {
        return true;
    }

    public void setListeners() {
        ll_diretcory.setOnClickListener(this);
        ll_chat.setOnClickListener(this);
        ll_community.setOnClickListener(this);
        ll_noticeBoard.setOnClickListener(this);
        ll_gallery.setOnClickListener(this);
        ll_dailyPanchang.setOnClickListener(this);
        ll_dailyHoroscope.setOnClickListener(this);
//        ll_news.setOnClickListener(this);
       // ll_reminders.setOnClickListener(this);
        ll_drawer_profile.setOnClickListener(this);
        ll_settings.setOnClickListener(this);
        ll_about.setOnClickListener(this);
        ll_rate.setOnClickListener(this);
        ll_share.setOnClickListener(this);
       // ll_appTour.setOnClickListener(this);

    }

    public void initializeComponents() {

        ll_diretcory = (LinearLayout) findViewById(R.id.ll_directory);
        tv_dirctory = (CrescoTextView) findViewById(R.id.tv_directory);
        ll_chat = (LinearLayout) findViewById(R.id.ll_chat);
        tv_messages = (CrescoTextView) findViewById(R.id.tv_chat);
        ll_community = (LinearLayout) findViewById(R.id.ll_community);
        tv_community = (CrescoTextView) findViewById(R.id.tv_kms);
        ll_noticeBoard = (LinearLayout) findViewById(R.id.ll_notice_board);
        tv_noticeBoard = (CrescoTextView) findViewById(R.id.tv_noticeBoard);
        ll_gallery = (LinearLayout) findViewById(R.id.ll_gallery);
        tv_gallery = (CrescoTextView) findViewById(R.id.tv_gallery);
        ll_dailyHoroscope = (LinearLayout) findViewById(R.id.ll_dailyHoroscope);
        tv_horoscope = (CrescoTextView) findViewById(R.id.tv_horoscope);
        ll_dailyPanchang = (LinearLayout) findViewById(R.id.ll_dailyPanchang);
        tv_panchang = (CrescoTextView) findViewById(R.id.tv_dailyPanchang);
        ll_drawer_profile = (LinearLayout) findViewById(R.id.ll_drawer_profile);
        tv_profile = (CrescoTextView) findViewById(R.id.tv_drawer_profile);
        ll_settings = (LinearLayout) findViewById(R.id.ll_internetSetting);
        tv_settings = (CrescoTextView) findViewById(R.id.tv_settings);
        ll_about = (LinearLayout) findViewById(R.id.ll_about);
        tv_about = (CrescoTextView) findViewById(R.id.tv_about);
        ll_rate = (LinearLayout) findViewById(R.id.ll_rate);
        tv_rate = (CrescoTextView) findViewById(R.id.tv_rate);
        ll_share = (LinearLayout) findViewById(R.id.ll_share);
        tv_share = (CrescoTextView) findViewById(R.id.tv_share);

    }

    public void setStatusBarColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(id));
        }

    }

    public void getDOB() {
        cal = Calendar.getInstance();
        j = new Join(this);
        pd = new PersonalDetails(this);
        members = new Members(this);
        dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat2 = new SimpleDateFormat("mm-dd");
        // parse dob
        try {
            String ls_dob = j.getDobOfRegisterUser();
            // String ls_dob = "1995-06-21";;

            Log.v(TAG, "dob from db" + ls_dob); // yyyy-MM-dd
            if (ls_dob != null || ls_dob != "") {
                if (ls_dob.equals("0000-00-00")) {
                  //  Log.v("Daily Horoscope", "dob 0000 ");
                    //if no value for dob
                    ls_dob = "1995-01-13";
                    inputDate = dateFormat1.parse(ls_dob);
                    cal = new GregorianCalendar();
                    cal.setTime(inputDate);
                    //  Log.v("Daily Horoscope", "dateFormat.parse(ls_dob)" + inputDate);
                    // month = cal.get(Calendar.MONTH) + 1;
                    month = cal.get(Calendar.MONTH);
                    //   Log.v("Daily Horoscope", "month " + month);
                    day = cal.get(Calendar.DAY_OF_MONTH);
                    //   Log.v("Daily Horoscope", "day " + day);

                    getZodiac(month, day);
                } else {
                   // Log.v("Daily Horoscope", "dob not 0000 ");
                    inputDate = dateFormat1.parse(ls_dob);
                    cal = new GregorianCalendar();
                    cal.setTime(inputDate);
                  //  Log.v("Daily Horoscope", "dateFormat.parse(ls_dob)" + inputDate);
                    // month = cal.get(Calendar.MONTH) + 1;
                    month = cal.get(Calendar.MONTH);
                    //      Log.v("Daily Horoscope", "month " + month);
                    day = cal.get(Calendar.DAY_OF_MONTH);
                    //       Log.v("Daily Horoscope", "day " + day);

                    getZodiac(month, day);
                }
            } else {
               // Log.v("Daily Horoscope", "dob blank ");
                ls_dob = "1995-01-13";
                inputDate = dateFormat1.parse(ls_dob);
                cal = new GregorianCalendar();
                cal.setTime(inputDate);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                getZodiac(month, day);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void getZodiac(int month, int day) {
        // zodiacSigns=new String[]{};
        if (month == 11 && day >= 22 || month == 0 && day <= 19) {
            zodiacSigns[0] = "Capricorn";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_CAPRICORN);
            //  Log.v(TAG, " zodiacSigns[0] " + zodiacSigns[0].toString());
            iv_horoscope.setImageResource(R.drawable.capricorn); //capricorn

        } else if ((month == 0) && (day >= 20) || (month == 1) && (day <= 18)) {
            zodiacSigns[1] = "Aquarius";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_AQUARIUS);
            //    Log.v(TAG, " zodiacSigns[1] " + zodiacSigns[1].toString());
            iv_horoscope.setImageResource(R.drawable.aquarius);

        } else if ((month == 1) && (day >= 19) || (month == 2) && (day <= 20)) {
            zodiacSigns[2] = "Pisces";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_PISCES);
            //   Log.v(TAG, " zodiacSigns[2] " + zodiacSigns[2].toString());
            iv_horoscope.setImageResource(R.drawable.pisces);

        } else if ((month == 2) && (day >= 21) || (month == 3) && (day <= 19)) {
            zodiacSigns[3] = "Aries";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_ARIES);
            //  Log.v(TAG, " zodiacSigns[3] " + zodiacSigns[3].toString());
            iv_horoscope.setImageResource(R.drawable.aries);//aries

        } else if ((month == 3) && (day >= 20) || (month == 4) && (day <= 20)) {
            zodiacSigns[4] = "Taurus";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_TAURUS);
            //  Log.v(TAG, " zodiacSigns[4] " + zodiacSigns[4].toString());
            iv_horoscope.setImageResource(R.drawable.taurus);//taurus

        } else if ((month == 4) && (day >= 21) || (month == 5) && (day <= 20)) {
            zodiacSigns[5] = "Gemini";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_GEMINI);
            // Log.v(TAG, " zodiacSigns[5] " + zodiacSigns[5].toString());
            iv_horoscope.setImageResource(R.drawable.gemini); //gemini

        } else if ((month == 5) && (day >= 21) || (month == 6) && (day <= 22)) {
            zodiacSigns[6] = "Cancer";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_CANCER);
            //  Log.v(TAG, " zodiacSigns[6] " + zodiacSigns[6].toString());
            iv_horoscope.setImageResource(R.drawable.cancer);//cancer

        } else if ((month == 6) && (day >= 23) || (month == 7) && (day <= 22)) {
            zodiacSigns[7] = "Leo";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_LEO);
            //  Log.v(TAG, " zodiacSigns[7] " + zodiacSigns[7].toString());
            iv_horoscope.setImageResource(R.drawable.leo);//leo

        } else if ((month == 7) && (day >= 23) || (month == 8) && (day <= 21)) {
            zodiacSigns[8] = "Virgo";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_VIRGO);
            //    Log.v(TAG, " zodiacSigns[8] " + zodiacSigns[8].toString());
            iv_horoscope.setImageResource(R.drawable.virgo);//virgo

        } else if ((month == 8) && (day >= 22) || (month == 9) && (day <= 21)) {
            zodiacSigns[9] = "Libra";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_LIBRA);
            //    Log.v(TAG, " zodiacSigns[9] " + zodiacSigns[9].toString());
            Log.v(TAG, " zodiacSigns[9] " + zodiacSigns[9].toString());
            iv_horoscope.setImageResource(R.drawable.libra);//libra

        } else if ((month == 9) && (day >= 24) || (month == 10) && (day <= 22)) {
            zodiacSigns[10] = "Scorpio";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_SCORPIO);
            //    Log.v(TAG, " zodiacSigns[10] " + zodiacSigns[10].toString());
            iv_horoscope.setImageResource(R.drawable.scorpio);//scorpio

        } else if ((month == 10) && (day >= 23) || (month == 11) && (day <= 21)) {
            zodiacSigns[11] = "Sagittarius";
            mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_SAGITTARIUS);
            //    Log.v(TAG, " zodiacSigns[11] " + zodiacSigns[11].toString());
            iv_horoscope.setImageResource(R.drawable.sagittarius);//sagittarius

        } else {
            // mPreferenceHelper.addString("zodiacSign", WebviewTags.ZODIAC_CAPRICORN );
            iv_horoscope.setImageResource(R.drawable.capricorn);

            Log.v(TAG, " Not a valid date ");


        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //PoojaP
            case R.id.ll_directory:
                drawer.closeDrawer(GravityCompat.START);
                //  }
                i = new Intent(this, MembersActiivity.class);
                //   i.putExtra("isClicked", "ll_members");
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                //    overridePendingTransition(0, 0);
                //   overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
                break;
            case R.id.ll_chat:
                drawer.closeDrawer(GravityCompat.START);
                callChatActivity();
                // overridePendingTransition(0, 0);
                // overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
                break;
            case R.id.ll_community:
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, JJSComities.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //i.putExtra("previousActivity", currActivity);
                startActivity(i);
                // overridePendingTransition(0, 0);
                // overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
                break;
            case R.id.ll_dailyHoroscope:
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, DailyHoroscope.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //i.putExtra("previousActivity", currActivity);
                startActivity(i);
                // overridePendingTransition(0, 0);
                //  overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
                break;
            case R.id.ll_dailyPanchang:
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, DailyPanchang.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;

            case R.id.ll_drawer_profile:
                drawer.closeDrawer(GravityCompat.START);
                //get all details and go to profile class
                goToProfileClass();
                break;
            case R.id.ll_internetSetting: //Media
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, DownloadSelection.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;
            case R.id.ll_about:
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, AboutAdnote.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;
            case R.id.ll_rate:
                drawer.closeDrawer(GravityCompat.START);
                rateApp();
                break;
            case R.id.ll_share:
                drawer.closeDrawer(GravityCompat.START);
                onInviteClicked();
                break;
          /*  case R.id.ll_apptour:
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, WelcomeNote.class);
                startActivity(i);
                break;*/
            case R.id.ll_notice_board:
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, ActivityNoticeBoard.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0, 0);
                break;

            case R.id.ll_gallery:
                drawer.closeDrawer(GravityCompat.START);
                i = new Intent(this, ActivityGalleryDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0, 0);
                break;
            default:
                break;
        }
    }

    private void goToProfileClass() {
        try {
            // goToProfileClass();

            Cursor cursor;

            String groupName = null;
            int groupId = 0;
            String address = null, address_1 = null, address_2 = null, address_3 = null, city = null, pincode = null, state = null, country = null, nativePlace = null, landline_no = null;

           /* Bundle b = new Bundle();
            b = pd.getMobileNum();
            ls_mobNum = b.getString(PhpFiles.TAG_MOB_NUM);*/
           // mPreferenceHelper.addString("mobile_no", ls_mobNum);
            getMobNumFromDb();
         //   Log.v(TAG, "mob_num : " + ls_mobNum);
            custID = ((MyApplication) this.getApplication()).getCustId();
          //  Log.v(TAG, "old Cust id in settingsactivity :" + custID);

            cursor = j.getFamilyName(ls_mobNum, custID);

            //Log.v(TAG, "Cursor count: " + cursor.getCount());

            if (cursor != null && cursor.getCount() > 0) {
                //Log.v(TAG, "Cursor not null : " + cursor.getCount());

                cursor.moveToFirst();
                groupName = cursor.getString(cursor.getColumnIndex(GroupsANDFamilies.GROUP_NAME));
                groupId = cursor.getInt(cursor.getColumnIndex(GroupsANDFamilies.GROUP_ID));
                address_1 = cursor.getString(cursor.getColumnIndex(Address.ADDRESS_1));
                address_2 = cursor.getString(cursor.getColumnIndex(Address.ADDRESS_2));
                address_3 = cursor.getString(cursor.getColumnIndex(Address.ADDRESS_3));
                city = cursor.getString(cursor.getColumnIndex(Address.CITY));
                pincode = cursor.getString(cursor.getColumnIndex(Address.PINCODE));
                state = cursor.getString(cursor.getColumnIndex(Address.STATE));
                country = cursor.getString(cursor.getColumnIndex(Address.COUNTRY));
                nativePlace = cursor.getString(cursor.getColumnIndex(Address.NATIVE_PLACE));
                landline_no = cursor.getString(cursor.getColumnIndex(Address.LANLINE_NO));

                //cursor.getString()
//                Log.v(TAG, "groupName : " + groupName);
//                Log.v(TAG, "groupId : " + groupId);

//            Log.v(TAG, "address_1 : " + address_1);
//            Log.v(TAG, "address_2 : " + address_2);
//            Log.v(TAG, "address_3 : " + address_3);
//            Log.v(TAG, "city : " + city);
//            Log.v(TAG, "pincode : " + pincode);
//            Log.v(TAG, "state : " + state);
//            Log.v(TAG, "country : " + country);

            }

            StringBuilder add = new StringBuilder();
            add.append(address_1 + ", " + address_2 + ", " + address_3 + ", " + city + " - " + pincode + ", " + state + ", " + country);

            //Intent intent = new Intent(this, Profile.class);
          //  Log.v(TAG, "GroupId : " + groupId);

            members = new Members(this);


            if (groupId == 0 || custID == 0) {
                if (InternetUtils.isConnected(this)) {

                    String userID = members.getUserIDByMobNo(ls_mobNum);

                    if (userID == null) {
                        userID = "";
                    }

                    //  Log.v(TAG, "user id in members : " + userID);

                    Intent i = new Intent(this, Profile.class);
                    i.putExtra("Family Id", groupId); //Family Id
                    //i.putExtra("GroupId", groupId); //Family Id
                    i.putExtra("Mobile Number", ls_mobNum);
                    i.putExtra("User_id", userID);
                    i.putExtra("First Name", "");
                    i.putExtra("callFrom", "SettingActivity");
                    //   i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    overridePendingTransition(0, 0);

                } else {
                    Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            } else {

                //Log.v(TAG, "city : " + city);
                //Log.v(TAG, "pincode : " + pincode);
                //Log.v(TAG, "state : " + state);
                //Log.v(TAG, "country : " + country);
               // Log.v(TAG, "GroupId : " + groupId);
                try {
                    Intent intent = new Intent(this, FamilyProfile.class);
                    intent.putExtra("GroupId", groupId);

                    intent.putExtra("GroupName", groupName);
                    intent.putExtra("Address", add.toString());
                    intent.putExtra("NativePlace", nativePlace);

                    intent.putExtra("Address_1", address_1);
                    intent.putExtra("Address_2", address_2);
                    intent.putExtra("Address_3", address_3);
                    intent.putExtra("City", city);
                    intent.putExtra("Pincode", pincode);
                    intent.putExtra("State", state);
                    intent.putExtra("Country", country);
                    intent.putExtra("Landline_No", landline_no);
                    //   i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(0, 0);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }

    }


    public void onInviteClicked() {
        String invitation_message = "JJS Connect - An amazing app to connect with JJS Community !";
        String appLinkUrl = "https://play.google.com/store/apps/details?id=com.cresco.CommunityConnectForJJSConnect&hl=en";
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(invitation_message)
                .setDeepLink(Uri.parse(appLinkUrl))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }
    public void rateApp() {
        String str = "https://play.google.com/store/apps/details?id=com.cresco.CommunityConnectForJJSConnect";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }
    private void callChatActivity() {
        pd = new PersonalDetails(this);
        gcmUsers = new GCMUsers(this);

        //Cursor cursor = gcmUsers.getUserById(1);
        int li_custID = ((MyApplication) this.getApplication()).getCustId();
        //Log.v("Home Activity", "Cust id in Titlebar :" + li_custID);

        Cursor cursor = gcmUsers.getUserBycustID(li_custID);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            String id = cursor.getString(cursor.getColumnIndex(GCMUsers._ID));
            String regIdTo = cursor.getString(cursor.getColumnIndex(GCMUsers.REG_ID));
            String mobile = cursor.getString(cursor.getColumnIndex(GCMUsers.MOBILE_NUM));
            String ls_name = cursor.getString(cursor.getColumnIndex(GCMUsers.DISPLAY_NAME));     //801ad80001no1te


            String regIdFrom = pd.getRegId();

            Intent intent = new Intent(this, ChatActivity.class);

            intent.putExtra(GCMUsers.MOBILE_NUM, mobile);
            intent.putExtra(GCMUsers.DISPLAY_NAME, ls_name);
            intent.putExtra(ChatActivity.TAG_REG_ID_TO, regIdTo);
            intent.putExtra(ChatActivity.TAG_REG_ID_FROM, regIdFrom);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);

        } else {
            for (int j = 0; j < 3; j++) {
                Toast.makeText(this, "Oops! Looks like some changes/updates to your system have messed up with some of the required services. Kindly reinstall the app.", Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onBackPressed() {
        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void getAppOption() throws JSONException {
        getMobNumFromDb();

   //     Log.v(TAG, "mob_num : " + ls_mobNum);
        final String url = PhpFiles.URL_CC_API;
        final ArrayList<NameValuePair> list_params = new ArrayList<NameValuePair>();
        list_params.add(new BasicNameValuePair(PhpFiles.TAG_APP_ID,String.valueOf(DBHelper.APP_ID)));
        list_params.add(new BasicNameValuePair(PhpFiles.TAG_CALL_TYPE,"AO"));
        list_params.add(new BasicNameValuePair(PhpFiles.TAG_MOB_NUM, ls_mobNum));
        final JSONParser jsonParser = new JSONParser();
        jo_resp = new JSONObject();
        class PostData extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... strings) {
               // try {
                    jo_resp = jsonParser.makeHttpRequest(url, "POST", list_params);
                    //   Log.v(this.getClass().getSimpleName(), " Url " + url + "list" + list_params);

                    //  Log.v(this.getClass().getSimpleName(), " Response " + jo_resp);
                    String isAdded = null;
                    if (jo_resp != null) {
                        try {
                            isAdded = jo_resp.getString("isAdded");
                            setSharepreference(isAdded);
                            setSharepreferenceForCurrentAdd(isAdded);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ls_message = "Unexpected Error!";
                            Toast.makeText(HomeActivity.this, ls_message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // the user should always be shown some message.
                        // this code is reached when the ' Server' is down.
                        ls_message = "Unexpected Connection Error! Please try again later.";
                        Toast.makeText(HomeActivity.this, ls_message, Toast.LENGTH_SHORT).show();
                    }
                    //   Log.v(this.getClass().getSimpleName(), " isAdded " + isAdded);
               /* } catch (Exception e) {
                    e.printStackTrace();
                    //ls_message = "Unexpected Error!";
                }*/
                return null;
            }

        }
        PostData d = new PostData();
        d.execute();
    }

    // for Add and Delete button for user profile
    private void setSharepreference(String add_delete) {
        String value_of_position = Character.toString(add_delete.charAt(11));
      //  Log.v(this.getClass().getSimpleName(), "value_of_position charAt(11) " + value_of_position);
        SharedPreferences sharedPreferences = getSharedPreferences(Globals.MY_SHAREPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Member", value_of_position);
        editor.commit();
    }

    // for Add and Delete button for user profile
    private void setSharepreferenceForCurrentAdd(String add_delete) {
        String value_of_position = Character.toString(add_delete.charAt(12));
     //   Log.v(this.getClass().getSimpleName(), "value_of_position charAt(12) " + value_of_position);
        SharedPreferences sharedPreferences = getSharedPreferences(Globals.MY_SHAREPREFERENCES_FOR_CUR_ADDRESS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CurrentAddress", value_of_position);
        editor.commit();
    }

}
