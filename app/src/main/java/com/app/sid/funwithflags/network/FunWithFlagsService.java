package com.app.sid.funwithflags.network;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by sidhanshu.udawat on 21-Mar-17.
 */

public interface FunWithFlagsService {

    String URL_FLAG_L = "http://www.geonames.org/flags/l/%s.gif";
    String URL_FLAG_X = "http://www.geonames.org/flags/x/%s.gif";

    String URL_BASE = "https://restcountries.eu/rest/v2/";
    String URL_COUNTRIES = "all";

    @GET(URL_COUNTRIES)
    Observable<List<CountryDTO>> downloadCountries();

    /********
     * Helper class that sets up a new services
     *******/
    class Creator {

        public static FunWithFlagsService newPostService() {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(FunWithFlagsService.class);
        }

        public static FunWithFlagsService newGetService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(FunWithFlagsService.class);
        }
    }
}
