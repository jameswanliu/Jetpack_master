package com.stephen.jetpack.ui.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.stephen.common.ui.BaseFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun initData() {
        homeViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.onStart()
        mBinding.radapter = homeViewModel.adapter
        homeViewModel.liveDataPage.observe(this, Observer {
            homeViewModel.adapter.submitList(it)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        homeViewModel.onStop()
    }
}
