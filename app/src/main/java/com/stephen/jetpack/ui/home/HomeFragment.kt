package com.stephen.jetpack.ui.home

import com.stephen.jetpack.base.BaseViewModelFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentHomeBinding

class HomeFragment : BaseViewModelFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object{
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
    }
}
