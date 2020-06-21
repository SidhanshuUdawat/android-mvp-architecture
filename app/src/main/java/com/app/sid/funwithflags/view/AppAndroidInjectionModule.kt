package com.app.sid.funwithflags.view

import com.app.sid.funwithflags.di.scopes.ActivityScope
import com.app.sid.funwithflags.view.countrylist.CountryListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppAndroidInjectionModule {

    @ContributesAndroidInjector
    @ActivityScope
    fun provideSplashScreenActivity(): Splash

    @ContributesAndroidInjector
    @ActivityScope
    fun provideCountryListActivityActivity(): CountryListActivity
}