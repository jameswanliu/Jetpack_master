package com.stephen.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel() {
    abstract fun onStart();
    abstract fun onStop();
}