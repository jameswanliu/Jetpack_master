package com.stephen.jetpack.base

import android.text.TextUtils
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.jdsjlzx.interfaces.OnRefreshListener
import com.stephen.common.bean.BaseResp
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

    val refreshTrigger = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    protected val page = MutableLiveData<Int>()
    val refreshing = MutableLiveData<Boolean>()
    val moreLoading = MutableLiveData<Boolean>()
    val hasMore = MutableLiveData<Boolean>()
    val autoRefresh = MutableLiveData<Boolean>()
    abstract val adapter: CommonPageListAdapter<T, out ViewDataBinding>


    fun loadMore() {
        page.value = (page.value ?: 1) + 1
        moreLoading.value = true
    }

    private fun autoRefresh() {
        autoRefresh.value = true
    }

    fun refresh() {
        page.value = 1
        refreshing.value = true
    }

    fun loadData() {
        refreshTrigger.value = true
        loading.value = true
    }

    fun attachLoading(otherState: MutableLiveData<Boolean>) {
        loading.observeForever {
            otherState.value = it
        }
    }


    fun addData(data: List<T>) {
        adapter.data.addAll(data)
        adapter.notifyDataSetChanged()
    }

    open fun onItemClick(view: View, position: Int) = Unit

    override fun onStart() =   autoRefresh()


    override fun onStop() = Unit


    open fun getDataList(source: LiveData<BaseResp<List<T>>>): LiveData<List<T>> {
        refreshing.postValue(false)
        moreLoading.postValue(false)
        return Transformations.map(source) {
            it.data
        }
    }

}