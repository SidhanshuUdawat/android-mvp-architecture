package com.app.sid.funwithflags;

import android.app.Application;

import com.app.sid.funwithflags.data.database.DatabaseManager;
import com.app.sid.funwithflags.di.components.DaggerApplicationComponent;
import com.app.sid.funwithflags.di.modules.ApplicationModule;
import com.app.sid.funwithflags.di.modules.RealmManagerModule;
import com.app.sid.funwithflags.di.provider.ApplicationBaseComponent;
import com.app.sid.funwithflags.utils.Connectivity;
import com.app.sid.funwithflags.utils.pref.PrefManager;

public class FunWithFlagsApplication extends Application {

    protected ApplicationBaseComponent appProvider;


    @Override
    public void onCreate() {
        super.onCreate();
        createAppComponent();
        PrefManager.getInstance().initPref(this);
        DatabaseManager.getInstance().initDatabase(this);
        Connectivity.getInstance().initConnectivity(this);
        setupRealm();
    }

    public void createAppComponent() {
        appProvider = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .realmManagerModule(new RealmManagerModule())
                .build();
    }

    public ApplicationBaseComponent getApplicationComponent() {
        return appProvider;
    }

    public void clearAppComponent() {
        appProvider = null;
    }

    public void setupRealm() {
        getApplicationComponent().getRealmManager().init();
    }
}
