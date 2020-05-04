package com.stephen.jetpack.ui.dashboard

import com.stephen.common.ui.BaseFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    companion object{
        fun newInstance():DashboardFragment{
            return DashboardFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_dashboard
    }

    override fun initData() {
    }


}
