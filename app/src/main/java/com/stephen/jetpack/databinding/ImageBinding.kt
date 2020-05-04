package com.stephen.jetpack.databinding

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by cuongpm on 12/18/18.
 */

object ImageBinding {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun ImageView.loadImage(url: String) {
        Glide.with(context).load(url).into(this)
    }

}