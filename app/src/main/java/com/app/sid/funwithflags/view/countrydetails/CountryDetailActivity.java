package com.app.sid.funwithflags.view.countrydetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.sid.funwithflags.FunWithFlagsApplication;
import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;
import com.app.sid.funwithflags.di.modules.CountryDetailsModule;
import com.app.sid.funwithflags.view.wikiview.WikiActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailMvp.View {

    public static final String SELECTED_COUNTRY = "selectedCountry";

    @BindView(R.id.img_flag)
    public ImageView mFlagImage;

    @BindView(R.id.headerImageProgress)
    public ProgressBar mHeaderImageProgress;

    @BindView(R.id.txt_continent)
    public TextView mContinent;

    @BindView(R.id.txt_subregion)
    public TextView mSubRegion;

    @BindView(R.id.txt_capital)
    public TextView mCapital;

    @BindView(R.id.txt_language)
    public TextView mLanguage;

    @BindView(R.id.txt_territory)
    public TextView mTerritory;

    @BindView(R.id.txt_population)
    public TextView mPopulation;

    @BindView(R.id.txt_nativename)
    public TextView mNativeName;

    @BindView(R.id.txt_currency)
    public TextView mCurrency;

    @BindView(R.id.txt_domain)
    public TextView mDomain;

    @BindView(R.id.txt_pcode)
    public TextView mPcode;

    private Unbinder unbinder;

    @Inject
    public CountryDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);
        SelectedCountry selectedCountry = getIntent().getParcelableExtra(SELECTED_COUNTRY);
        initToolbar(selectedCountry.getName());
        unbinder = ButterKnife.bind(this);
        mPresenter.init(selectedCountry);
    }

    private void initToolbar(String countryName) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(countryName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.supportFinishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showHeaderImageProgress(boolean isShown) {
        mHeaderImageProgress.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loadCachedFlag(String url) {
        Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(mFlagImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mPresenter.onFlagLoadedSuccessfully();
                    }

                    @Override
                    public void onError(Exception e) {
                        mPresenter.onCachedFlagLoadingFailed();
                    }
                });
    }

    @Override
    public void loadFlag(String url) {
        Picasso.get()
                .load(url)
                .error(R.drawable.flag_ind)
                .into(mFlagImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mPresenter.onFlagLoadedSuccessfully();
                    }

                    @Override
                    public void onError(Exception e) {
                        mPresenter.onFlagLoadingFailed();
                    }
                });
    }

    @Override
    public void setContinent(String value) {
        mContinent.setText(value);
    }

    @Override
    public void setSubRegion(String value) {
        mSubRegion.setText(value);
    }

    @Override
    public void setCapital(String value) {
        mCapital.setText(value);
    }

    @Override
    public void setTerritory(String value) {
        mTerritory.setText(value);
    }

    @Override
    public void setPopulation(String value) {
        mPopulation.setText(value);
    }

    @Override
    public void setNativeName(String value) {
        mNativeName.setText(value);
    }

    @Override
    public void setLanguage(String value) {
        mLanguage.setText(value);
    }

    @Override
    public void setCurrency(String value) {
        mCurrency.setText(value);
    }

    @Override
    public void setDomain(String value) {
        mDomain.setText(value);
    }

    @Override
    public void setPinCode(String value) {
        mPcode.setText(value);
    }

    @Override
    public void showWikiPage(String countryName) {
        Intent intent = new Intent(this, WikiActivity.class);
        intent.putExtra(WikiActivity.C_NAME, countryName);
        startActivity(intent);
    }

    @Override
    public void showMap(String countryName) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + countryName);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


    @OnClick(R.id.btn_wiki)
    public void onWikiClicked() {
        mPresenter.onWikiClicked();
    }

    @OnClick(R.id.btn_map)
    public void onMapClicked() {
        mPresenter.onMapClicked();
    }
}
