package com.stephen.jetpack.view

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.stephen.common.ext.otherwise
import com.stephen.common.ext.yes
import com.stephen.common.utils.SystemUtil.toDp
import com.stephen.jetpack.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.listeners.onClick

class TitleBarView : _RelativeLayout {

    private var textView: TextView

    private var title: String = ""

    private var color: Int = 0

    private var hideBackIcon = false

    private var rightMenuId: Int = 0

    private var hideMenuIcon = true

    private var hideCloseIcon = true

    private var rigthView: ImageButton? = null

    private var closeButton: ImageButton? = null


    private var backBlock: (() -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet) : super(context) {
        initAttr(attrs)
        gravity = Gravity.CENTER_VERTICAL
        backgroundColor = resources.getColor(R.color.colorPrimary)
        textView = textView {
            textColor = color
            textSize = 16.0f
            paint.isFakeBoldText = false
            gravity = Gravity.CENTER
            text = title
        }.lparams {
            width = LayoutParams.WRAP_CONTENT
//            gravity = Gravity.CENTER
//            centerInParent()
            centerHorizontally()
        }

        linearLayout {
            orientation = LinearLayout.HORIZONTAL

            imageButton {
                visibility = hideBackIcon.yes { View.GONE }.otherwise { View.VISIBLE }
                onClick {
                    backBlock?.let {
                        it()
                    }.let {
                        (context as Activity).finish()
                    }
                }

                imageResource = R.mipmap.icon_return
                backgroundColor = android.R.color.transparent
            }.lparams {
                width = resources.getDimension(R.dimen.dp_45).toInt()
                height = resources.getDimension(R.dimen.dp_45).toInt()
                gravity = Gravity.CENTER
                rightMargin = toDp(context, 5)
            }


            closeButton = imageButton {
                visibility = hideCloseIcon.yes { View.GONE }.otherwise { View.VISIBLE }
                imageResource = R.mipmap.icon_power_close
                backgroundColor = android.R.color.transparent
            }.lparams {
                width = resources.getDimension(R.dimen.avatar_large).toInt()
                height = resources.getDimension(R.dimen.avatar_large).toInt()
                gravity = Gravity.CENTER
            }

        }.lparams {
            width = LinearLayout.LayoutParams.WRAP_CONTENT
            height = LinearLayout.LayoutParams.WRAP_CONTENT
        }



        rigthView = imageButton {
            visibility = hideMenuIcon.yes { View.GONE }.otherwise { View.VISIBLE }
            imageResource = rightMenuId
        }.lparams {
            width = resources.getDimension(R.dimen.avatar_large).toInt()
            height = resources.getDimension(R.dimen.avatar_large).toInt()
            alignParentRight()
            rightMargin = toDp(context, 5)
        }
    }



    fun rightMenuOnclick(block: () -> Unit) {
        rigthView?.onClick {
            block()
        }
    }

    fun onClickBack(block: () -> Unit) {
        this.backBlock = block
    }


    fun closeMenu(block: () -> Unit) = closeButton?.onClick {
        block()
    }


    private fun initAttr(attrs: AttributeSet) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.TitleBarView).apply {
                title = getString(R.styleable.TitleBarView_bar_title) ?: ""
                color = getColor(R.styleable.TitleBarView_title_color, Color.parseColor("#213E58"))
                hideBackIcon = getBoolean(R.styleable.TitleBarView_back_menu_hide, false)
                hideMenuIcon = getBoolean(R.styleable.TitleBarView_right_menu_hide, true)
                hideCloseIcon = getBoolean(R.styleable.TitleBarView_hide_close_button, true)
                rightMenuId =
                    getResourceId(R.styleable.TitleBarView_menu_icon, R.mipmap.icon_power_close)
                recycle()
            }
        }
    }


}