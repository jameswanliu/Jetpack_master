package com.stephen.jetpack.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.stephen.jetpack.adapter.SampleAdapter


@BindingAdapter("app:refreshListener")
fun LRecyclerView.setRefreshListener(invoke: Function0<Any>) {
    this.setOnRefreshListener { invoke.invoke() }
}


/**
 * 先设置lRecyclerViewAdapter 再设置OnItemClick
 */
@BindingAdapter("app:refreshComplete")
fun LRecyclerView.refreshComplete(size: Int) {
    if (this.adapter != null) {
        if ((this.adapter as LRecyclerViewAdapter).innerAdapter != null) {
            this.refreshComplete(size)
        }
    }
}


@BindingAdapter("app:setAdapter")
fun LRecyclerView.setSampleAdapter(adapter: SampleAdapter) {
    val lRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
    this.adapter = lRecyclerViewAdapter
}
