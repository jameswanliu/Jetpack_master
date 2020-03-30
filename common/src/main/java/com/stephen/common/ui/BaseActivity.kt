package com.stephen.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<D:ViewDataBinding> :DaggerAppCompatActivity(){
    open lateinit var mbinding:D


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding  = DataBindingUtil.setContentView(this, getLayoutId())
        initData()
    }
    abstract fun  getLayoutId():Int
    abstract fun initData()
}