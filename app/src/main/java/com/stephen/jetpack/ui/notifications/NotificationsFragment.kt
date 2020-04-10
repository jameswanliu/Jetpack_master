package com.stephen.jetpack.ui.notifications

import android.os.Bundle
import android.view.View
import com.stephen.jetpack.base.BaseViewModelFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentNotificationsBinding
import com.stephen.jetpack.ui.home.HomeViewModel

class NotificationsFragment :
    BaseViewModelFragment<FragmentNotificationsBinding, NotificationsViewModel>() {
    companion object {
        fun newInstance(): NotificationsFragment {
            return NotificationsFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_notifications
    }


    override fun initData() {
        mViewModel  = viewModelFactory.create(NotificationsViewModel::class.java)
    }
}
