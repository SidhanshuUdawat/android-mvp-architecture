
package com.app.sid.fun.withflags;


import android.content.Context;
import android.support.annotation.NonNull;

import com.app.sid.fun.withflags.data.FakeRemoteDataSource;
import com.app.sid.funwithflags.data.DashboardRepository;
import com.app.sid.funwithflags.data.local.DashboardLocalDataSource;
import com.app.sid.funwithflags.utils.schedulers.BaseSchedulerProvider;
import com.app.sid.funwithflags.utils.schedulers.SchedulerProvider;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static DashboardRepository provideRepository(@NonNull Context context) {
        checkNotNull(context);
        return DashboardRepository.getInstance(FakeRemoteDataSource.getInstance(context),
                DashboardLocalDataSource.getInstance(context, provideSchedulerProvider()));
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
