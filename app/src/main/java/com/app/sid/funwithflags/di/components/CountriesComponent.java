package com.app.sid.funwithflags.di.components;

import com.app.sid.funwithflags.di.modules.CountriesModule;
import com.app.sid.funwithflags.di.provider.ApplicationBaseComponent;
import com.app.sid.funwithflags.di.scopes.PerActivity;
import com.app.sid.funwithflags.view.countrylist.CountryListActivity;

import dagger.Component;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

@PerActivity
@Component(
        dependencies = {ApplicationBaseComponent.class},
        modules = {CountriesModule.class})

public interface CountriesComponent {
    void inject(CountryListActivity exploreView);
}

