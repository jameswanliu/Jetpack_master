package com.stephen.jetpack.bean

import androidx.recyclerview.widget.RecyclerView

/**
 * create by stephen
 * on 2020/4/10
 */


class AdapterList<T>(val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : ArrayList<T>() {


    override fun remove(element: T): Boolean {
        val index = indexOf(element)
        return super.remove(element).apply {
            adapter.notifyItemRemoved(index)
        }
    }


    override fun addAll(elements: Collection<T>): Boolean {
        return super.addAll(elements).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        return super.addAll(index, elements).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun clear() {
        super.clear()
        adapter.notifyDataSetChanged()
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        adapter.notifyItemInserted(index)
    }

    override fun add(element: T): Boolean {
        return super.add(element).apply {
            adapter.notifyItemInserted(size - 1)
        }
    }


    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex).apply {
            super.removeRange(fromIndex, toIndex)
                .apply { adapter.notifyItemRangeRemoved(fromIndex, toIndex) }
        }
    }

    fun update(elements: Collection<T>) {
        super.clear()
        super.addAll(elements)
        adapter.notifyDataSetChanged()
    }


    override fun set(index: Int, element: T): T {
        return super.set(index, element).also {
            adapter.notifyDataSetChanged()
        }
    }


    override fun removeAt(index: Int): T {
        return super.removeAt(index).apply {
            adapter.notifyItemRemoved(index)
            adapter.notifyDataSetChanged()
        }
    }
}