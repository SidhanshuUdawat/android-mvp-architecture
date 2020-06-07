package com.app.sid.funwithflags.di.modules;

import com.app.sid.funwithflags.realm.RealmManager;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailInteractor;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailLocalDataSource;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailMvp;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CountryDetailsModule {

    CountryDetailMvp.View mView;

    public CountryDetailsModule(CountryDetailMvp.View view) {
        mView = view;
    }

    @Provides
    public CountryDetailMvp.LocalDataSource providesLocalDataSource(RealmManager realmManager) {
        return new CountryDetailLocalDataSource(realmManager);
    }

    @Provides
    public CountryDetailMvp.Interactor providesInteractor(CountryDetailMvp.LocalDataSource localDataSource) {
        return new CountryDetailInteractor(localDataSource);
    }

    @Provides
    public CountryDetailPresenter providesPresenter(CountryDetailMvp.Interactor interactor) {
        return new CountryDetailPresenter(mView, interactor);
    }
}
