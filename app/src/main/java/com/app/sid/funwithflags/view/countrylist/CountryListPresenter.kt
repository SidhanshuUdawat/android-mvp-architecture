package com.app.sid.funwithflags.view.countrylist

import com.app.sid.funwithflags.datasets.remote.Country
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class CountryListPresenter @Inject constructor(
        private val interactor: CountryListMvp.Interactor
) {
    private lateinit var view: CountryListMvp.View
    private val subscriptions: CompositeSubscription = CompositeSubscription()

    fun onPause() {
        unsubscribe()
    }

    fun bind(view: CountryListMvp.View) {
        this.view = view
        fetchCountries()
    }

    fun onRetry() {
        view.showInternetError(true)
        fetchCountries()
    }

    private fun fetchCountries() {
        if (!view.isInternetAvailable()) {
            view.showInternetError(true)
        } else {
            view.showProgress(true)
            subscriptions.clear()
            val subscription = interactor.getCountryList()
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeOn(Schedulers.io())
                    ?.subscribe(object : Subscriber<List<Country?>?>() {
                        override fun onCompleted() {}
                        override fun onError(e: Throwable) {
                            loadingCountriesFailed()
                        }

                        override fun onNext(countries: List<Country?>?) {
                            countries?.filterNotNull()?.let {
                                loadCountries(it)
                            }
                        }
                    })
            subscriptions.add(subscription)
        }
    }

    private fun loadingCountriesFailed() {
        view.showProgress(false)
        view.showInternetError(true)
    }

    private fun loadCountries(countries: List<Country>) {
        view.updateCountries(countries)
        view.showProgress(false)
    }

    fun onQueryTextSubmit(query: String) {
        view.onQueryTextSubmit(query)
    }

    fun onQueryTextChange(query: String) {
        view.onQueryTextChange(query)
    }

    private fun unsubscribe() {
        subscriptions.clear()
    }

}