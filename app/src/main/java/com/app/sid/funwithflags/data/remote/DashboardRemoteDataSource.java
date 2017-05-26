package com.app.sid.funwithflags.data.remote;


import com.app.sid.funwithflags.data.DashboardDataSource;
import com.app.sid.funwithflags.datasets.DashboardDTO;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.network.FunWithFlagsService;

import java.util.List;

import rx.Observable;

public class DashboardRemoteDataSource implements DashboardDataSource<DashboardDTO> {

    private static DashboardRemoteDataSource INSTANCE;

    public static DashboardRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DashboardRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private DashboardRemoteDataSource() {
    }

    @Override
    public Observable<List<CountryDTO>> downloadCountries() {
        return FunWithFlagsService.Creator.newGetService().downloadCountries();
    }

    @Override
    public Observable<List<CountryDTO>> fetchCountries() {
        // Do nothing
        return null;
    }

    @Override
    public Observable<CountryDTO> fetchCountry(CountryDTO countryDTO) {
        // Do nothing
        return null;
    }

    @Override
    public void saveCountry(CountryDTO objCountry) {
        // Do nothing
    }

    @Override
    public void deleteAllCountries() {
        // Do nothing
    }

    @Override
    public void deleteCountry(CountryDTO countryDTO) {
        // Do nothing
    }
}
