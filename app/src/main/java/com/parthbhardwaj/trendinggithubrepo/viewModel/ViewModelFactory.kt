package com.parthbhardwaj.trendinggithubrepo.viewModel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.parthbhardwaj.trendinggithubrepo.databinding.ActivityMainBinding
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoDatabase

class ViewModelFactory(private val activity: AppCompatActivity,
                       private val binding: ActivityMainBinding

) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, RepoDatabase::class.java, "posts").build()
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(binding,db.repoDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")    }

}