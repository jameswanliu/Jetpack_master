package com.stephen.common.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.stephen.common.BaseViewModel
import javax.inject.Inject

/**
 * create by stephen
 * on 2020/3/30
 */
abstract class BaseViewModelActivity<D:ViewDataBinding,ViewModel:BaseViewModel> :BaseActivity<D>(){

    lateinit var mViewModel: ViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(mViewModel::class.java)
        mViewModel.onStart()
    }


    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onStop()
    }

}