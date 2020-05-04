package com.stephen.jetpack.ui.home

import com.stephen.jetpack.adapter.SampleAdapter
import com.stephen.jetpack.base.CommonPageViewModel
import com.stephen.jetpack.bean.GirlBean
import javax.inject.Inject

class HomeViewModel @Inject constructor() : CommonPageViewModel<GirlBean>() {

    override val adapter = SampleAdapter()

    override fun onStart() {

    }
    override fun onStop() {

    }

}