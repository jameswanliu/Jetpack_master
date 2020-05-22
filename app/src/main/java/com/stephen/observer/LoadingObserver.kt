package com.stephen.observer

import android.content.Context
import androidx.lifecycle.Observer
import com.stephen.common.ext.otherwise
import com.stephen.common.ext.yes
import com.stephen.jetpack.view.LoadingDialog

/**
 * create by stephen
 * on 2020/5/22
 */


class LoadingObserver(context: Context) : Observer<Boolean> {
    private val dialog = LoadingDialog.createDialog(context)
    override fun onChanged(t: Boolean?) {
        t?.let {
            it.yes {
                dialog.show()
            }.otherwise {
                dialog.dismiss()
            }
        }
    }

}