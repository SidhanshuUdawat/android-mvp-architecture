package com.app.sid.funwithflags.di.modules

import com.app.sid.funwithflags.realm.RealmManager
import com.app.sid.funwithflags.view.countrylist.CountryListInteractor
import com.app.sid.funwithflags.view.countrylist.CountryListMvp
import com.app.sid.funwithflags.view.countrylist.CountryListMvp.RemoteDataSource
import com.app.sid.funwithflags.view.countrylist.CountryListPresenter
import com.app.sid.funwithflags.view.countrylist.CountryLocalDataSource
import com.app.sid.funwithflags.view.countrylist.CountryRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class CountryListModule {

    @Provides
    fun provideRemoteDataSource(): RemoteDataSource = CountryRemoteDataSource()

    @Provides
    fun providesLocalDataSource(realmManager: RealmManager): CountryListMvp.LocalDataSource =
            CountryLocalDataSource(realmManager)

    @Provides
    fun providesInteractor(
            localDataSource: CountryListMvp.LocalDataSource,
            remoteDataSource: RemoteDataSource
    ): CountryListMvp.Interactor = CountryListInteractor(localDataSource, remoteDataSource)

    @Provides
    fun providesPresenter(interactor: CountryListMvp.Interactor): CountryListPresenter =
            CountryListPresenter(interactor)

}