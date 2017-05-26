package com.app.sid.funwithflags.view.wikiview;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.data.DashboardRepository;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.network.FunWithFlagsService;
import com.app.sid.funwithflags.utils.schedulers.BaseSchedulerProvider;
import com.app.sid.funwithflags.view.FunWithFlagsApp;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;


public class WikiPresenter implements WikiContract.Presenter {


    @NonNull
    private final WikiContract.View mView;

    public WikiPresenter(@NonNull WikiContract.View view) {
        mView = checkNotNull(view, "view cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }

    @Override
    public boolean isGoBack() {
        return mView.isGoBack();
    }
}
