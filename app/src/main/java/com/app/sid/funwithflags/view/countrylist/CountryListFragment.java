package com.app.sid.funwithflags.view.countrylist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.utils.DividerItemDecoration;
import com.app.sid.funwithflags.utils.Utils;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailActivity;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailFragment;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;


public class CountryListFragment extends Fragment implements CountryListContract.View,
        CountryListAdapter.CountryInteraction {

    @BindView(R.id.list_countries)
    public RecyclerView mRecyclerView;

    @BindView(R.id.layout_no_internet)
    public LinearLayout mLayoutNoInternet;

    @BindView(R.id.layout_loading)
    public LinearLayout mLayoutLoading;

    private CountryListContract.Presenter mPresenter;

    private CountryListAdapter objAdapter;

    private Unbinder unbinder;

    public CountryListFragment() {
    }

    public static CountryListFragment newInstance() {
        CountryListFragment fragment = new CountryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_countries_list, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    myActionMenuItem.collapseActionView();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (objAdapter != null) {
                    objAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_country_list, container, false);
        unbinder = ButterKnife.bind(this, root);
        initList();
        return root;
    }

    private void initList() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        objAdapter = new CountryListAdapter(Collections.<CountryDTO>emptyList(), this);
        mRecyclerView.setAdapter(objAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void onStart() {
        super.onStart();
        mPresenter.isDataAvailable();
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
        mLayoutNoInternet.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mLayoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLayoutLoading.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void noInternet() {
        mRecyclerView.setVisibility(View.GONE);
        mLayoutLoading.setVisibility(View.GONE);
        mLayoutNoInternet.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshMessages(List<CountryDTO> countryDTOs) {
        objAdapter.replaceData(countryDTOs);
        if (mRecyclerView.getVisibility() == View.GONE) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mLayoutNoInternet.setVisibility(View.GONE);
            mLayoutLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(@NonNull CountryListContract.Presenter presenter) {
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

    @Override
    public void OnListInteractionListener(View view, CountryDTO country, int pos) {
        Intent intent = new Intent(getActivity(), CountryDetailActivity.class);
        intent.putExtra(CountryDetailFragment.COUNTRY_NAME, country.getName());
        intent.putExtra(CountryDetailFragment.ALPHA_CODE, country.getAlpha2Code());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            ImageView img_flag = (ImageView) view.findViewById(R.id.img_flag);
            TextView country_name = (TextView) view.findViewById(R.id.txt_country_name);

            Pair<View, String> p1 = Pair.create((View) img_flag, getString(R.string.trans_country));
            Pair<View, String> p2 = Pair.create((View) country_name, getString(R.string.trans_country_txt));

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(getActivity(), p1, p2);

            startActivity(intent, options.toBundle());
        } else {
            getActivity().startActivity(intent);
        }

    }

    private void showMessage(String message) {
        Utils.showSnackBar(getView(),
                message, Snackbar.LENGTH_LONG, null);
    }

    @OnClick(R.id.btn_retry)
    public void buttonRetry() {
        mPresenter.isDataAvailable();
    }
}
