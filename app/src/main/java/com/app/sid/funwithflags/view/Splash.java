package com.app.sid.funwithflags.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.view.base.BaseActivity;
import com.app.sid.funwithflags.view.countrylist.CountryListActivity;


public class Splash extends BaseActivity {


    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showCountryList();
            }
        }, SPLASH_TIME_OUT);
    }

    public void showCountryList() {
        startActivity(new Intent(Splash.this, CountryListActivity.class));
        Splash.this.finish();
    }
}


