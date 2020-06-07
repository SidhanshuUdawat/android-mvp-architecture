package com.app.sid.funwithflags.di.modules

import android.content.Context
import com.app.sid.funwithflags.FunWithFlagsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(app: FunWithFlagsApplication): Context = app.applicationContext
}
