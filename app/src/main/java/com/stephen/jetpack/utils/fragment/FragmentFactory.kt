package com.stephen.jetpack.utils.fragment

import androidx.fragment.app.Fragment
import com.stephen.jetpack.ui.dashboard.DashboardFragment
import com.stephen.jetpack.ui.home.HomeFragment
import com.stephen.jetpack.ui.notifications.NotificationsFragment
import javax.inject.Inject


interface FragmentFactory {
    fun createHomeFragment(): Fragment
    fun createDashboardFragment(): Fragment
    fun createNotificationsFragment(): Fragment
}

class FragmentFactoryImpl @Inject constructor() : FragmentFactory {
    override fun createHomeFragment() = HomeFragment.newInstance()
    override fun createDashboardFragment() = DashboardFragment.newInstance()
    override fun createNotificationsFragment() = NotificationsFragment.newInstance()
}