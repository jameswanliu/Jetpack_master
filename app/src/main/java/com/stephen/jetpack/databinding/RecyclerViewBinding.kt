package com.stephen.jetpack.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.github.jdsjlzx.interfaces.OnLoadMoreListener
import com.github.jdsjlzx.interfaces.OnRefreshListener
import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.stephen.jetpack.adapter.SampleAdapter


@BindingAdapter(value = ["refreshListener", "loadMoreListener"], requireAll = false)
fun setRefreshListener(
    lRecyclerView: LRecyclerView,
    onRefreshListener: OnRefreshListener,
    onLoadMoreListener: OnLoadMoreListener
) {
    lRecyclerView.setOnRefreshListener(onRefreshListener)
    lRecyclerView.setOnLoadMoreListener(onLoadMoreListener)
}


/**
 * 先设置lRecyclerViewAdapter 再设置OnItemClick
 */
@BindingAdapter("refreshComplete")
fun refreshComplete(lRecyclerView: LRecyclerView, size: Int) {
    if (lRecyclerView.adapter != null) {
        if ((lRecyclerView.adapter as LRecyclerViewAdapter).innerAdapter != null) {
            lRecyclerView.refreshComplete(size)
        }
    }
}


@BindingAdapter(value = ["setAdapter", "loadmoreEnable"])
fun setSampleAdapter(lRecyclerView: LRecyclerView, adapter: SampleAdapter, flag: Boolean) {
    val lRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
    lRecyclerView.adapter = lRecyclerViewAdapter
    lRecyclerView.setLoadMoreEnabled(flag)
}
