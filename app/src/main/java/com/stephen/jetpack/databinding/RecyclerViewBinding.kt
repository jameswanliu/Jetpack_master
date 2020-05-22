package com.stephen.jetpack.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.stephen.jetpack.adapter.SampleAdapter


@BindingAdapter(
    value = ["refreshing", "moreLoading", "hasMore"],
    requireAll = false
)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    refreshing: Boolean,
    moreLoading: Boolean,
    hasMore: Boolean

) {
    if (!refreshing) smartLayout.finishRefresh()
    if (!moreLoading) smartLayout.finishLoadMore()
    smartLayout.setNoMoreData(!hasMore)
}


@BindingAdapter(
    value = ["onRefreshListener", "onLoadMoreListener"],
    requireAll = false
)
fun bindListener(
    smartLayout: SmartRefreshLayout,
    refreshListener: OnRefreshListener?,
    loadMoreListener: OnLoadMoreListener?
) {
    smartLayout.setOnRefreshListener(refreshListener)
    smartLayout.setOnLoadMoreListener(loadMoreListener)
}



@BindingAdapter(
    value = ["autoRefresh"]
)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    autoRefresh: Boolean
) {
    if (autoRefresh) smartLayout.autoRefresh()
}


@BindingAdapter(value = ["setAdapter"], requireAll = false)
fun setSampleAdapter(recyclerView: RecyclerView, adapter: SampleAdapter) {
    recyclerView.itemAnimator = DefaultItemAnimator()
    recyclerView.layoutManager =
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    recyclerView.adapter = adapter
}



