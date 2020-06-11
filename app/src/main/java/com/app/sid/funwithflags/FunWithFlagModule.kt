package com.app.sid.funwithflags

import com.app.sid.funwithflags.di.modules.CountryListModule
import dagger.Module

@Module(
        includes = [
            CountryListModule::class
        ]
)
class FunWithFlagModule