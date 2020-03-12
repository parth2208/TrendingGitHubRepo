package com.parthbhardwaj.trendinggithubrepo.di.component

import android.app.Application
import com.parthbhardwaj.trendinggithubrepo.di.module.DbModule
import com.parthbhardwaj.trendinggithubrepo.di.module.NetworkApiModule
import com.parthbhardwaj.trendinggithubrepo.viewModel.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * This component will provide inject() methods.
 */
@Component(modules = [DbModule::class,
                    NetworkApiModule::class])
@Singleton
interface AppComponent {

    fun inject (mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder{

//        @BindsInstance
//        fun application(application: Application): Builder

        fun build(): AppComponent

        fun networkModule(networkApiModule: NetworkApiModule): Builder

        fun databaseModule(dbModule: DbModule): Builder
    }
}