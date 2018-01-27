package com.app.sid.funwithflags.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

@Module
public class ApplicationModule {

    private Application mApp;

    public ApplicationModule(Application app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApp.getApplicationContext();
    }
}
