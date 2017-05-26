package com.app.sid.funwithflags.view.countrydetails;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.data.DashboardRepository;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.network.FunWithFlagsService;
import com.app.sid.funwithflags.utils.schedulers.BaseSchedulerProvider;
import com.app.sid.funwithflags.view.FunWithFlagsApp;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;


public class CountryDetailPresenter implements CountryDetailContract.Presenter {

    @NonNull
    private final DashboardRepository mRepository;
    @NonNull
    private final CountryDetailContract.View mView;

    @NonNull
    private CompositeSubscription mSubscriptions;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;


    public CountryDetailPresenter(@NonNull DashboardRepository repository,
                                  @NonNull CountryDetailContract.View view,
                                  @NonNull BaseSchedulerProvider schedulerProvider) {
        mRepository = checkNotNull(repository, "repository cannot be null");
        mView = checkNotNull(view, "view cannot be null!");
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }


    @Override
    public void fetchCountry(String countryName, String alphaCode) {

        final CountryDTO country = new CountryDTO();
        country.setName(countryName);
        country.setAlpha2Code(alphaCode);

        mSubscriptions.clear();
        mView.showProgress();

        Subscription subscription = mRepository.fetchCountry(country)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Subscriber<CountryDTO>() {
                    @Override
                    public void onCompleted() {
                        if (mView.isActive())
                            mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView.isActive()) {
                            mView.hideProgress();
                        }
                    }

                    @Override
                    public void onNext(CountryDTO countryDTOs) {
                        if (mView.isActive()) {
                            mView.hideProgress();
                            mView.setupView(countryDTOs);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void loadImage(final ImageView mImage, String alphaCode) {

        final String mFlagURL = String.format(FunWithFlagsService.URL_FLAG_X,
                alphaCode.toLowerCase());

        Picasso.with(FunWithFlagsApp.getApp().appContext())
                .load(mFlagURL)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(mImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mView.stopImageProgress();
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(FunWithFlagsApp.getApp().appContext())
                                .load(mFlagURL)
                                .error(R.drawable.flag_ind)
                                .into(mImage, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        mView.stopImageProgress();
                                    }

                                    @Override
                                    public void onError() {
                                        mView.stopImageProgress();
                                    }
                                });
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
