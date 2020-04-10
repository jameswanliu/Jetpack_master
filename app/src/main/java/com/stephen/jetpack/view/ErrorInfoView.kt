package com.stephen.jetpack.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import com.stephen.jetpack.R
import org.jetbrains.anko.*


/**
 * Created by benny on 7/15/17.
 */
class ErrorInfoView(val parentView: ViewGroup) : _RelativeLayout(parentView.context) {

    private var textView: TextView

    var isShowing = false

    init {
        backgroundColor = Color.WHITE
        textView = textView {
            textSize = 16f
            textColor = resources.getColor(R.color.nothing_color)
            val drawable = resources.getDrawable(R.mipmap.logo_nothing)
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null)
            compoundDrawablePadding = resources.getDimension(R.dimen.dp_25).toInt()
            padding = dip(5)
            gravity = Gravity.CENTER
        }.lparams {
            width = LayoutParams.WRAP_CONTENT
            height = LayoutParams.WRAP_CONTENT
            topMargin = resources.getDimension(R.dimen.dp_97).toInt()
            centerHorizontally()
        }
    }

    fun show(text: String) {
        if (!isShowing) {
            parentView.addView(this, matchParent, matchParent)
            alpha = 0f
            animate().alpha(1f).setDuration(100).start()
            isShowing = true
        }
        textView.text = text
    }

    fun dismiss() {
        if (isShowing) {
            parentView.startViewTransition(this)
            parentView.removeView(this)
            animate().alpha(0f).setDuration(100).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    parentView.endViewTransition(this@ErrorInfoView)
                }
            }).start()
            isShowing = false
        }
    }

    fun show(@StringRes textRes: Int) = show(context.getString(textRes))
}