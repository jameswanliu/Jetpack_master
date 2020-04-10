package com.stephen.jetpack.viewmodel

import com.stephen.common.ui.BaseViewModel

/**
 * create by stephen
 * on 2020/4/10
 */
abstract class CommonListViewModel<Data> :BaseViewModel() {

    override fun onStart() {

    }

    override fun onStop() {
    }

    abstract val listPage: ArrayList<Data>
    open fun refreshData() = Unit

    open fun loadMore() = Unit

    open fun initData() = Unit

}