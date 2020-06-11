package com.app.sid.funwithflags.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder().apply {
                addInterceptor(loggingInterceptor)
            }.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl("www.t")
                    .client(okHttpClient)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
}
