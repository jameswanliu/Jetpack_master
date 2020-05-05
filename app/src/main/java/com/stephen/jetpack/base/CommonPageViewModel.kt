package com.stephen.jetpack.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.stephen.common.ui.BaseViewModel
import com.stephen.jetpack.adapter.CommonPageListAdapter
import com.stephen.jetpack.base.paging.PageDateSourceFactory
import io.reactivex.Observable

/**
 * create by stephen
 * on 2020/5/4
 */


abstract class CommonPageViewModel<T> : BaseViewModel() {
    abstract val adapter: CommonPageListAdapter<T, out ViewDataBinding>
    lateinit var liveDataPage: LiveData<PagedList<T>>
    protected val NUM_PER_PAGE = 15
    protected val PAGE_FIRST = 1
    protected var mPage = PAGE_FIRST

    override fun onStart() {

    }


    abstract fun getDataList():Observable<PagedList<T>>


    /**
     * 初始化PageList
     */
    private fun initPagedList() {
        val positionalDataSource = object : PositionalDataSource<T>() {
            override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
            }

            override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
                val position = computeInitialLoadPosition(params, NUM_PER_PAGE)


            }
        }

        liveDataPage = LivePagedListBuilder(
            PageDateSourceFactory(positionalDataSource)//自己定义
            , PagedList.Config.Builder().setPageSize(NUM_PER_PAGE)//每次加载的数据数量15
                .setPrefetchDistance(NUM_PER_PAGE)//15
                .setEnablePlaceholders(false).setInitialLoadSizeHint(NUM_PER_PAGE)//15
                .build()
        ).build();

    }

}