package com.stephen.jetpack.data.remote

import androidx.lifecycle.LiveData
import com.stephen.common.bean.BaseResp
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.data.repository.GirlRepository
import com.stephen.jetpack.net.ApiService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * create by stephen
 * on 2020/5/6
 */


@Singleton
class GirlRemoteDataSource @Inject constructor(private val apiService: ApiService) : GirlRepository {
    override fun getGirlDataList(position: Int): LiveData<BaseResp<List<GirlBean>>> =
        apiService.getGirlsList(position)
}