package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.data.DashboardRepository;
import com.app.sid.funwithflags.utils.schedulers.BaseSchedulerProvider;
import com.app.sid.funwithflags.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sidhanshu Udawat on 26-May-17.
 */
public class CountryListPresenterTest {


    @Mock
    private DashboardRepository mTasksRepository;

    @Mock
    private CountryListContract.View mView;

    private BaseSchedulerProvider mSchedulerProvider;

    private CountryListPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mSchedulerProvider = new ImmediateSchedulerProvider();

        // The presenter wont't update the view unless it's active.
        when(mView.isActive()).thenReturn(true);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mPresenter = new CountryListPresenter(mTasksRepository,
                mView, mSchedulerProvider);

        // Then the presenter is set to the view
        verify(mView).setPresenter(mPresenter);
    }


    @After
    public void tearDown() throws Exception {
        mTasksRepository = null;

    }
}