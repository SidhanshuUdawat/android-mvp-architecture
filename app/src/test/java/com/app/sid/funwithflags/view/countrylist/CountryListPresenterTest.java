package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.Countries;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Sidhanshu Udawat on 20-Jan-18.
 */
public class CountryListPresenterTest {

    private CountryListMvp.View mView;
    private CountryListMvp.Interactor mInteractor;
    private CountryListPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mView = mock(CountryListMvp.View.class);
        mInteractor = mock(CountryListMvp.Interactor.class);
        mPresenter = new CountryListPresenter(mView);
    }

    @Test
    public void given_init_when_internet_not_available_then_show_error_screen() {
        when(mView.isInternetAvailable()).thenReturn(false);
        mPresenter.init();
        verify(mView).isInternetAvailable();
        verify(mView).showInternetError(true);
        verifyNoMoreInteractions(mView);
    }

    @Test
    public void given_init_when_internet_available_then_show_countries() {
        when(mView.isInternetAvailable()).thenReturn(true);
        when(mInteractor.getCountryList()).thenReturn(Observable.<List<Countries>>empty());
        mPresenter.init();
        verify(mView).isInternetAvailable();
        verify(mView).showProgress(true);
        verify(mInteractor).getCountryList();
        verifyNoMoreInteractions(mView, mInteractor);
    }
}