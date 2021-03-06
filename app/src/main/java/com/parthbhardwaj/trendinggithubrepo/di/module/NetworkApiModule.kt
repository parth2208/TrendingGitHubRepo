package com.parthbhardwaj.trendinggithubrepo.di.module

import com.parthbhardwaj.trendinggithubrepo.remote.RepoApiService
import com.parthbhardwaj.trendinggithubrepo.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This is the provider for all network required dependencies
 */
@Module
@Suppress("unused")
object NetworkApiModule {

    /**
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): RepoApiService {
        return retrofit.create(RepoApiService::class.java)
    }

    /**
     * @param gson
     * @return retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}