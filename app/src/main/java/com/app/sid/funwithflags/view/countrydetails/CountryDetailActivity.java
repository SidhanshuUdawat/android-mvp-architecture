package com.app.sid.funwithflags.view.countrydetails;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.app.sid.fun.withflags.Injection;
import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.utils.ActivityUtils;
import com.app.sid.funwithflags.utils.EspressoIdlingResource;

public class CountryDetailActivity extends AppCompatActivity {

    private CountryDetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        String country_name = getIntent().getStringExtra(CountryDetailFragment.COUNTRY_NAME);
        String alphaCode = getIntent().getStringExtra(CountryDetailFragment.ALPHA_CODE);


        CountryDetailFragment tasksFragment =
                (CountryDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = CountryDetailFragment.newInstance(country_name, alphaCode);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }

        // Create the presenter
        mPresenter = new CountryDetailPresenter(Injection.provideRepository(getApplicationContext()),
                tasksFragment,
                Injection.provideSchedulerProvider());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    this.supportFinishAfterTransition();
                } else {
                    this.finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
