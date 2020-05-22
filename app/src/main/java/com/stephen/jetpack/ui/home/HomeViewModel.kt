package com.stephen.jetpack.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.stephen.common.log.logger
import com.stephen.common.rx.convert
import com.stephen.jetpack.adapter.SampleAdapter
import com.stephen.jetpack.base.CommonPageViewModel
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.data.repository.GirlRepository
import io.reactivex.Observable
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val girlRepository: GirlRepository) :
    CommonPageViewModel<GirlBean>() {
    override fun onItemClick(view: View, position: Int) = Unit
    override val adapter = SampleAdapter()
    val dataObserver =
        getDataList(Transformations.switchMap(page) { girlRepository.getGirlDataList(it) })
}