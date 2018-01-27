package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.Countries;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryListInteractor implements CountryListMvp.Interactor {

    private final CountryListMvp.RemoteDataSource mRemoteDataSource;
    private final CountryListMvp.LocalDataSource mLocalDataSource;

    public CountryListInteractor(CountryListMvp.LocalDataSource localDataSource, CountryListMvp.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<List<Countries>> getCountryList() {
        if (mLocalDataSource.isLocalDataPresent()) {
            return mLocalDataSource.getCountryList();
        } else {
            return mRemoteDataSource.getCountryList()
                    .flatMap(new Func1<List<Countries>, Observable<Countries>>() {
                        @Override
                        public Observable<Countries> call(List<Countries> countries) {
                            return Observable.from(countries)
                                    .doOnNext(new Action1<Countries>() {
                                        @Override
                                        public void call(Countries countries) {
                                            mLocalDataSource.saveCountry(countries);
                                        }
                                    });
                        }
                    }).toList();
        }
    }
}
