package com.stephen.jetpack.base

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.stephen.common.bean.BaseResp
import com.stephen.common.ui.BaseViewModel
import com.stephen.jetpack.adapter.CommonPageListAdapter

/**
 * create by stephen
 * on 2020/5/4
 */


abstract class CommonPageViewModel<T> : BaseViewModel() {
    val refreshTrigger = ObservableBoolean()
    val loading = MutableLiveData<Boolean>()
    val page = MutableLiveData<Int>()
    val refreshing = ObservableBoolean()
    val moreLoading = ObservableBoolean()
    val hasMore = ObservableBoolean()
    val autoRefresh = ObservableBoolean()
    abstract val adapter: CommonPageListAdapter<T, out ViewDataBinding>

    fun loadMore() {
        page.value = (page.value?:1)+1
        moreLoading.set(true)
    }

    fun autoRefresh() {
        autoRefresh.set(true)
    }

    fun refresh() {
        refreshing.set(true)
        page.value = 1
    }

    fun loadData() {
        refreshTrigger.set(true)
        loading.value = true
    }

    fun attachLoading(otherState: MutableLiveData<Boolean>) {
        loading.observeForever {
            otherState.value = it
        }
    }

    fun addData(data: List<T>, isFirst: Boolean) {
        adapter.addData(data, isFirst)
    }

    open fun onItemClick(view: View, position: Int) = Unit

    override fun onStart() = Unit

    override fun onStop() = Unit

    open fun getDataList(source: LiveData<BaseResp<List<T>>>): LiveData<List<T>> {
        return Transformations.map(source) {
            refreshing.set(false)
            moreLoading.set(false)
            hasMore.set(it?.data?.size !in 0 until 10)
            it.data
        }
    }

}