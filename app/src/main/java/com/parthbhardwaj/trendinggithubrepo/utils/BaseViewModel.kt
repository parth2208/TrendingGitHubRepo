package com.parthbhardwaj.trendinggithubrepo.utils

import androidx.lifecycle.ViewModel
import com.parthbhardwaj.trendinggithubrepo.di.component.RepoAppComponent
import com.parthbhardwaj.trendinggithubrepo.di.module.NetworkApiModule
import com.parthbhardwaj.trendinggithubrepo.viewModel.MainViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: RepoAppComponent = DaggerRepoAppComponent
        .builder()
        .networkModule(NetworkApiModule)
        .build()

    init {

    }

    private fun inject(){
        when(this){
            is MainViewModel -> injector.inject(this)
        }
    }

}