package com.stephen.jetpack.data.remote

import androidx.paging.PagedList
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.bean.GirlResp
import com.stephen.jetpack.data.repository.GirlRepository
import com.stephen.jetpack.net.ApiService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * create by stephen
 * on 2020/5/6
 */


@Singleton
class GirlRemoteDataSource @Inject constructor(private val apiService: ApiService) : GirlRepository {
    override fun getGirlDataList(position: Int): Observable<GirlResp> =
        apiService.getGirlsList(position)

}