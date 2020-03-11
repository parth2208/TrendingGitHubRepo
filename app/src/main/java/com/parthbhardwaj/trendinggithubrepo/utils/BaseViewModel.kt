package com.parthbhardwaj.trendinggithubrepo.utils

import androidx.lifecycle.ViewModel
import com.parthbhardwaj.trendinggithubrepo.di.component.AppComponent
import com.parthbhardwaj.trendinggithubrepo.di.component.DaggerAppComponent
import com.parthbhardwaj.trendinggithubrepo.di.module.NetworkApiModule
import com.parthbhardwaj.trendinggithubrepo.viewModel.MainViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .networkModule(NetworkApiModule)
        .build()

    init {
        inject()
    }

    private fun inject(){
        when(this){
            is MainViewModel -> injector.inject(this)
        }
    }

}