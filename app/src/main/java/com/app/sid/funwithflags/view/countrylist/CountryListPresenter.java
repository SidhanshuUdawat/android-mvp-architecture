package com.app.sid.funwithflags.view.countrylist;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.constants.AppConst;
import com.app.sid.funwithflags.data.DashboardRepository;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.utils.Connectivity;
import com.app.sid.funwithflags.utils.pref.PrefManager;
import com.app.sid.funwithflags.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;


public class CountryListPresenter implements CountryListContract.Presenter {
    
    @NonNull
    private final DashboardRepository mRepository;
    @NonNull
    private final CountryListContract.View mView;

    @NonNull
    private CompositeSubscription mSubscriptions;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    public CountryListPresenter(@NonNull DashboardRepository repository,
                                @NonNull CountryListContract.View view,
                                @NonNull BaseSchedulerProvider schedulerProvider) {
        mRepository = checkNotNull(repository, "repository cannot be null");
        mView = checkNotNull(view, "view cannot be null!");
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void isDataAvailable() {
        boolean isAvailable = PrefManager.getInstance().getBoolean(AppConst.IS_DATA_DOWNLOADED);
        if (isAvailable) {
            fetchCountries();
        } else {
            if (!Connectivity.getInstance().isConnected()) {
                mView.noInternet();
            } else {
                downloadData();
            }
        }
    }

    @Override
    public void downloadData() {
        mSubscriptions.clear();
        mView.showProgress();
        PrefManager.getInstance().saveBoolean(AppConst.IS_DATA_DOWNLOADED, false);
        mRepository.deleteAllCountries();

        Observable<List<CountryDTO>> cashedObserver = (Observable<List<CountryDTO>>)
                mRepository.getPreparedObservable(mRepository.downloadCountries(), CountryDTO.class, true, true);

        Subscription subscription = cashedObserver
                .flatMap(new Func1<List<CountryDTO>, Observable<CountryDTO>>() {
                    @Override
                    public Observable<CountryDTO> call(List<CountryDTO> countryDTOs) {
                        return Observable.from(countryDTOs)
                                .doOnNext(new Action1<CountryDTO>() {
                                    @Override
                                    public void call(CountryDTO countryDTO) {
                                        mRepository.saveCountry(countryDTO);
                                    }
                                });
                    }
                }).toList()
                .subscribe(new Subscriber<List<CountryDTO>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        mView.noInternet();
                    }

                    @Override
                    public void onNext(List<CountryDTO> countryDTOs) {
                        PrefManager.getInstance().saveBoolean(AppConst.IS_DATA_DOWNLOADED, true);
                        mView.refreshMessages(countryDTOs);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void fetchCountries() {
        mSubscriptions.clear();
        mView.showProgress();

        Subscription subscription = mRepository.fetchCountries()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Subscriber<List<CountryDTO>>() {
                    @Override
                    public void onCompleted() {
                        if (mView.isActive())
                            mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView.isActive()) {
                            mView.hideProgress();
                            mView.noInternet();
                        }
                    }

                    @Override
                    public void onNext(List<CountryDTO> countryDTOs) {
                        if (mView.isActive()) {
                            mView.refreshMessages(countryDTOs);
                            mView.hideProgress();
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
