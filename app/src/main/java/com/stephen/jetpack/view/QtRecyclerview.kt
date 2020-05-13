package com.stephen.jetpack.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.github.jdsjlzx.recyclerview.LRecyclerView

/**
 * create by stephen
 * on 2020/5/13
 *
 * 量子recyclerview
 */


class QtRecyclerview : RelativeLayout {

    private val recyclerView by lazy {
        LRecyclerView(this.context)
    }

    private val errorInfoView by lazy {
        ErrorInfoView(this)
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )


    fun showProgress() = errorInfoView.dismiss()
    fun showContent() = errorInfoView.dismiss()
    fun showErrorView(msg: String = "") = errorInfoView.show(msg)


}