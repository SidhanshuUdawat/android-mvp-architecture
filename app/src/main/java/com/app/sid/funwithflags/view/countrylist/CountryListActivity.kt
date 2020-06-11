package com.app.sid.funwithflags.view.countrylist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.app.sid.funwithflags.R
import com.app.sid.funwithflags.datasets.remote.Country
import com.app.sid.funwithflags.datasets.remote.SelectedCountry
import com.app.sid.funwithflags.utils.Connectivity
import com.app.sid.funwithflags.utils.DividerItemDecoration
import com.app.sid.funwithflags.view.BaseActivity
import com.app.sid.funwithflags.view.countrydetails.CountryDetailActivity
import com.app.sid.funwithflags.view.countrylist.CountryListAdapter.OnCountryListAdapterInteraction
import javax.inject.Inject

internal class CountryListActivity : BaseActivity(), CountryListMvp.View, OnCountryListAdapterInteraction {

    private val recyclerView = findViewById<RecyclerView>(R.id.list_countries)
    private val noInternetConnection = findViewById<LinearLayout>(R.id.layout_no_internet)
    private val progress = findViewById<LinearLayout>(R.id.loading_layout)
    private val retryButton = findViewById<Button>(R.id.btn_retry)

    private lateinit var actionMenuItem: MenuItem
    private lateinit var searchView: SearchView
    private lateinit var countryList: MutableList<Country>
    private lateinit var listAdapter: CountryListAdapter

    @Inject
    internal lateinit var mPresenter: CountryListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)
        initToolBar()
        initList()
        mPresenter.bind(this)
        retryButton.setOnClickListener {
            mPresenter.onRetry()
        }
    }

    private fun initList() {
        countryList = ArrayList()
        listAdapter = CountryListAdapter(countryList, this)
        val linearLayoutManager = LinearLayoutManager(this)
        val itemDecoration: ItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.run {
            adapter = listAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecoration)
        }
    }

    private fun initToolBar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setIcon(R.mipmap.ic_launcher)
        }
    }

    public override fun onPause() {
        super.onPause()
        mPresenter.onPause()
    }

    override fun showProgress(isVisible: Boolean) {
        progress.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showInternetError(isVisible: Boolean) {
        noInternetConnection.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun isInternetAvailable(): Boolean {
        return Connectivity.getInstance().isConnected
    }

    override fun updateCountries(countryList: List<Country>) {
        val oldSize = this.countryList.size
        this.countryList.addAll(countryList)
        listAdapter.notifyItemRangeInserted(oldSize, countryList.size)
    }

    override fun onQueryTextSubmit(query: String) {
        if (!searchView.isIconified) {
            searchView.isIconified = true
        }
        actionMenuItem.collapseActionView()
    }

    override fun onQueryTextChange(query: String) {
        listAdapter.filter.filter(query)
    }

    override fun onCountryClicked(view: View, country: Country, pos: Int) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        intent.putExtra(CountryDetailActivity.SELECTED_COUNTRY, SelectedCountry(country.name, country.alpha2Code))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val img_flag = view.findViewById<View>(R.id.img_flag) as ImageView
            val country_name = view.findViewById<View>(R.id.txt_country_name) as TextView
            val p1 = Pair.create(img_flag as View, getString(R.string.trans_country))
            val p2 = Pair.create(country_name as View, getString(R.string.trans_country_txt))
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2)
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_countries_list, menu)
        actionMenuItem = menu.findItem(R.id.action_search)
        searchView = actionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mPresenter.onQueryTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mPresenter.onQueryTextChange(newText)
                return false
            }
        })
        return true
    }
}