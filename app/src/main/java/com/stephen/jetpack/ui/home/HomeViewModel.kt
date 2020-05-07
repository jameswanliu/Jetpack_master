package com.stephen.jetpack.ui.home

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.stephen.common.bean.BaseResp
import com.stephen.jetpack.adapter.SampleAdapter
import com.stephen.jetpack.base.CommonPageViewModel
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.data.repository.GirlRepository
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val girlRepository: GirlRepository) :
    CommonPageViewModel<GirlBean>() {
    override val adapter = SampleAdapter()
    override fun getDataList(position: Int): LiveData<BaseResp<PagedList<GirlBean>>> =  girlRepository.getGirlDataList(position)
}