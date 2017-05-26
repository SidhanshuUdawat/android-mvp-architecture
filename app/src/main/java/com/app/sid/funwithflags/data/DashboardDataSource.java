package com.app.sid.funwithflags.data;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.List;

import rx.Observable;


public interface DashboardDataSource<T> {

    Observable<List<CountryDTO>> downloadCountries();

    Observable<List<CountryDTO>> fetchCountries();

    Observable<CountryDTO> fetchCountry(CountryDTO countryDTO);

    void saveCountry(CountryDTO objCountry);

    void deleteAllCountries();

    void deleteCountry(CountryDTO countryDTO);
}
