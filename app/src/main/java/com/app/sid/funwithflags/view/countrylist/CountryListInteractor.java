package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

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

    public CountryListInteractor() {
        mRemoteDataSource = new CountryRemoteDataSource();
        mLocalDataSource = new CountryLocalDataSource();
    }

    @Override
    public Observable<List<CountryDTO>> getCountryList() {
        if (mLocalDataSource.isLocalDataPresent()) {
            return mLocalDataSource.getCountryList();
        } else {
            return mRemoteDataSource.getCountryList()
                    .flatMap(new Func1<List<CountryDTO>, Observable<CountryDTO>>() {
                        @Override
                        public Observable<CountryDTO> call(List<CountryDTO> countryDTOs) {
                            return Observable.from(countryDTOs)
                                    .doOnNext(new Action1<CountryDTO>() {
                                        @Override
                                        public void call(CountryDTO countryDTO) {
                                            mLocalDataSource.saveCountry(countryDTO);
                                        }
                                    });
                        }
                    }).toList();
        }
    }
}
