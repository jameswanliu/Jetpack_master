package com.stephen.jetpack.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stephen.jetpack.view.TitleBarView

/**
 * create by stephen
 * on 2020/5/14
 */
object TitleBarBinding {

    @BindingAdapter("app:rightClick")
    @JvmStatic
    fun TitleBarView.rightClick(block:()->Unit) {
        this.rightMenuOnclick {
            block
        }
    }
}