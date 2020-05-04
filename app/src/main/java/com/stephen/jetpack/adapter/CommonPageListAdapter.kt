package com.stephen.jetpack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * create by stephen
 * on 2020/5/4
 */
abstract class CommonPageListAdapter<T, D : ViewDataBinding> constructor(
    private val resid: Int,
    callback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T,
        CommonPageListAdapter.CommonViewHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val d: D = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            resid,
            parent,
            false
        )
        return CommonViewHolder(d)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val mBinding: D = DataBindingUtil.getBinding(holder.itemView)!!
        onBind(mBinding, position)
    }


    abstract fun onBind(mBinding: D, position: Int)
    class CommonViewHolder(viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}