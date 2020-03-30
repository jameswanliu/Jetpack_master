package com.stephen.common.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


inline fun ImageView.loadImage(path: Any, requestOptions: RequestOptions = RequestOptions()) {
    Glide.with(this.context).load(path).apply(requestOptions).into(this)
}

/**
 * 加载圆角图片
 */
inline fun ImageView.loadImageWithCorner(path: Any, corner: Int = 10) {
    val roundedCorners = RoundedCorners(corner)
    var requestOptions = RequestOptions.bitmapTransform(roundedCorners)
//    loadImage(path, requestOptions)
    Glide.with(this.context).load(path).apply(requestOptions).into(this)

}
