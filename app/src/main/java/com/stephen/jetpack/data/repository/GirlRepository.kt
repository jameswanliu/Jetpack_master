package com.stephen.jetpack.data.repository

import androidx.paging.PagedList
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.bean.GirlResp
import com.stephen.jetpack.di.qualifier.RemoteData
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * create by stephen
 * on 2020/5/6
 */


interface GirlRepository {
    fun getGirlDataList(position: Int): Observable<GirlResp>
}

@Singleton
class GirlRepositoryImpl @Inject constructor(@RemoteData private val girlRemoteDataSource: GirlRepository) : GirlRepository {
    override fun getGirlDataList(position: Int): Observable<GirlResp> =
        girlRemoteDataSource.getGirlDataList(position)
}