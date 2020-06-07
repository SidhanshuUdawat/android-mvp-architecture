package com.app.sid.funwithflags

import com.app.sid.funwithflags.di.components.DaggerApplicationComponent
import com.app.sid.funwithflags.di.provider.ApplicationBaseComponent
import com.app.sid.funwithflags.utils.Connectivity
import com.app.sid.funwithflags.utils.pref.PrefManager
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FunWithFlagsApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.factory().create(applicationContext)

    override fun onCreate() {
        super.onCreate()
        PrefManager.getInstance().initPref(this)
        Connectivity.getInstance().initConnectivity(this)
    }
}