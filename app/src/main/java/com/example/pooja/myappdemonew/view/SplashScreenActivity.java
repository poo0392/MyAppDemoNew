package com.example.pooja.myappdemonew.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.pooja.myappdemonew.R;

/**
 * Created by Zafar.Hussain on 29/11/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {
    private ProgressBar progress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progress=(ProgressBar)findViewById(R.id.progressBar_splash) ;
        loadProgressBar();

    }
    private void loadProgressBar() {
        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(100);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Intent i = new Intent(SplashScreenActivity.this, BannerWithLoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        };
        t.start();
    }

}
