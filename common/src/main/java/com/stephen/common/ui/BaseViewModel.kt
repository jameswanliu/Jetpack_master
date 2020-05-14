package com.stephen.common.ui

import androidx.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel() {
    abstract fun onStart()
    abstract fun onStop()
}