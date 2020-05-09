package com.stephen.jetpack.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.stephen.common.bean.BaseResp
import com.stephen.common.ui.BaseViewModel
import com.stephen.jetpack.adapter.CommonPageListAdapter

/**
 * create by stephen
 * on 2020/5/4
 */


abstract class CommonPageViewModel<T> : BaseViewModel() {
    abstract val adapter: CommonPageListAdapter<T, out ViewDataBinding>

    private val refreshing = MutableLiveData<Boolean>()
    protected val refresh = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private val hasmore = MutableLiveData<Boolean>()
    private val page = MutableLiveData(1)

    private val baseRespList = Transformations.switchMap(page) {
        getDataList(it)
    }


    var liveDataPage= Transformations.map(baseRespList) {
        refreshing.value = false
        loading.value = false
        it.data
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    abstract fun getDataList(position: Int): LiveData<BaseResp<PagedList<T>>>

    fun refresh() {
        page.value = 0
        refreshing.value = true
    }

    fun loadMore() {
        page.value = +1
        loading.value = true
    }
}