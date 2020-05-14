package com.stephen.jetpack.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stephen.common.ui.BaseViewModel
import com.stephen.jetpack.adapter.CommonPageListAdapter
import com.stephen.jetpack.data.compat.ListDataSourceFactory
import com.stephen.jetpack.net.status.Status
import com.stephen.jetpack.utils.netError
import com.stephen.jetpack.utils.noData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * create by stephen
 * on 2020/5/4
 */


abstract class CommonPageViewModel<T> : BaseViewModel() {

    private var firstTime = true

    abstract val adapter: CommonPageListAdapter<T, out ViewDataBinding>

    private val compositeDisposable = CompositeDisposable()

    var pagedList: LiveData<PagedList<T>>

    private var listDataSourceFactory: ListDataSourceFactory<T>

    private val refreshing = MutableLiveData<Boolean>()

    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setInitialLoadSizeHint(20)
        .setEnablePlaceholders(false)
        .build()


    init {
        listDataSourceFactory = ListDataSourceFactory(::getDataList, compositeDisposable)
        pagedList = LivePagedListBuilder<Int, T>(listDataSourceFactory, config).build()
    }

    val refresh = {
        firstTime = false
        listDataSourceFactory.listMutableList.value?.invalidate()
    }

    fun retry() = listDataSourceFactory.listMutableList.value?.retry()


    fun loadEnd() {
        Transformations.switchMap(listDataSourceFactory.listMutableList) {
            Transformations.map(it.networkState) {
                it.status == Status.FAILED && it.throwable != null && it.throwable.noData()
            }
        }
    }


    fun initNetError() {
        Transformations.switchMap(listDataSourceFactory.listMutableList) {
            Transformations.map(it.initialLoad) {
                it.status == Status.FAILED && it.throwable != null && it.throwable.netError() && firstTime
            }
        }
    }

    fun netState() =
        Transformations.switchMap(listDataSourceFactory.listMutableList) { it.networkState }

    val loadMore = {
        pagedList.apply {
            value?.loadAround(this.value?.size!!)
        }
    }

    val loading: LiveData<Boolean> =
        Transformations.switchMap(listDataSourceFactory.listMutableList) {
            Transformations.map(it.initialLoad) {
                it.status == Status.LOADING && firstTime
            }
        }


    private fun loadData() {
        pagedList.observeForever {
            adapter.submitList(it)
        }
    }


    override fun onStart() {
        loadData()
    }

    override fun onStop() {
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


    abstract fun getDataList(position: Int): Observable<List<T>>


}