package com.stephen.jetpack.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.jdsjlzx.interfaces.OnLoadMoreListener
import com.github.jdsjlzx.interfaces.OnRefreshListener
import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.stephen.jetpack.adapter.SampleAdapter


@BindingAdapter("refreshListener")
fun LRecyclerView.refreshListener(block: () -> Unit) {
    setOnRefreshListener { block.invoke() }
}


@BindingAdapter("refresh")
fun LRecyclerView.refresh(boolean: Boolean) {
    if (boolean) refresh()
}


@BindingAdapter("loadMoredListener")
fun LRecyclerView.loadMoredListener(block: () -> Unit) {
    setOnRefreshListener { block.invoke() }
}

@BindingAdapter(value = ["setAdapter", "loadmoreEnable"], requireAll = false)
fun setSampleAdapter(lRecyclerView: LRecyclerView, adapter: SampleAdapter, flag: Boolean) {
    val lRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
    lRecyclerView.adapter = lRecyclerViewAdapter
    lRecyclerView.itemAnimator = DefaultItemAnimator()
    lRecyclerView.layoutManager =
        LinearLayoutManager(lRecyclerView.context, LinearLayoutManager.VERTICAL, false)
    lRecyclerView.setLoadMoreEnabled(flag)
}


@BindingAdapter("refreshComplete")
fun LRecyclerView.refreshComplete(size:Int) {
    if (adapter != null) {
        if ((adapter as LRecyclerViewAdapter).innerAdapter != null) {
            refreshComplete(size)
        }
    }
}

