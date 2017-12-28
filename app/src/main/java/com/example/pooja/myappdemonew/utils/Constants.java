package com.example.pooja.myappdemonew.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Meenakshi Aher on 25/05/2017.
 */

public class Constants {



   String WebUrl="http://10.62.121.189";

    //Internet offline msg
    public static String offline_msg = "Internet connection appears to be offline";

    public static boolean isNetworkInfo(Activity activity) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService( Context.CONNECTIVITY_SERVICE );
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return
                    activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            return false;
        }
    }


    public static void snackbar(View layout, String msg) {
        Snackbar.make( layout, msg, Snackbar.LENGTH_LONG ).setAction( "Action", null ).show();
    }


    public static void callIntent(Context con, Class con2, final int enterAnim, final int exitAnim) {
        Intent intent = new Intent( con, con2 );
       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        con.startActivity( intent );
        ((Activity) con).overridePendingTransition( enterAnim, exitAnim );
    }
}
