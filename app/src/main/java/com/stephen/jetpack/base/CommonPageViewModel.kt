package com.stephen.jetpack.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.stephen.common.ui.BaseViewModel
import com.stephen.jetpack.adapter.CommonPageListAdapter
import com.stephen.jetpack.base.paging.PageDateSourceFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * create by stephen
 * on 2020/5/4
 */


abstract class CommonPageViewModel<T> : BaseViewModel() {
    abstract val adapter: CommonPageListAdapter<T, out ViewDataBinding>
    private lateinit var compositeDisposable: CompositeDisposable
    lateinit var liveDataPage: LiveData<PagedList<T>>
    protected val NUM_PER_PAGE = 15
    protected val PAGE_FIRST = 1

    override fun onStart() {
        compositeDisposable = CompositeDisposable()
        initPagedList()
    }

    override fun onStop() {
        compositeDisposable.clear()
    }

    abstract fun getDataList(position: Int): Observable<T>


    private fun callDataList(
        position: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, T>?,
        initCallback: PageKeyedDataSource.LoadInitialCallback<Int, T>?
    ) {
        getDataList(position).doOnSubscribe {compositeDisposable.add(it)}
    }

    /**
     * 初始化PageList
     */
    private fun initPagedList() {
        val pageKeyedDataSource = object : PageKeyedDataSource<Int, T>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, T>
            ) = callDataList(PAGE_FIRST, null, callback)

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) =
                callDataList(params.key, callback, null)

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            }
        }
        liveDataPage = LivePagedListBuilder<Int, T>(
            PageDateSourceFactory(pageKeyedDataSource),
            PagedList.Config.Builder().setPageSize(NUM_PER_PAGE)//每次加载的数据数量15
                .setPrefetchDistance(NUM_PER_PAGE)//15
                .setEnablePlaceholders(false).setInitialLoadSizeHint(NUM_PER_PAGE)//15
                .build()
        ).build()


    }

}