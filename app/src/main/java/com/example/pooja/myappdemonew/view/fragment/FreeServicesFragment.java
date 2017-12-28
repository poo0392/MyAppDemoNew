package com.example.pooja.myappdemonew.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.pooja.myappdemonew.R;

/**
 * Created by Zafar.Hussain on 28/12/2017.
 */

public class FreeServicesFragment extends Fragment {
    Context mContext;
    WebView webview;
    ProgressBar prgs;
    LinearLayout ll_webview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freeservices, container, false);
        mContext = getActivity();
        //  back_press_screen = 1;
        attachView(view);
        return view;
    }

    private void attachView(View view) {
        webview = (WebView) view.findViewById(R.id.webview_link);
        prgs = (ProgressBar) view.findViewById(R.id.progressBar);
        ll_webview = (LinearLayout) view.findViewById(R.id.ll_webview);


        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);


       /* if ((info == null || !info.isConnected() || !info.isAvailable())) {
           // txtNoInternet.setVisibility(View.VISIBLE);
            webview.setVisibility(View.GONE);
            ll_webview.setVisibility(View.GONE);
            Toast.makeText(mContext, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
        } else {*/

            webview.setVisibility(View.VISIBLE);
            ll_webview.setVisibility(View.VISIBLE);
           // txtNoInternet.setVisibility(View.GONE);

           // url = getResources().getString(R.string.travel_online);
         //   Log.d("", "print values" + url);
            webview.setWebViewClient(new MyBrowser());
            webview.loadUrl("https://Heapp3.hdfcergo.com/MotorIDV");
          //  webview.loadUrl("https://www.tutorialspoint.com/");
      //  }
    }

    private class MyBrowser extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            if (prgs.getVisibility() == View.GONE) {
                prgs.setVisibility(View.VISIBLE);
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (prgs.getVisibility() == View.GONE) {
                prgs.setVisibility(View.VISIBLE);
            }
            //  webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (prgs.getVisibility() == View.VISIBLE)
                prgs.setVisibility(View.GONE);


        }
    }
}
