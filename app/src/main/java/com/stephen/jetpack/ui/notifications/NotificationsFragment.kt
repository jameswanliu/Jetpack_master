package com.stephen.jetpack.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stephen.common.ui.BaseViewModelFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentNotificationsBinding

class NotificationsFragment : BaseViewModelFragment<FragmentNotificationsBinding,NotificationsViewModel>() {
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
