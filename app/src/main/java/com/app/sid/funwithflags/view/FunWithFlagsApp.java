package com.app.sid.funwithflags.view;

import android.app.Application;
import android.content.Context;

import com.app.sid.funwithflags.data.database.DatabaseManager;
import com.app.sid.funwithflags.utils.Connectivity;
import com.app.sid.funwithflags.utils.pref.PrefManager;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class FunWithFlagsApp extends Application {

    private static FunWithFlagsApp mInstance;

    public static synchronized FunWithFlagsApp getApp() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

        PrefManager.getInstance().initPref(this);
        DatabaseManager.getInstance().initDatabase(this);
        Connectivity.getInstance().initConnectivity(this);
    }

    public Context appContext() {
        return this.getApplicationContext();
    }
}
