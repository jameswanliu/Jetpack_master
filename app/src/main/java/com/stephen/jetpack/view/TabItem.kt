package com.stephen.jetpack.view

import androidx.fragment.app.Fragment

/**
 * create by stephen
 * on 2020/5/14
 */


data class TabItem(val resId: Int, val title: String, val cls: Class<out Fragment>)