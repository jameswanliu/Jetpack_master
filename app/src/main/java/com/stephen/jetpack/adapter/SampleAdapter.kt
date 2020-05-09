package com.stephen.jetpack.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stephen.jetpack.R
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.databinding.LayoutSampleBinding

/**
 * create by stephen
 * on 2020/4/29
 */

class SampleAdapter : CommonPageListAdapter
<GirlBean, LayoutSampleBinding>(R.layout.layout_sample, callback) {
    companion object {
        val callback = object : DiffUtil.ItemCallback<GirlBean>() {
            override fun areItemsTheSame(oldItem: GirlBean, newItem: GirlBean): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: GirlBean, newItem: GirlBean): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onBind(mBinding: LayoutSampleBinding, position: Int) {
        mBinding.bean = getItem(position)
    }
}