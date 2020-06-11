package com.app.sid.funwithflags.di.components

import android.content.Context
import com.app.sid.funwithflags.FunWithFlagModule
import com.app.sid.funwithflags.FunWithFlagsApplication
import com.app.sid.funwithflags.di.modules.ApiModule
import com.app.sid.funwithflags.di.modules.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            AndroidInjectionModule::class,
            ApiModule::class,
            FunWithFlagModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<FunWithFlagsApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
