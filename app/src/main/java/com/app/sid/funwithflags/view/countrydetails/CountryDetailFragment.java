package com.app.sid.funwithflags.view.countrydetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.utils.ProgressHelper;
import com.app.sid.funwithflags.utils.Utils;
import com.app.sid.funwithflags.view.wikiview.WikiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;


public class CountryDetailFragment extends Fragment implements CountryDetailContract.View {

    public static final String COUNTRY_NAME = "countrycode";
    public static final String ALPHA_CODE = "ALPHA_CODE";

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.img_flag)
    public ImageView mImgFlag;

    @BindView(R.id.progressBar)
    public ProgressBar mProgress;

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


    private CountryDetailContract.Presenter mPresenter;

    private Unbinder unbinder;

    private String mCountryName;
    private String mAlphaCode;

    public CountryDetailFragment() {
    }

    public static CountryDetailFragment newInstance(String countryName, String alphaCode) {
        CountryDetailFragment fragment = new CountryDetailFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(COUNTRY_NAME, countryName);
        mBundle.putString(ALPHA_CODE, alphaCode);
        fragment.setArguments(mBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCountryName = getArguments().getString(COUNTRY_NAME);
            mAlphaCode = getArguments().getString(ALPHA_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragmnet_country_detail, container, false);
        unbinder = ButterKnife.bind(this, root);
        mToolbar.setTitle(mCountryName);
        ((CountryDetailActivity) getActivity()).setSupportActionBar(mToolbar);
        ((CountryDetailActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return root;
    }

    public void onStart() {
        super.onStart();
        mPresenter.loadImage(mImgFlag, mAlphaCode);
        mPresenter.fetchCountry(mCountryName, mAlphaCode);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void showProgress() {
        if (!ProgressHelper.getInstance().isRunning())
            ProgressHelper.getInstance().showProgress(getContext(),
                    getString(R.string.please_wait), null);
    }

    @Override
    public void hideProgress() {
        ProgressHelper.getInstance().hideProgress();
    }

    @Override
    public void stopImageProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setupView(CountryDTO countryDTO) {
        mContinent.setText(countryDTO.getRegion());
        mSubRegion.setText(countryDTO.getSubregion());
        mCapital.setText(countryDTO.getCapital());
        mTerritory.setText(countryDTO.getArea());
        mPopulation.setText(countryDTO.getPopulation());
        mNativeName.setText(countryDTO.getNativeName());
        mLanguage.setText(countryDTO.getLang());
        mCurrency.setText(countryDTO.getCurrency());
        mDomain.setText(countryDTO.getDomain());
        mPcode.setText(countryDTO.getPhonecode());
    }

    @Override
    public void setPresenter(@NonNull CountryDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void dispatchError(String error) {
        hideProgress();
        showMessage(error);
    }

    @Override
    public void dispatchError(int resId) {
        hideProgress();
        showMessage(getString(resId));
    }

    private void showMessage(String message) {
        Utils.showSnackBar(getView(),
                message, Snackbar.LENGTH_LONG, null);
    }


    @OnClick(R.id.btn_wiki)
    public void buttonWiki() {
        Intent intent = new Intent(getActivity(), WikiActivity.class);
        intent.putExtra(WikiActivity.C_NAME, mCountryName);
        startActivity(intent);
    }

    @OnClick(R.id.btn_map)
    public void buttonMap() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + mCountryName);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
