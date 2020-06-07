package com.app.sid.funwithflags.view.countrylist;


import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.datasets.remote.Country;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sidhanshu Udawat on 20-Jan-18.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder implements CountryViewHolderMvp.View {

    interface OnCountryViewHolderInteraction {
        void onCountryClicked(View v, Country countryModel, int pos);
    }

    private View mItemView;

    @BindView(R.id.img_flag)
    public ImageView mImgFlag;

    @BindView(R.id.progressBar)
    public ProgressBar mProgressBar;

    @BindView(R.id.txt_country_name)
    public TextView mTxtCountryName;

    @BindView(R.id.txt_country_capital)
    public TextView mTxtCountryCapital;

    private CountryViewHolderPresenter mPresenter;
    private OnCountryViewHolderInteraction mListener;

    public CountryViewHolder(View itemView, OnCountryViewHolderInteraction listener) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
        mPresenter = new CountryViewHolderPresenter(this);
        mListener = listener;
    }

    public void bind(Country country) {
        mPresenter.bind(country);
        mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onCountryClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void setCountryCapital(String value) {
        mTxtCountryCapital.setText(value);
    }

    @Override
    public void setCountryName(String value) {
        mTxtCountryName.setText(value);
    }

    @Override
    public void showProgress(boolean isShown) {
        mProgressBar.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setCachedFlagImage(String url) {
        Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(mImgFlag, new Callback() {
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
    public void setFlagImage(String url) {
        Picasso.get()
                .load(url)
                .error(R.drawable.flag_ind)
                .into(mImgFlag, new Callback() {
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
    public void onCountryClicked(Country countryModel, int pos) {
        mListener.onCountryClicked(itemView, countryModel, pos);
    }
}
