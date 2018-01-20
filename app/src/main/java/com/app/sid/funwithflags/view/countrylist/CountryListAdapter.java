package com.app.sid.funwithflags.view.countrylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CountryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {


    public interface OnCountryListAdapterInteraction {
        void onCountryClicked(View v, CountryDTO object, int pos);
    }

    private List<CountryDTO> mValues;
    private List<CountryDTO> mFilteredList;
    private OnCountryListAdapterInteraction mListener;

    public CountryListAdapter(List<CountryDTO> countries,
                              OnCountryListAdapterInteraction listener) {
        mValues = checkNotNull(countries);
        mFilteredList = checkNotNull(countries);
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.country_list_item, parent, false);
        return new CountryViewHolder(view, new CountryViewHolder.OnCountryViewHolderInteraction() {
            @Override
            public void onCountryClicked(View v, CountryDTO countryModel, int pos) {
                mListener.onCountryClicked(v, countryModel, pos);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CountryViewHolder) {
            CountryViewHolder holderView = (CountryViewHolder) holder;
            holderView.bind(mFilteredList.get(position));
        }
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
                    for (CountryDTO country : mValues) {
                        if (country.getName().toLowerCase().contains(charString.toLowerCase())
                                || country.getCapital().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(country);
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
}
