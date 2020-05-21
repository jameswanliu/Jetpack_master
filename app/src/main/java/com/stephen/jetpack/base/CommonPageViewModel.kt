package com.stephen.jetpack.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.jdsjlzx.interfaces.OnRefreshListener
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

    var firstTime = MutableLiveData<Boolean>(true)

    abstract val adapter: CommonPageListAdapter<T, out ViewDataBinding>

    private val compositeDisposable = CompositeDisposable()

    private var pageSize = MutableLiveData<Int>()

    private var pagedList: LiveData<PagedList<T>>

    private var listDataSourceFactory: ListDataSourceFactory<T>

    private val refreshing = MutableLiveData<Boolean>()

    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setInitialLoadSizeHint(20)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(1)
        .build()


    init {
        listDataSourceFactory = ListDataSourceFactory(::getDataList, compositeDisposable)
        pagedList = LivePagedListBuilder<Int, T>(listDataSourceFactory, config).build()
    }

    open fun refresh() {
        firstTime.postValue(false)
        listDataSourceFactory.listMutableList.value?.invalidate()
    }


    fun retry() = listDataSourceFactory.listMutableList.value!!.retry()

    fun loadEnd() {
        Transformations.switchMap(listDataSourceFactory.listMutableList) {
            Transformations.map(it.networkState) {
                it.status == Status.FAILED && it.throwable != null && it.throwable.noData()
            }
        }
    }


    fun loadMore() {

    }


    /**
     * 刷新完成
     */
   val refreshCompelete =
        Transformations.switchMap(listDataSourceFactory.listMutableList) {
            it.refreshCompelete
        }

    open fun onItemClick(view: View, position: Int) = Unit


    fun initNetError() {
        Transformations.switchMap(listDataSourceFactory.listMutableList) {
            Transformations.map(it.initialLoad) {
                it.status == Status.FAILED && it.throwable != null && it.throwable.netError() && firstTime.value!!
            }
        }
    }

    fun netState() =
        Transformations.switchMap(listDataSourceFactory.listMutableList) { it.networkState }


    val loading: LiveData<Boolean> =
        Transformations.switchMap(listDataSourceFactory.listMutableList) {
            Transformations.map(it.initialLoad) {
                it.status == Status.LOADING && firstTime.value!!
            }
        }


    private fun loadData() {
        pagedList.observeForever {
            adapter.submitList(it)
        }
    }


    override fun onStart() = loadData()

    override fun onStop() {
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


    abstract fun getDataList(position: Int): Observable<List<T>>


}