package com.app.sid.funwithflags.di.modules;

import com.app.sid.funwithflags.model.api.CountryApi;
import com.app.sid.funwithflags.realm.RealmManager;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailInteractor;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailLocalDataSource;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailMvp;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailPresenter;
import com.app.sid.funwithflags.view.countrylist.CountryListInteractor;
import com.app.sid.funwithflags.view.countrylist.CountryListMvp;
import com.app.sid.funwithflags.view.countrylist.CountryListPresenter;
import com.app.sid.funwithflags.view.countrylist.CountryLocalDataSource;
import com.app.sid.funwithflags.view.countrylist.CountryRemoteDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */
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
