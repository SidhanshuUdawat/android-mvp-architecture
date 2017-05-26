package com.app.sid.funwithflags.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.LruCache;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

public class DashboardRepository implements DashboardDataSource {

    @Nullable
    private static DashboardRepository INSTANCE = null;

    @NonNull
    private final DashboardDataSource mDashRemoteDataSource;

    @NonNull
    private final DashboardDataSource mDashLocalDataSource;

    private LruCache<Class<?>, Observable<?>> apiObservables = new LruCache<>(10);

    @VisibleForTesting
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private DashboardRepository(@NonNull DashboardDataSource tasksRemoteDataSource,
                                @NonNull DashboardDataSource tasksLocalDataSource) {
        mDashRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mDashLocalDataSource = checkNotNull(tasksLocalDataSource);
    }


    public static DashboardRepository getInstance(@NonNull DashboardDataSource tasksRemoteDataSource,
                                                  @NonNull DashboardDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DashboardRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public Observable<List<CountryDTO>> downloadCountries() {
        return mDashRemoteDataSource.downloadCountries();
    }

    @Override
    public Observable<List<CountryDTO>> fetchCountries() {
        return mDashLocalDataSource.fetchCountries();
    }

    @Override
    public Observable<CountryDTO> fetchCountry(CountryDTO countryDTO) {
        return mDashLocalDataSource.fetchCountry(countryDTO);
    }

    @Override
    public void saveCountry(CountryDTO objCountry) {
        mDashLocalDataSource.saveCountry(objCountry);
    }

    @Override
    public void deleteAllCountries() {
        mDashLocalDataSource.deleteAllCountries();
    }

    @Override
    public void deleteCountry(CountryDTO countryDTO) {
        mDashLocalDataSource.deleteCountry(countryDTO);
    }


    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable,
                                               Class<?> clazz,
                                               boolean cacheObservable,
                                               boolean useCache) {
        Observable<?> preparedObservable = null;

        if (useCache) //this way we don't reset anything in the cache if this is the only instance of us not wanting to use it.
            preparedObservable = apiObservables.get(clazz);

        if (preparedObservable != null) {
            return preparedObservable;
        }

        //we are here because we have never created this observable before or we didn't want to use the cache...

        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        if (cacheObservable) {
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz, preparedObservable);
        }

        return preparedObservable;
    }
}
