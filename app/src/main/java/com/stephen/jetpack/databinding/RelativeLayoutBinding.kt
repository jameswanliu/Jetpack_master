package com.stephen.jetpack.databinding

import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.stephen.jetpack.view.ErrorInfoView

/**
 * create by stephen
 * on 2020/4/17
 */


object RelativeLayoutBinding {
    @BindingAdapter("app:errorview")
    @JvmStatic
    fun RelativeLayout.showErrorView(view: ErrorInfoView) {
        this.addView(view,0)
    }


    @BindingAdapter("app:showContent")
    @JvmStatic
    fun RelativeLayout.dismissErrorView(view: ErrorInfoView) {
        this.removeViewAt(0)
    }

}