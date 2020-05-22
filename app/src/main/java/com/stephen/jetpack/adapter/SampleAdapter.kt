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
<GirlBean, LayoutSampleBinding>(R.layout.layout_sample) {
    override fun onBind(mBinding: LayoutSampleBinding, position: Int) {
        mBinding.bean = data[position]
    }
    override fun getItemCount(): Int = data.size
}