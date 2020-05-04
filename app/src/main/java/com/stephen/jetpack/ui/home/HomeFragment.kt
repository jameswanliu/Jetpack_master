package com.stephen.jetpack.ui.home

import com.stephen.common.ui.BaseFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    private lateinit var homeViewModel: HomeViewModel

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        mBinding.radapter = homeViewModel.adapter


    }
}
