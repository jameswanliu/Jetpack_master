package com.stephen.common.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.stephen.common.BaseViewModel
import javax.inject.Inject

abstract class BaseViewModelActivity<D:ViewDataBinding,ViewModel:BaseViewModel> :BaseActivity<D>(){

    lateinit var mViewModel: ViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel  = viewModelFactory.create(mViewModel::class.java).apply {
            onStart()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onStop()
    }

}