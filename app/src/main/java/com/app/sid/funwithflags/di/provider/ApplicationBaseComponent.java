package com.app.sid.funwithflags.di.provider;

import android.app.Application;
import android.content.Context;

import com.app.sid.funwithflags.realm.RealmManager;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public interface ApplicationBaseComponent {

    Application application();

    Context getContext();

    RealmManager getRealmManager();
}
