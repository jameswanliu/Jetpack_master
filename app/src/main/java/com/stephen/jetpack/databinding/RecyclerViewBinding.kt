package com.stephen.jetpack.databinding

import androidx.databinding.BindingAdapter
import com.github.jdsjlzx.interfaces.OnRefreshListener
import com.github.jdsjlzx.recyclerview.LRecyclerView


object RecyclerViewBinding {


    @BindingAdapter("app:refreshListener")
    @JvmStatic
    fun LRecyclerView.setRefreshListener(listener: OnRefreshListener) {
        this.apply {
            setOnRefreshListener(listener)
        }
    }





}