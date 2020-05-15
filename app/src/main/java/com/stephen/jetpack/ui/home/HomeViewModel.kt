package com.stephen.jetpack.ui.home

import android.view.View
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
    override val adapter = SampleAdapter()
    override fun getDataList(position: Int): Observable<List<GirlBean>> =  girlRepository.getGirlDataList(position).convert()
    override fun onItemClick(view: View, position: Int) = logger.info("onItemClick = $position")
}