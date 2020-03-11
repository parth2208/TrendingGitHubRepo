package com.parthbhardwaj.trendinggithubrepo.viewModel

import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import com.parthbhardwaj.trendinggithubrepo.R
import com.parthbhardwaj.trendinggithubrepo.databinding.ActivityMainBinding
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoDao
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoTable
import com.parthbhardwaj.trendinggithubrepo.remote.RepoApiService
import com.parthbhardwaj.trendinggithubrepo.ui.adapter.TrendingListAdapter
import com.parthbhardwaj.trendinggithubrepo.utils.BaseViewModel
import com.parthbhardwaj.trendinggithubrepo.utils.SharedPreferencesHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel(
    private val binding: ActivityMainBinding,
    private val repoDao: RepoDao
) : BaseViewModel() {

    @Inject
    lateinit var repoApiService: RepoApiService

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    val errorClickListener = View.OnClickListener { loadRepositories() }

    val itemListAdapter: TrendingListAdapter = TrendingListAdapter()

    val sharedPreferencesHelper: SharedPreferencesHelper = SharedPreferencesHelper(binding.root.context)

    init {
        loadRepositories()
        binding.simpleSwipeRefreshLayout.setOnRefreshListener {
            binding.simpleSwipeRefreshLayout.isRefreshing = false
            loadRepositories()
        }
    }

    private fun loadRepositories(){
        val minutes =
            TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - sharedPreferencesHelper.getValueLong("TIME")!!)

        subscription = Observable.fromCallable { repoDao.completeRepository }
            .concatMap { dbRepositoryList ->
                if (minutes >= 120 || dbRepositoryList.isEmpty())
                    repoApiService.getRepositories().concatMap { apiRepositoryList ->
                        repoDao.insertAll(*apiRepositoryList.toTypedArray())
                        sharedPreferencesHelper.save("TIME", System.currentTimeMillis())
                        Observable.just(apiRepositoryList)
                    }
                else
                    Observable.just(dbRepositoryList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart(){
        errorVisibility.value = View.GONE
        binding.shimmerViewContainer.startShimmerAnimation()
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
        errorVisibility.value = View.GONE
        binding.shimmerViewContainer.stopShimmerAnimation()
    }

    private fun onRetrievePostListSuccess(repositoryList: List<RepoTable>){
        itemListAdapter.updateItemList(repositoryList)
    }

    private fun onRetrievePostListError() {
        errorVisibility.value = View.VISIBLE
        errorMessage.value = R.string.repo_error
        binding.shimmerViewContainer.stopShimmerAnimation()
    }

    fun onClickToolBarMenu(v: View) {
        showFilterPopup(v)
    }

    private fun showFilterPopup(v: View) {
        val popup =
            PopupMenu(v.context, v, Gravity.END, 0, R.style.OverflowMenu)
        popup.inflate(R.menu.main_menu)

        popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    else -> return false
                }
            }
        })
        popup.show()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}