package com.app.sid.funwithflags.view.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Sidhanshu
 * This activtiy is extented by every activity created in the application, used for better
 * handeling generic metods and perform operation which could be common throughtout the application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
