package com.stephen.jetpack.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stephen.common.ui.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    override fun onStart() {

    }

    override fun onStop() {
    }
}