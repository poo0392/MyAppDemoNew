/*
package com.example.pooja.myappdemonew.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cresco.CommunityConnectForJJSConnect.Adapter.NoticeBoardAdapter;
import com.cresco.CommunityConnectForJJSConnect.Classes.Globals;
import com.cresco.CommunityConnectForJJSConnect.Classes.Information;
import com.cresco.CommunityConnectForJJSConnect.Php.PhpFiles;
import com.cresco.CommunityConnectForJJSConnect.Services.CrescoTextView;
import com.cresco.CommunityConnectForJJSConnect.Services.DBHelper;
import com.cresco.CommunityConnectForJJSConnect.Services.FileUtils;
import com.cresco.CommunityConnectForJJSConnect.Services.InternetUtils;
import com.cresco.CommunityConnectForJJSConnect.Services.JSONParser;
import com.cresco.CommunityConnectForJJSConnect.Services.PreferenceHelper;
import com.cresco.CommunityConnectForJJSConnect.Tables.PersonalDetails;
import com.example.pooja.myappdemonew.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

*/
/**
 * Created by Jitendra Keshari on 5/07/2016...
 *//*


public class ActivityNoticeBoard extends HomeActivity implements View.OnClickListener {
    private static final String TAG = "ActivityNoticeBoard";
    NoticeBoardAdapter adapter;
    ListView listView;
    ArrayList<NameValuePair> list_params;
    JSONParser jsonParser;
    JSONObject jo_resp;
    StringBuffer buffer;
    ArrayList<Information> list;
    PersonalDetails pd;
    int custID = 0;
    int appID = 0;
    Boolean isCopied;
    LinearLayout llNoInternet,ll_listv;
    TextView tv_noInternet;
    Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_notice_board);
        // start PoojaP
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_notice_board, null, false);
        drawer.addView(contentView, 0);

        mPreferenceHelper = new PreferenceHelper(this);

        setStatusBarColor(R.color.header_notice_color);
        //set drawer components
        initializeComponents();
        setListeners();

        String title = "NOTICE BOARD";
        setAppToolbar(title);
        toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_notice_color));
        setToggle();
        //end drawer

        /// end pooja
        FileUtils.deleteDB(this);
        attachUI();
        // setAppToolbar();
      //pp  isCopied = mPreferenceHelper.getBoolean("isCopied");
     //   Log.v(TAG, "isCopied :" + isCopied);

        if (InternetUtils.isConnected(this)) {
          //pp  if(isCopied.equals(false)) {
       //         Log.v(TAG, "isCopied :" + isCopied);
                getResponseFromServer();

                //copyFileInCache();
            }else {
                getFileFromCache();
            }
       //pp } else if (isCopied.equals(false)) {
       //     Log.v(TAG, "isCopied 2:" + isCopied);
//          pp  llNoInternet.setVisibility(View.VISIBLE);
//            ll_listv.setVisibility(View.GONE);

           // Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        */
/*}PP
        else {
            getFileFromCache();

        }
*//*


    }


    private void getDetailsFromDb() {
      */
/*  pd = new PersonalDetails(this);
        Bundle bd = new Bundle();
        bd = pd.getMobileNum();
        ls_mobNum = bd.getString(PhpFiles.TAG_MOB_NUM);
*//*


        custID = ((MyApplication) this.getApplication()).getCustId();
    //    Log.v(TAG, "CustId in ActivityNoticeBoardDetails :" + custID);

    }

    private void attachUI() {
        llNoInternet = (LinearLayout) findViewById(R.id.llNoInternet);
        ll_listv = (LinearLayout) findViewById(R.id.ll_listv);
        tv_noInternet = (TextView) findViewById(R.id.txtNoInternet);
        tv_noInternet.setTypeface(typeface);

        listView = (ListView) findViewById(R.id.listview1);
        listView.setOnItemClickListener(itemclk);
    }

    private AdapterView.OnItemClickListener itemclk = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Globals._DATE = list.get(position).getDate();
            Globals.TITLE = list.get(position).getTitle();
            Globals.DESCRIPTION = list.get(position).getDescription();
            Globals.ATTACHMENT = list.get(position).getAttachment();

            Log.v(TAG,"Globals.ATTACHMENT" +Globals.ATTACHMENT);
            Intent intent = new Intent(ActivityNoticeBoard.this, ActivityNoticeBoardDetails.class);
            //intent.putExtra("mob_num", ls_mobNum);
            startActivity(intent);
            overridePendingTransition(0, 0);

        }
    };


    private void getResponseFromServer() {
    //    Log.v(this.getClass().getSimpleName(), "getResponseFromServer called");
        //Pooja: added getDetailsFromDb() to get cust_id and get mobilenumber from db
        getDetailsFromDb();
        getMobNumFromDb();
        //end pooja

        //String url = PhpFiles.URL_FOLDER_BASE;
        list_params = new ArrayList<NameValuePair>();
        list_params.add(new BasicNameValuePair(PhpFiles.TAG_APP_ID, String.valueOf(DBHelper.APP_ID)));
        list_params.add(new BasicNameValuePair(PhpFiles.TAG_MOB_NUM, ls_mobNum));
        list_params.add(new BasicNameValuePair(PhpFiles.TAG_CUST_ID, "" + custID));
        list_params.add(new BasicNameValuePair(PhpFiles.TAG_CALL_TYPE, "NBO"));  // NBO -- Notice board operation ,GO - Gallery Operation:
  //      Log.v(this.getClass().getSimpleName(), "list param" + list_params);
        postData();
    }

    private void postData() {
        class GetData extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new Dialog(ActivityNoticeBoard.this, R.style.crescoDialogStyle);
                progressDialog.setContentView(R.layout.progressbar_round);
                CrescoTextView tv_loading = (CrescoTextView) progressDialog.findViewById(R.id.loading);
                tv_loading.setText("Loading... ");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {
               // String url = PhpFiles.URL_FOLDER_BASE + "adnote_event.php";
                String url = PhpFiles.URL_CC_API;
          //      Log.v(this.getClass().getSimpleName(), "Response" + url);
                jsonParser = new JSONParser();
                jo_resp = jsonParser.makeHttpRequest(url, "POST", list_params);
                Log.v(this.getClass().getSimpleName(), "Response" + jo_resp);
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.v(this.getClass().getSimpleName(), "Response" + jo_resp);
                progressDialog.dismiss();
                copyFileInCache();
                */
/*if (jo_resp != null ) {//if(jo_resp.length() < 1){
                    copyFileInCache();
                }else{
                    llNoInternet.setVisibility(View.VISIBLE);
                      ll_listv.setVisibility(View.GONE);
                }*//*



            }
        }
        GetData data = new GetData();
        data.execute();
    }

    private void copyFileInCache() {
  //      Log.v(this.getClass().getSimpleName(), "copyFileInCache called");
        File file;
        String FILENAME = "notice_board_file";
        String data = jo_resp.toString();
        FileOutputStream fos = null;
        try {
            file = new File(getCacheDir(), FILENAME);
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
            Log.v(this.getClass().getSimpleName(), "File copy.....");
         //PP   mPreferenceHelper.addBoolean("isCopied", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getFileFromCache();
    }


    private void getFileFromCache() {
     //   Log.v(this.getClass().getSimpleName(), "getFileFromCache called");
        BufferedReader input = null;
         String FILENAME = "notice_board_file";
        File file;
        try {

            file = new File(getCacheDir(), FILENAME);
            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }
            attachAdapter();
         //   Log.v(this.getClass().getSimpleName(), "Read file content : " + buffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attachAdapter() {
        list = new ArrayList<Information>();
        // list.add("Jitendra");
        Information info = null;
        String data = buffer.toString();
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray array = obj.getJSONArray("notice_board");
            JSONArray array1 = obj.getJSONArray("attachment");
            Log.v(TAG,"notice_board "+array.length()+"attachment "+array1.length());
            if(array.length()<1){
                llNoInternet.setVisibility(View.VISIBLE);
                ll_listv.setVisibility(View.GONE);
            }
            for (int i = 0; i < array.length(); i++) {
                info = new Information();
                String date = array.getJSONObject(i).getString("date");
                String title = array.getJSONObject(i).getString("title");
                String description = array.getJSONObject(i).getString("description");
                String id = array.getJSONObject(i).getString("id");
                for (int j = 0; j < array1.length(); j++) {
                    String attachment_path = array1.getJSONObject(j).getString("attachment_path");
                    String entity_id = array1.getJSONObject(j).getString("entity_id");
                        if (id.equals(entity_id)) {
                            Log.v(TAG, "attachment path" + attachment_path);
                            info.setAttachment(attachment_path);
                        }
                    else {
                        Log.v(TAG,"attachment path else "+attachment_path);
                       // info.setAttachment("");
                    }
                }
                info.setDate(date);
                info.setTitle(title);
                info.setDescription(description);
                list.add(info);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new NoticeBoardAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
//		super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent backIntent=new Intent(ActivityNoticeBoard.this,LandingGridMenuActivity.class);
            startActivity(backIntent);
            finish();
            //overridePendingTransition(0, 0);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
*/
