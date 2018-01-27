package com.app.sid.funwithflags.di.modules;

import com.app.sid.funwithflags.model.api.CountryApi;
import com.app.sid.funwithflags.realm.RealmManager;
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
public class CountriesModule {

    CountryListMvp.View mView;

    public CountriesModule(CountryListMvp.View view) {
        mView = view;
    }

    @Provides
    public CountryListMvp.RemoteDataSource provideRemoteDataSource() {
        return new CountryRemoteDataSource(new CountryApi());
    }

    @Provides
    public CountryListMvp.LocalDataSource providesLocalDataSource(RealmManager realmManager) {
        return new CountryLocalDataSource(realmManager);
    }

    @Provides
    public CountryListMvp.Interactor providesInteractor(CountryListMvp.LocalDataSource localDataSource, CountryListMvp.RemoteDataSource remoteDataSource) {
        return new CountryListInteractor(localDataSource, remoteDataSource);
    }

    @Provides
    public CountryListPresenter providesPresenter(CountryListMvp.Interactor interactor) {
        return new CountryListPresenter(mView, interactor);
    }
}
