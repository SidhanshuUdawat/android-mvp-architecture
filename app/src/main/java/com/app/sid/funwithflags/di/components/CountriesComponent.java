package com.app.sid.funwithflags.di.components;

import com.app.sid.funwithflags.di.modules.CountryListModule;
import com.app.sid.funwithflags.di.provider.ApplicationBaseComponent;
import com.app.sid.funwithflags.di.scopes.PerActivity;
import com.app.sid.funwithflags.view.countrylist.CountryListActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationBaseComponent.class},
        modules = {CountryListModule.class})

public interface CountriesComponent {
    void inject(CountryListActivity exploreView);
}

