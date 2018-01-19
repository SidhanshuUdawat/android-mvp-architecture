package com.app.sid.funwithflags.view.countrylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.view.FunWithFlagsApp;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class CountryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    String URL_FLAG_L = "http://www.geonames.org/flags/l/%s.gif";
    public interface CountryInteraction {
        void OnListInteractionListener(View v, CountryDTO object, int pos);
    }

    private List<CountryDTO> mValues;
    private List<CountryDTO> mFilteredList;
    private CountryInteraction mListener;

    public CountryListAdapter(List<CountryDTO> countries,
                              CountryInteraction listener) {
        setList(countries);
        mListener = listener;
    }

    public void replaceData(List<CountryDTO> countries) {
        setList(countries);
        notifyDataSetChanged();
    }

    private void setList(List<CountryDTO> countries) {
        mValues = checkNotNull(countries);
        mFilteredList = checkNotNull(countries);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.country_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holderView = (ViewHolder) holder;
        viewCountry(holderView, position);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    mFilteredList = mValues;
                } else {

                    List<CountryDTO> filteredList = new ArrayList<>();

                    for (CountryDTO androidVersion : mValues) {

                        if (androidVersion.getName().toLowerCase().contains(charString)
                                || androidVersion.getCapital().toLowerCase().contains(charString)) {
                            filteredList.add(androidVersion);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List<CountryDTO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private void viewCountry(final ViewHolder holder, final int position) {
        holder.mItem = mFilteredList.get(position);
        holder.mTxtCountryName.setText(holder.mItem.getName());
        holder.mTxtCountryCapital.setText(holder.mItem.getCapital());

        final String mFlagURL = String.format(URL_FLAG_L
                , holder.mItem.getAlpha2Code().toLowerCase());

        Picasso.with(FunWithFlagsApp.getApp().appContext())
                .load(mFlagURL)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.mImgFlag, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(FunWithFlagsApp.getApp().appContext())
                                .load(mFlagURL)
                                .error(R.drawable.flag_ind)
                                .into(holder.mImgFlag, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        holder.mProgressBar.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError() {
                                        holder.mProgressBar.setVisibility(View.GONE);
                                    }
                                });
                    }
                });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnListInteractionListener(v, holder.mItem, position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        @BindView(R.id.img_flag)
        public ImageView mImgFlag;

        @BindView(R.id.progressBar)
        public ProgressBar mProgressBar;

        @BindView(R.id.txt_country_name)
        public TextView mTxtCountryName;

        @BindView(R.id.txt_country_capital)
        public TextView mTxtCountryCapital;

        public CountryDTO mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
