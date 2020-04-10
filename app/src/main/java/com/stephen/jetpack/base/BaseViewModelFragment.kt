package com.stephen.jetpack.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.stephen.common.ui.BaseFragment
import com.stephen.common.ui.BaseViewModel
import javax.inject.Inject

/**
 * create by stephen
 * on 2020/3/30
 */
abstract class BaseViewModelFragment<D:ViewDataBinding,ViewModel: BaseViewModel>: BaseFragment<D>() {
    lateinit var mViewModel: ViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onStop()
    }

}