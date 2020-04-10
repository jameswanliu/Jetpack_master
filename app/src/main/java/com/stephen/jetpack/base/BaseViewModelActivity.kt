package com.stephen.jetpack.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.stephen.common.ui.BaseActivity
import com.stephen.common.ui.BaseViewModel
import javax.inject.Inject

/**
 * create by stephen
 * on 2020/3/30
 */
abstract class BaseViewModelActivity<ViewModel : BaseViewModel, D : ViewDataBinding> :
    BaseActivity<D>() {
    lateinit var mViewModel: ViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onStop()
    }

}