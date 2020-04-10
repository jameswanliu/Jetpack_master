package com.stephen.jetpack.ui.dashboard

import com.stephen.jetpack.base.BaseViewModelFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentDashboardBinding
import com.stephen.jetpack.ui.notifications.NotificationsViewModel

class DashboardFragment : BaseViewModelFragment<FragmentDashboardBinding, DashboardViewModel>() {
    companion object{
        fun newInstance():DashboardFragment{
            return DashboardFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_dashboard
    }

    override fun initData() {
        mViewModel  = viewModelFactory.create(DashboardViewModel::class.java)
    }


}
