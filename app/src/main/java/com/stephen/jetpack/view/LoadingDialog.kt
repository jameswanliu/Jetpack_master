package com.stephen.jetpack.view

import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AppCompatDialog
import com.stephen.jetpack.R
import com.wang.avi.AVLoadingIndicatorView

/**
 * create by stephen
 * on 2020/5/22
 */


class LoadingDialog : AppCompatDialog {
    private var avLoadingIndicatorView: AVLoadingIndicatorView? = null
    companion object {
        private var loadingDialog: LoadingDialog? = null
        fun createDialog(context: Context): LoadingDialog {
            loadingDialog = LoadingDialog(context, R.style.CustomProgressDialog)
            loadingDialog?.setContentView(R.layout.customprogressdialog)
            loadingDialog?.window?.attributes?.gravity = Gravity.CENTER
            return loadingDialog!!
        }
    }
    @JvmOverloads
    constructor(context: Context, theme: Int) : super(context, theme)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        loadingDialog?.let {
            avLoadingIndicatorView?.show()
        }
    }


    override fun show() {
        super.show()
    }

    override fun dismiss() {
        avLoadingIndicatorView?.hide()
        super.dismiss()
    }


}