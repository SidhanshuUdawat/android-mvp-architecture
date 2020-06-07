package com.app.sid.funwithflags.di.components;

import com.app.sid.funwithflags.di.modules.CountryDetailsModule;
import com.app.sid.funwithflags.di.provider.ApplicationBaseComponent;
import com.app.sid.funwithflags.di.scopes.PerActivity;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationBaseComponent.class},
        modules = {CountryDetailsModule.class})

public interface CountryDetailComponent {
    void inject(CountryDetailActivity view);
}

