package com.parthbhardwaj.trendinggithubrepo.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.parthbhardwaj.trendinggithubrepo.R
import com.parthbhardwaj.trendinggithubrepo.databinding.ActivityMainBinding
import com.parthbhardwaj.trendinggithubrepo.viewModel.MainViewModel
import com.parthbhardwaj.trendinggithubrepo.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.repoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this,binding)).get(MainViewModel::class.java)
        binding.viewModel = viewModel

    }
}
