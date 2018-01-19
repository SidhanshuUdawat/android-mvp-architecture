package com.app.sid.funwithflags.view.countrylist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;
import com.app.sid.funwithflags.utils.Connectivity;
import com.app.sid.funwithflags.utils.DividerItemDecoration;
import com.app.sid.funwithflags.utils.EspressoIdlingResource;
import com.app.sid.funwithflags.view.countrydetails.CountryDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CountryListActivity extends AppCompatActivity implements CountryListMvp.View,
        CountryListAdapter.CountryInteraction {

    @BindView(R.id.list_countries)
    public RecyclerView mRecyclerView;

    @BindView(R.id.layout_no_internet)
    public LinearLayout mNoInternetConnection;

    @BindView(R.id.loading_layout)
    public LinearLayout mProgress;

    private List<CountryDTO> mCountriesList;
    private CountryListAdapter mListAdapter;
    private CountryListPresenter mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        unbinder = ButterKnife.bind(this);
        initToolBar();
        initList();
        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new CountryListPresenter(this);
        mPresenter.init();
    }

    private void initList() {
        mCountriesList = new ArrayList<>();
        mListAdapter = new CountryListAdapter(mCountriesList, this);
        mRecyclerView.setAdapter(mListAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
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
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showProgress(boolean isVisible) {
        mProgress.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }


    @Override
    public void showInternetError(boolean isVisible) {
        mNoInternetConnection.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean isInternetAvailable() {
        return Connectivity.getInstance().isConnected();
    }

    @Override
    public void updateCountries(List<CountryDTO> countryList) {
        int oldSize = mCountriesList.size();
        mCountriesList.addAll(countryList);
        mListAdapter.notifyItemRangeInserted(oldSize, countryList.size());
    }

    @Override
    public void OnListInteractionListener(View view, CountryDTO country, int pos) {
        Intent intent = new Intent(this, CountryDetailActivity.class);
        intent.putExtra(CountryDetailActivity.SELECTED_COUNTRY, new SelectedCountry(country.getName(), country.getAlpha2Code()));
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ImageView img_flag = (ImageView) view.findViewById(R.id.img_flag);
            TextView country_name = (TextView) view.findViewById(R.id.txt_country_name);
            Pair<View, String> p1 = Pair.create((View) img_flag, getString(R.string.trans_country));
            Pair<View, String> p2 = Pair.create((View) country_name, getString(R.string.trans_country_txt));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_countries_list, menu);
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
                if (mListAdapter != null) {
                    mListAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        return true;
    }

    @OnClick(R.id.btn_retry)
    public void retryButton() {
        mPresenter.onRetry();
    }
}
