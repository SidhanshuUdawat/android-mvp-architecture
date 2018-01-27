package com.app.sid.funwithflags.di.modules;

import android.content.Context;

import com.app.sid.funwithflags.realm.RealmManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */
@Module
public class RealmManagerModule {
    @Provides
    @Singleton
    RealmManager providesRealm(Context context) {
        return new RealmManager(context);
    }
}
