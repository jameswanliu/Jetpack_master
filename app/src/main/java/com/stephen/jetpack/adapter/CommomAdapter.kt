package com.stephen.jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.stephen.jetpack.bean.AdapterList


abstract class CommomAdapter<T>(@LayoutRes val resId: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    val data = AdapterList<T>(this)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CommonViewHolder(LayoutInflater.from(parent.context).inflate(resId, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = onBindData(holder, data[position])

    override fun getItemCount(): Int = data.size

    abstract fun onBindData(viewHolder: RecyclerView.ViewHolder, item: T)


    class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}