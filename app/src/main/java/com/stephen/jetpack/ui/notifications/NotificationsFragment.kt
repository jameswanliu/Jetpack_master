package com.stephen.jetpack.ui.notifications

import com.stephen.jetpack.base.BaseViewModelFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentNotificationsBinding

class NotificationsFragment : BaseViewModelFragment<FragmentNotificationsBinding, NotificationsViewModel>() {
    companion object{
        fun newInstance():NotificationsFragment{
            return NotificationsFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_notifications
    }

    override fun initData() {
    }
}
