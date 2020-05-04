package com.stephen.jetpack.ui.notifications

import com.stephen.common.ui.BaseFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentNotificationsBinding

class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding>() {
    companion object {
        fun newInstance(): NotificationsFragment {
            return NotificationsFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_notifications
    }


    override fun initData() {
    }
}
